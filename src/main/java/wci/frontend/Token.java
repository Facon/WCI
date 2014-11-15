package wci.frontend;

public class Token {
    protected TokenType type; // Token específico del lenguaje
    protected String text; // Texto del token
    protected Object value; // Valor del token
    protected Source source; // Fuente al que pertenece el Token
    protected int lineNum; // Nº de línea del fuente donde esta el token
    protected int position; // Posición del 1º carácter del token

    public Token(Source source) throws Exception {
        this.source = source;
        this.lineNum = source.getLineNum();
        this.position = source.getPosition();

        extract();
    }

    protected void extract() throws Exception {
        text = Character.toString(currentChar());
        value = null;

        nextChar(); // Consumir carácter actual
    }

    protected char currentChar() throws Exception {
        return source.currentChar();
    }

    protected char nextChar() throws Exception {
        return source.nextChar();
    }

    protected char peekChar() throws Exception {
        return source.peekChar();
    }

    public Number getLineNumber() {
        return source.getLineNum();
    }
}
