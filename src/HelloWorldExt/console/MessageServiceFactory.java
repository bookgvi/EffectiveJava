package HelloWorldExt.console;

import java.util.Properties;

public class MessageServiceFactory {

    private IMessageRenderer mr;
    private IMessageProvider mp;

    public MessageServiceFactory() {
        Properties props = new Properties();
        try {
            props.load(this.getClass().getResourceAsStream("msf.properties"));
            String renderer = props.getProperty("renderer");
            String provider = props.getProperty("provider");
            mr = (IMessageRenderer) Class.forName(renderer).getDeclaredConstructor().newInstance();
            mp = (IMessageProvider) Class.forName(provider).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public IMessageRenderer getMr() {
        return mr;
    }

    public IMessageProvider getMp() {
        return mp;
    }
}
