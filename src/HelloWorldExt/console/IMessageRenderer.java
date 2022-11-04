package HelloWorldExt.console;

public interface IMessageRenderer {
    IMessageProvider getMessageProvider();
    void setMessageProvider(IMessageProvider mp);
    void render();
}
