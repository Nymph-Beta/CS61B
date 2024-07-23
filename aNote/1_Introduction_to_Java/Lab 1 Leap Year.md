```
/** Class that determines whether or not a year is a leap year.

 *  @author Yu qingyuan

 */

public class LeapYear {  

    /** Calls isLeapYear to print correct statement.

     *  @param  year to be analyzed

     */ 

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

    /** Must be provided an integer as a command line argument ARGS. */

    public static void main(String[] args) {

        if (args.length < 1) {

            System.out.println("Please enter command line arguments.");

            System.out.println("e.g. java Year 2000");
        }

        for (int i = 0; i < args.length; i++) {
            try {
                int year = Integer.parseInt(args[i]);
                checkLeapYear(year);
            } catch (NumberFormatException e) {

                System.out.printf("%s is not a valid number.\n", args[i]);
            }
        }
    }
}
```

#### args数组
LeapYear.java文件 需要通过命令行参数，来提供年份信息

在主函数中，`main`方法的`args`数组是Java程序的一个入口点，用于接收从命令行传递给程序的参数。这个数组的每个元素都是一个字符串，代表了在命令行中输入的各个参数。这些参数是按照它们在命令行中出现的顺序排列的。

- 如果没有从命令行传递任何参数，`args`数组将会是一个空数组（`args.length`等于0），但它不会是`null`。
- 由于`args`数组的元素是字符串，如果需要将这些参数用作其他类型（如整数），你需要进行适当的类型转换。例如，使用`Integer.parseInt(args[i])`可以将字符串转换为整数。

假设你有一个名为`App.java`的程序，它包含一个`main`方法。你可以这样从命令行启动这个程序，并向它传递一些参数：
`java App arg1 arg2 arg3`

这里，`arg1`、`arg2`、`arg3`是传递给程序的参数，它们会被自动放入`main`方法的`String[] args`数组中，如下所示：
- `args[0]` 将会是 `"arg1"`
- `args[1]` 将会是 `"arg2"`
- `args[2]` 将会是 `"arg3"`



#### `Scanner`类标准输入

可以通过允许通过标准输入来提供所需的参数：
`import java.util.Scanner`
```
while (scanner.hasNextInt()) { // 检查是否有整数输入 
	int year = scanner.nextInt(); // 读取整数输入 
	checkLeapYear(year); // 检查并打印是否为闰年 
	System.out.println("Enter another year to check or press any non-integer key to exit:"); 
}
```