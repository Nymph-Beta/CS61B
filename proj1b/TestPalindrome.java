import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
//    Uncomment this class once you've created your Palindrome class.
    @Test
    public void testisPalindrome() {
        // --- 测试非回文词 ---
        assertFalse("'cat' is not a palindrome", palindrome.isPalindrome("cat"));
        assertFalse("'horse' is not a palindrome", palindrome.isPalindrome("horse"));

        // --- 测试回文词 ---
        assertTrue("'racecar' is a palindrome", palindrome.isPalindrome("racecar")); // 奇数长度
        assertTrue("'noon' is a palindrome", palindrome.isPalindrome("noon"));       // 偶数长度

        // --- 测试边界情况 (Edge Cases) ---
        assertTrue("An empty string should be considered a palindrome", palindrome.isPalindrome(""));
        assertTrue("A single character string should be a palindrome", palindrome.isPalindrome("a"));

        // --- 测试大小写敏感性 ---
        assertFalse("'Racecar' should not be a palindrome due to case sensitivity", palindrome.isPalindrome("Racecar"));
    }

    @Test
    public void testisPalindromeWithCharacterComparator() {
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertFalse(palindrome.isPalindrome("horse", offByOne));
    }
}
