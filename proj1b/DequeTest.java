// 在proj1b目录中创建一个临时测试
public class DequeTest {
    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        Deque<Integer> ad = new ArrayDeque<>();

        // 测试基本功能
        lld.addFirst(1);
        lld.addLast(2);
        System.out.println("LinkedListDeque size: " + lld.size());

        ad.addFirst(1);
        ad.addLast(2);
        System.out.println("ArrayDeque size: " + ad.size());
    }
}
