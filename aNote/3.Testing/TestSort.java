import org.junit.Test;

public class TestSort {
    /**
     * Tests the sort method of the Sort class
     */
    @Test
    public void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);
//        for (int i = 0; i < input.length; i++) {
//            if (!input[i].equals(expected[i])) {
//                System.out.println("Mismatch in position " + i + ", expected: " + expected + ", but got: " + input[i] + ".");
//                break;
//            }
//        }
        org.junit.Assert.assertArrayEquals(expected, input);
    }

    @org.junit.Test
    public void testFindSmallest() {
        String[] input = {"i", "have", "an", "egg"};
//        String expected = "an";
        int expected = 2;

        int actual = Sort.findSmallest(input, 0);
        org.junit.Assert.assertEquals(expected, actual);

        String[] input2 = {"there", "are", "many", "pigs"};
//        String expected2 = "are";
        int expected2 = 1;

        int actual2 = Sort.findSmallest(input2, 0);
        org.junit.Assert.assertEquals(expected2, actual2);
    }

    @org.junit.Test
    public void testSwap() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};

        Sort.swap(input, a, b);
        org.junit.Assert.assertArrayEquals(expected, input);
    }

//    public static void main(String[] args) {
//        testSort();
//        testFindSmallest(); // note: we changed this from testSort!
//        testSwap();
//    }
}
