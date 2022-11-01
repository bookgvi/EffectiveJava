package Cleaners;

import java.lang.ref.Cleaner;

public class AutumnStreet implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();

    private static class Leaf implements Runnable {
        private int leafCount;

        Leaf(int leafCount) {
            this.leafCount = leafCount;
        }

        @Override
        public void run() {
            this.leafCount = 0;
            System.out.println("Cleaning finish");
        }
    }

    private final Cleaner.Cleanable cleanable;
    private Leaf leaf;

    AutumnStreet(int leafCount) {
        this.leaf = new Leaf(leafCount);
        this.cleanable = cleaner.register(this, leaf);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }
}
