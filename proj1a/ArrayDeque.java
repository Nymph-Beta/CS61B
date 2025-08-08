public class ArrayDeque<T> {
    private T[] items;
    private int size;

    private int head;
    private int tail;

    private static final int INITIAL_CAPACITY = 8;
    private static final int RESIZE_FACTOR = 2;
    private static final double MIN_USAGE_RATIO = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        head = 0;
        tail = 0;
    }

    /**
     *
     * @param index
     * @return
     */
    private int getNext(int index) {
        return (index + 1) % items.length;
    }

    /**
     * 计算给定索引在循环数组中的上一个位置。
     * @param index
     * @return
     */
    private int getPrev(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /**
     *
     * @param capacity
     */
    private void resize(int capacity) {
        T[] newitems = (T[]) new Object[capacity];

//        int cur = head;
//        for(int i = 0; i < size; i++){
//            newitems[i] = items[cur];
//            cur = getNext(head);
//        }
//
//        head = 0;
//        tail = size;
        if (head < tail) {
            System.arraycopy(items, head, newitems, 0, size);
        } else if (size > 0) {
            int rightpartLength = items.length - head;
            System.arraycopy(items, head, newitems, 0, rightpartLength);

            int leftpartLength = tail;
            System.arraycopy(items, 0, newitems, rightpartLength, leftpartLength);
        }
        items = newitems;
        head = 0;
        tail = size;
    }

    private void checkAndGrow() {
        if (size == items.length) {
            resize(items.length * RESIZE_FACTOR);
        }
    }

    private void checkAndShrink() {
//        if ((double) size / items.length < MIN_USAGE_RATIO) {
        if (items.length >= 16 && (double) size / items.length < MIN_USAGE_RATIO) {
            resize(items.length / RESIZE_FACTOR);
        }
    }

    public void addFirst(T item) {
//        if(size == items.length){
//            resize(items.length * RESIZE_FACTOR);
//        }
        checkAndGrow();

//        items[head] = item;
//        head = getPrev(head);
        head = getPrev(head);
        items[head] = item;
        size++;
    }

    public void addLast(T item) {
//        if(size == items.length){
//            resize(items.length * RESIZE_FACTOR);
//        }
        checkAndGrow();

//        tail = getNext(tail);
//        items[tail] = item;
        items[tail] = item;
        tail = getNext(tail);
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[head];
        items[head] = null;
        head = getNext(head);
        size--;
//        return item;

//        if((double) size / items.length < MIN_USAGE_RATIO){
//            resize(items.length / RESIZE_FACTOR);
//        }
        checkAndShrink();
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        int index = getPrev(tail);
        T item = items[index];
        items[index] = null;
        tail = index;
        size--;

//        if((double) size / items.length < MIN_USAGE_RATIO){
//            resize(items.length / RESIZE_FACTOR);
//        }
        checkAndShrink();
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

//        int physicalIndex = (head + index) % size;
        int physicalIndex = (head + index) % items.length;
        return items[physicalIndex];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }

        System.out.println();
    }

    /*
    public ArrayDeque(ArrayDeque<T> other){
        int capacity = Math.max(INITIAL_CAPACITY, other.size);

//        this.items = (T[]) new Object[INITIAL_CAPACITY];
        this.items = (T[]) new Object[capacity];
        this.size = other.size;

        if(other.head < other.tail){
            System.arraycopy(other.items, other.head, this.items, 0, other.size);
        } else if (other.size > 0) {
            int rightpartLength = other.items.length - other.head;
            System.arraycopy(other.items, other.head, this.items, 0, rightpartLength);

            int leftpartLength = other.tail;
            System.arraycopy(other.items, 0, this.items, rightpartLength, leftpartLength);
        }
        head = 0;
        tail = size;
    }
     */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size()];
        size = 0;

        for(int i = 0; i < other.size(); i++) {
            addLast((T)other.get(i));
        }
    }
}

