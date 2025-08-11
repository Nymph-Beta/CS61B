public interface Deque<T> {
    /** 在deque的前端添加一个元素 */
    void addFirst(T item);

    /** 在deque的后端添加一个元素 */
    void addLast(T item);

    /** 如果deque为空返回true，否则返回false */
    default boolean isEmpty() {
        return size() == 0;
    }

    /** 返回deque中元素的数量 */
    int size();

    /** 打印deque中的所有元素，用空格分隔 */
    void printDeque();

    /** 移除并返回deque前端的元素，如果不存在则返回null */
    T removeFirst();

    /** 移除并返回deque后端的元素，如果不存在则返回null */
    T removeLast();

    /** 获取指定索引位置的元素，如果不存在则返回null */
    T get(int index);
}
