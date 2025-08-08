/**
 * 一个使用循环哨兵实现的泛型双端队列。
 * @param <T> 队列中存储的元素类型。
 * */
public class LinkedListDeque<T> {

    /**
     * 内部泛型节点类。
     * @param <T> 节点存储的元素类型。
     */
//    private static class IntNode{
    private static class Node<T> {
        private Node<T>  prev;
        private Node<T>  next;
        private T item;

        //    public IntNode(IntNode p, int i, IntNode n){
        public Node(Node<T> p, T i, Node<T> n) {
            this.prev = p;
            this.next = n;
            this.item = i;
        }
    }

    private Node<T> sentinel;
    private int size;

    /**
     * 创建一个包含单个元素的双端队列。
     * @param x 要添加的初始元素。
     */
    /*
    public LinkedListDeque(T x){
        sentinel = new Node<>(null,null ,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;

        Node<T> newNode = new Node<>(sentinel,x,sentinel);
        sentinel.next = newNode;
        sentinel.prev = newNode;
        size = 1;
    }
     */

    /**
     * 创建一个空的双端队列。
     */
    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * 在队列前端添加一个元素。
     * @param x 要添加的元素。
     */
    public void addFirst(T x) {
        Node<T> newNode = new Node<>(sentinel, x, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    /**
     * 在队列后端添加一个元素。
     * @param x 要添加的元素。
     */
    public void addLast(T x) {
        Node<T> newnode = new Node<>(sentinel.prev, x, sentinel);
        sentinel.prev.next = newnode;
        sentinel.prev = newnode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 打印队列中的所有元素，从头到尾，以空格分隔。
     * 打印完所有元素后换行。
     */
    public void printDeque() {
        Node<T> cur = sentinel.next;
        while (cur != sentinel){
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * 移除并返回双端队列前端的元素。
     * 若不存在该元素，则返回 null。
     */
    public T removeFirst() {
        Node<T> cur = sentinel.next;
        if (cur == sentinel) {
            return null;
        }
        sentinel.next = cur.next;
        cur.next.prev = sentinel;
        size--;
        return cur.item;
    }

    /**
     * 移除并返回双端队列末端的元素。
     * 若不存在该元素，则返回 null。
     */
    public T removeLast() {
        Node<T> cur = sentinel.prev;
        if (cur == sentinel) {
            return null;
        }
//        sentinel.prev = cur.prev.next;
        sentinel.prev = cur.prev;
        cur.prev.next = sentinel;
        size--;
        return cur.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque
     */
    public T get(int index) {
        Node<T> cur = sentinel.next;
        if (index < 0 || index >= size) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.item;
    }

    /**
     * Same as get, but uses recursion.
     * @param index
     * @return
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    /**
     * 私有的递归辅助方法。
     * @param cur
     * @param index
     * @return
     */
    private T getRecursiveHelper(Node<T> cur, int index) {
        if (index == 0) {
            return cur.item;
        }
        return getRecursiveHelper(cur.next, index - 1);
    }

    public int size() {
        return size;
    }

    /**
     * Creates a deep copy of other
     * means that you create an entirely new ArrayDeque, with the exact same items as other.
     * However, they should be different objects,
     * if you change other, the new ArrayDeque you created should not change as well.
     * @param other
     */
    /*
    public LinkedListDeque(LinkedListDeque<T> other){
        sentinel = new Node<>(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        Node<T> p =other.sentinel.next;
        while(p != other.sentinel){
            this.addLast(p.item);
            p = p.next;
        }
    }
     */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node<>(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        for(int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }
}
