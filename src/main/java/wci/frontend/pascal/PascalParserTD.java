package wci.frontend.pascal;

import wci.frontend.*;
import wci.message.Message;
import wci.message.MessageType;

import java.io.IOException;

public class PascalParserTD extends Parser {
    protected static PascalErrorHandler errorHandler = new PascalErrorHandler();

    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis(); // Iniciamos cronómetro

        try {
            // Mientras el token no sea EOF, seguimos procesando tokens
            while (!((token = nextToken()) instanceof EofToken)) {
                TokenType tokenType = token.getType();

                if (tokenType != PascalTokenType.ERROR) {
                    // Formatear cada token
                    sendMessage(new Message(MessageType.TOKEN, new Object[]{
                            token.getLineNumber(),
                            token.getPosition(),
                            tokenType,
                            token.getText(),
                            token.getValue()
                    }));
                } else {
                    errorHandler.flag(token, (PascalErrorCode) token.getValue(), this);
                }
            }

            // Enviar un resumen al parser
            float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
            sendMessage(new Message(MessageType.PARSER_SUMMARY, new Number[]{
                    token.getLineNumber(),
                    getErrorCount(),
                    elapsedTime
            }));
        } catch (IOException ex) {
            errorHandler.abortTranslation(PascalErrorCode.IO_ERROR, this);
        }
    }

    @Override
    public int getErrorCount() {
        return errorHandler.getErrorCount();
    }
}
