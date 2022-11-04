package HelloWorldExt.console;

public class ConsoleApp {
    public static void main(String[] args) {
        MessageServiceFactory msf =  new MessageServiceFactory();
        IMessageRenderer mr = msf.getMr();
        IMessageProvider mp = msf.getMp();
        mr.setMessageProvider(mp);
        mr.render();
    }
}
