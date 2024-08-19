public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p){
        double G = 6.67e-11;
        double r = this.calcDistance(p);
        return G * this.mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p){
        double F = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        return F * dx / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        double F = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        return F * dy / this.calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double netForceX = 0;
        for (Planet p : allPlanets){
            if (!this.equals(p)){
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double netForceY = 0;
        for (Planet p : allPlanets){
            if (!this.equals(p)){
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
