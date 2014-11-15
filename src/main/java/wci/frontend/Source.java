package wci.frontend;

import wci.message.*;

import java.io.BufferedReader;
import java.io.IOException;

public class Source implements MessageProducer {
    public static final char EOL = '\n';
    public static final char EOF = (char) 0;

    protected static MessageHandler messageHandler = new MessageHandler();

    private BufferedReader reader;
    private String line;
    private int lineNum;
    private int currentPos;

    public Source(BufferedReader reader) throws IOException {
        this.lineNum = 0;
        this.currentPos = -2; // Para leer la primera línea del fuente.
        this.reader = reader;
    }

    public int getLineNum() {
        return lineNum;
    }

    public int getPosition() {
        return currentPos;
    }

    public char currentChar() throws Exception {
        // ¿Primera vez leyendo el fuente?
        if (currentPos == -2) {
            readLine();
            return nextChar();
        }

        // ¿Final de fichero?
        if (line == null) {
            return EOF;
        }

        // ¿Final de línea?
        if ((currentPos == -1) || (currentPos == line.length())) {
            return EOL;
        }

        // ¿Se necesita leer la siguiente línea?
        if (currentPos > line.length()) {
            readLine();
            return nextChar();
        } else { // Devolver carácter en la posición actual
            return line.charAt(currentPos);
        }
    }

    public char nextChar() throws Exception {
        ++currentPos;
        return currentChar();
    }

    public char peekChar() throws Exception {
        // Leer carácter actual
        currentChar();

        if (line == null) {
            return EOF;
        }

        int nextPos = currentPos + 1;
        return nextPos < line.length() ? line.charAt(nextPos) : EOL;
    }

    private void readLine() throws IOException {
        line = reader.readLine(); // Null al final del fuente
        currentPos = 0;

        if (line != null) {
            ++lineNum;
            sendMessage(new Message(MessageType.SOURCE_LINE, new Object[]{lineNum, line}));
        }
    }

    public void close() throws Exception {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }

    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }

    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }

    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }
}
