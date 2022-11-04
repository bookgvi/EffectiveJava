package HelloWorldExt.Factory;

public class HelloWorldMessageProvider implements IMessageProvider {
    @Override
    public String getMessage() {
        return "Hello, world!";
    }
}
