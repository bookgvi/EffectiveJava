package HelloWorldExt.console;

import java.util.Objects;

public class MessageRenderer implements IMessageRenderer {
    private IMessageProvider mp;
    @Override
    public IMessageProvider getMessageProvider() {
        boolean isNull = Objects.nonNull(mp);
        return mp;
    }

    @Override
    public void setMessageProvider(IMessageProvider mp) {
        this.mp = mp;
    }

    @Override
    public void render() {
        boolean isNull = Objects.nonNull(mp);
        System.out.println(mp.getMessage());
    }
}
