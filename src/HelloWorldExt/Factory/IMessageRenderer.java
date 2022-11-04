package HelloWorldExt.Factory;

public interface IMessageRenderer {
    void render();
    IMessageProvider getMessageProvider();
    void setMessageProvider(IMessageProvider mp);
}
