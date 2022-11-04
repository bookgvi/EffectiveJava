package HelloWorldExt.Factory;

public class App {
    public static void main(String[] args) {
        IMessageProvider mp = new HelloWorldMessageProvider();
        IMessageRenderer mr = new MessageRenderer();
        mr.setMessageProvider(mp);
        mr.render();
    }
}
