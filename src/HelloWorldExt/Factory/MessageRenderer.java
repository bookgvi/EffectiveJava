package HelloWorldExt.Factory;

public class MessageRenderer implements IMessageRenderer {
    IMessageProvider mp;

    @Override
    public void render() {
        if (mp == null) throw new RuntimeException("Check message provider");
        else System.out.println(mp.getMessage());
    }

    @Override
    public IMessageProvider getMessageProvider() {
        return this.mp;
    }

    @Override
    public void setMessageProvider(IMessageProvider mp) {
        this.mp = mp;
    }
}
