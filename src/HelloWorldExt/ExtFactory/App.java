package HelloWorldExt.ExtFactory;

import HelloWorldExt.Factory.IMessageProvider;
import HelloWorldExt.Factory.IMessageRenderer;

public class App {
    public static void main(String[] args) {
        IMessageProvider mp = MessageSupportFactory.INSTANCE.getMp();
        IMessageRenderer mr = MessageSupportFactory.INSTANCE.getMr();
        mr.setMessageProvider(mp);
        mr.render();

    }
}
