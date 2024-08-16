public class SLList {
    public class IntNode{
        public int item;
        public IntNode next;
        public IntNode(int i , IntNode n){
            item = i;
            next = n;
        }
    }

    private IntNode first;
    private int size;

    public SLList(int x){
        first = new IntNode(x, first);
        size = 1;
    }

    public void addFirst(int x){
        first = new IntNode(x, first);
        size += 1;
    }

    public int getFirst(){
        return first.item;
    }

    public int size(){
        return size;
    }

    /** fixed addLast     */
    public void addLast(int x){
        if (first == null){
            first = new IntNode(x, first, first);
        }else{
            IntNode p = first;
            while(p.next != null){
                p= p.next;
            }

            p.next = new IntNode(x, null);
        }
        size += 1;
    }

    public static void main(String[] args){
        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(20);
        System.out.println(L.size());
    }
    
}
