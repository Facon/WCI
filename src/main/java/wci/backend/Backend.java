package wci.backend;

import wci.intermediate.ICode;
import wci.intermediate.SymTab;
import wci.message.Message;
import wci.message.MessageHandler;
import wci.message.MessageListener;

/**
 * Created by Facon on 15/11/2014.
 */
public abstract class Backend {
    protected static MessageHandler messageHandler = new MessageHandler();
    protected SymTab symTab;
    protected ICode iCode;

    public abstract void process(ICode iCode, SymTab symTab) throws Exception;

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
