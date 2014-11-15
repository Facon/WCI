package wci.frontend.pascal;

import wci.frontend.EofToken;
import wci.frontend.Parser;
import wci.frontend.Scanner;
import wci.frontend.Token;
import wci.message.Message;
import wci.message.MessageType;

public class PascalParserTD extends Parser {
    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis(); // Iniciamos cronómetro

        // Mientras el token no sea EOF, seguimos procesando tokens
        while (!((token = nextToken()) instanceof EofToken)) {
        }

        // Cálculamos el tiempo transcurrido hasta procesar _todo el código fuente y enviamos un mensaje
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
        sendMessage(new Message(MessageType.PARSER_SUMMARY, new Number[]{token.getLineNumber(), getErrorCount(), elapsedTime}));
    }

    @Override
    public int getErrorCount() {
        return 0;
    }
}
