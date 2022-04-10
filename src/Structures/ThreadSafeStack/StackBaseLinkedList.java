package Structures.ThreadSafeStack;

public class StackBaseLinkedList<V> {
    ListNode<V> stack = null;
    private final CAS<ListNode<V>> atomicVal = new CAS<>();

    public void threadSafePush(V val) {
        ListNode<V> newItem = new ListNode<>(val);
        ListNode<V> oldItem = null;
        do {
            oldItem = atomicVal.getVal();
            newItem.next = oldItem;
        } while (!atomicVal.compareAndSet(oldItem, newItem));
    }

    public void push(V val) {
        ListNode<V> old = stack;
        stack = new ListNode<>(val, old);
    }

    public V pop() {
        V val = stack.val;
        stack = stack.next;
        return val;
    }

    private static class CAS<V> {
        private V val;

        synchronized V getVal() {
            return val;
        }

        private synchronized V comapreAndSwap(V expVal, V newVal) {
            V oldVal = val;
            if (expVal == val)
                val = newVal;
            return oldVal;
        }

        private synchronized boolean compareAndSet(V expVal, V newVal) {
            return expVal == comapreAndSwap(expVal, newVal);
        }
    }

    private static class ListNode<V> {
        V val;
        ListNode<V> next;

        ListNode() {
        }

        ListNode(V val) {
            this.val = val;
            this.next = null;
        }

        ListNode(V val, ListNode<V> next) {
            this.val = val;
            this.next = next;
        }
    }
}
