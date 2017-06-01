public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	//Note that force is a vector
	//dx and dy are signed
	//ax = Fx / m
	//ay = Fy / m
	//|F| = |F1| = |F2|
	public Planet(double xP, double yP, double xV,double yV, double m, String img)
	{
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}
	
	public Planet(Planet p) 
	{
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	
	public double calcDistance(Planet p) 
	{
		double res = Math.sqrt(Math.pow((p.xxPos - this.xxPos),2) + Math.pow((p.yyPos - this.yyPos), 2));
		return res;
	}
	
	public double calcForceExertedBy(Planet p)
	{
		double dis = this.calcDistance(p);
		double G = 6.67 * 0.00000000001;
		double res = G * p.mass * this.mass / Math.pow(dis, 2);
		return res;
	}
	
	public double calcForceExertedByX(Planet p)
	{
		double res = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
		return res;
	}
	
	public double calcForceExertedByY(Planet p)
	{
		double res = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
		return res;
	}
	
	public double calcNetForceExertedByX(Planet[] P)
	{
		double res = 0.0;
		for(int i=0; i < P.length; i++) {
			if (this.equals(P[i]))
				continue;
			res += this.calcForceExertedByX(P[i]);
		}
		return res;
	}
	
	public double calcNetForceExertedByY(Planet[] P)
	{
		double res = 0.0;
		for(int i=0; i < P.length; i++) {
			if (this.equals(P[i]))
				continue;
			res += this.calcForceExertedByY(P[i]);
		}
		return res;
	}
	
	public void update(double t,double forceX, double forceY)
	{
		this.xxVel += forceX / this.mass * t;
		this.yyVel += forceY / this.mass * t;
		this.xxPos += this.xxVel * t;
		this.yyPos += this.yyVel * t;
	}
	
	
	public void draw()
	{
		StdDraw.picture(this.xxPos, this.yyPos, "\\\\images\\\\" + this.imgFileName);
	}
}
