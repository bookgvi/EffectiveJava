package Optional;

public class OptionalTest {
    private Dummy dummy;
    OptionalTest(Dummy dummy) {
        this.dummy = dummy;
    }

    public Dummy getDummy() {
        return dummy;
    }

    public void setDummy(Dummy dummy) {
        this.dummy = dummy;
    }
}
