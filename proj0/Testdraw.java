public class Testdraw {
    public static void main(String[] args) {
        // 创建一个测试行星对象
        Planet p1 = new Planet(1e12, 2e11, 0, 0, 2e30, "earth.gif");

        // 设置绘图窗口的比例
        StdDraw.setScale(-2e12, 2e12);

        // 绘制背景图像
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        // 绘制行星
        p1.draw();

        // 显示绘图
        StdDraw.show();
    }
}
