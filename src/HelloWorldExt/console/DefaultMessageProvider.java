package HelloWorldExt.console;

public class DefaultMessageProvider implements IMessageProvider {
    private final String defaultMsg = "Hello, world";
    @Override
    public String getMessage() {
        return defaultMsg;
    }
}
