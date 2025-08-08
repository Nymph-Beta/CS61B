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
        while (cur != sentinel) {
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
     * Gets the item at the given index, where 0 is the front.
     * @param index the index of the item
     * @return the item at the given index, or null if no such item exists
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
     * @param index the index of the item
     * @return the item at the given index, or null if no such item exists
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    /**
     * Helper method for getRecursive.
     * @param cur the current node
     * @param index the remaining index to traverse
     * @return the item at the target index
     */
    private T getRecursiveHelper(Node<T> cur, int index) {
        if (index == 0) {
            return cur.item;
        }
        return getRecursiveHelper(cur.next, index - 1);
    }

    /**
     * Returns the number of items in the deque.
     * @return the size of the deque
     */
    public int size() {
        return size;
    }

    /**
     * Creates a deep copy of other。This constructor is implemented as per the project specification.
     *
     * NOTE: The autograder for this submission phase reports that this constructor
     * should be removed". It is temporarily commented out to pass the API check.
     * This code may need to be re-enabled for later parts of the project
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
    /*
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }
     */
}
