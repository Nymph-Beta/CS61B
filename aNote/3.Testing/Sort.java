public class Sort {
    /**
     * Sorts strings destructively
     */
    public static void sort(String[] a) {
        // find the smallest item
        // move it to the front
        // selection sort the rest (using recursion?)
//        String smallest = findSmallest(a);
//        int smallestIndex = findSmallest(a);
//        swap(a, 0, smallestIndex);
        sort(a, 0);
    }

    /** Returns the smallest string in x. */
    public static int findSmallest(String[] x, int start) {
//        return x[3];
//        String smallest = x[0];
//        int smallestIndex = start;
//        for (int i = 0; i < x.length; i += 1) {
//            int cmp = x[i].compareTo(x[smallestIndex]);
//            if (cmp < 0) {
//                smallest = x[i];
//                smallestIndex = i;
//            }
//        }
//        return smallestIndex;
        int smallestIndex = start;
        for (int i = start; i < x.length; i += 1) {
            int cmp = x[i].compareTo(x[smallestIndex]);
            if (cmp < 0) {
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    public static void swap(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void sort(String[] x, int start){
        // TODO
        if (start == x.length) {
            return;
        }

        int smallestIndex = findSmallest(x, start);
        swap(x, start, smallestIndex);
        sort(x, start + 1);
    }
}
