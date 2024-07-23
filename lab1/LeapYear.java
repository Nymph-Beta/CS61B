import java.util.Scanner; // 导入Scanner类，用于获取用户输入

public class LeapYear {

    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    private static void checkLeapYear(int year) {
        if (isLeapYear(year)) {
            System.out.printf("%d is a leap year.\n", year);
        } else {
            System.out.printf("%d is not a leap year.\n", year);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象，用于读取用户输入
        System.out.println("Please enter a year to check if it's a leap year:");

        // 使用循环允许多次输入和检查
        while (scanner.hasNextInt()) { // 检查是否有整数输入
            int year = scanner.nextInt(); // 读取整数输入
            checkLeapYear(year); // 检查并打印是否为闰年
            System.out.println("Enter another year to check or press any non-integer key to exit:");
        }

        scanner.close(); // 关闭scanner对象
    }
}
