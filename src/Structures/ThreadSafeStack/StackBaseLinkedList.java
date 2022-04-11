package Structures.ThreadSafeStack;

public class StackBaseLinkedList<V> {
    private ListNode<V> stack = null;
    private final CAS<ListNode<V>> atomicVal = new CAS<>();
    private int size = 0;

    public ListNode<V> getStack() {
        return stack;
    }

    public void threadSafePush(V val) {
        ListNode<V> newItem = new ListNode<>(val);
        ListNode<V> oldItem = null;
        do {
            oldItem = atomicVal.getVal();
            newItem.next = oldItem;
        } while (!atomicVal.compareAndSet(oldItem, newItem));
    }

    public V threadSafePop() {
        ListNode<V> oldItem = null;
        ListNode<V> newItem = null;
        do {
            oldItem = atomicVal.getVal();
            if (oldItem == null) return null;
            newItem = oldItem.next;
        } while(!atomicVal.compareAndSet(oldItem, newItem));
        return atomicVal.getVal() != null ? atomicVal.getVal().val : null;
    }

    // не потокобезопасная операция добавления элемента в стек
    // в результате элемент(ы) может быть НЕ добавлен в стек
    public void push(V val) {
        ListNode<V> old = stack;
        stack = new ListNode<>(val, old);
        size += 1;
    }


    // не потокобезопасная операция извлечения элемента из стека
    // в результате в стеке может остаться элемент
    public V pop() {
        if (stack == null) return null;
        V val = stack.val;
        stack = stack.next;
        size -= 1;
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
        private V val;
        private ListNode<V> next;

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
