package Cleaners;

import java.lang.ref.Cleaner;

public class Cleanable1 implements AutoCloseable {
    private State state;
    private Cleaner.Cleanable cleanable;
    private Cleaner cleaner = Cleaner.create();

    Cleanable1(int juncCount) {
        this.state = new State(juncCount);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }


    private static class State implements Runnable {
        private int juncCount;

        State(int juncCount) {
            this.juncCount = juncCount;
        }

        @Override
        public void run() {
            this.juncCount = 0;
            System.out.println("Cleaned...");
        }
    }
}
