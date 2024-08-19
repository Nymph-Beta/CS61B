public class TestPlanet {
    public static void main(String[] args) {
        Planet p1 = new Planet(1e12, 2e11, 0, 0, 2e30, "jupiter.gif");
        Planet p2 = new Planet(2e12, 3e11, 0, 0, 6e24, "earth.gif");

        double force = p1.calcForceExertedBy(p2);
        System.out.println("The force between p1 and p2 is: " + force);
    }
}
