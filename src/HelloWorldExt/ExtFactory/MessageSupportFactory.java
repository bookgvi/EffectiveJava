package HelloWorldExt.ExtFactory;

import HelloWorldExt.Factory.IMessageProvider;
import HelloWorldExt.Factory.IMessageRenderer;

import java.util.Properties;

public enum MessageSupportFactory {
    INSTANCE;

    private IMessageProvider mp;
    private IMessageRenderer mr;

    MessageSupportFactory() {
        Properties props = new Properties();
        try {
            props.load(this.getClass().getResourceAsStream("../qqq/msf.properties"));
            String mrClass = props.getProperty("renderProvider.class");
            String mpClass = props.getProperty("messageProvider.class");

            mr = (IMessageRenderer) Class.forName(mrClass).getDeclaredConstructor().newInstance();
            mp = (IMessageProvider) Class.forName(mpClass).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public IMessageProvider getMp() {
        return mp;
    }

    public IMessageRenderer getMr() {
        return mr;
    }
}
