
public class NBody {
	public static String background = "\\images\\starfield.jpg";
	public static Double readRadius(String filename) 
	{
		String filepath = filename;
		In in = new In(filepath);
		Double r = 0.0;
		if(!in.isEmpty()) {
			int n = in.readInt();
			 r = in.readDouble();
		}
		return r;
	}
	
	public static Planet[] readPlanets(String filename)
	{
		In in = new In(filename);
		Planet[] P = null;
		if(!in.isEmpty()) {
			int n = in.readInt();
			Double r = in.readDouble();
			P = new Planet[n];
			for(int i=0; i< n; i++) {
				double xpos = in.readDouble();
				double ypos = in.readDouble();
				double xvel = in.readDouble();
				double yvel = in.readDouble();
				double mass = in.readDouble();
				String imgp = in.readString();
				P[i] = new Planet(xpos, ypos, xvel, yvel, mass, imgp);
			}
		}
		return P;
	}
	
	public static void main(String args[]) 
	{
		if(args.length == 3) {
			double T = Double.parseDouble(args[0]);
			double dt = Double.parseDouble(args[1]);
			String filename = args[2];
			Planet[] P = NBody.readPlanets(filename);
			//Audio
			StdAudio.play(".\\audio\\2001.mid");
			//Draw
			double r = NBody.readRadius(filename);
			StdDraw.setScale(-r, r);
			StdDraw.clear();
			StdDraw.picture(0, 0, background);
			for(int i=0; i<P.length; i++) {
				P[i].draw();
			}
			//Animation
			double time = 0;
			while(time < T) {
				double[] xForces = new double[P.length];
				double[] yForces = new double[P.length];
				for(int i=0; i<P.length; i++) {
					xForces[i] = P[i].calcNetForceExertedByX(P);
					yForces[i] = P[i].calcNetForceExertedByY(P);
				}
				StdDraw.clear();
				StdDraw.picture(0, 0, background);
				for(int i=0; i<P.length; i++) {
					P[i].update(dt, xForces[i], yForces[i]);
					P[i].draw();
				}
				StdDraw.show(10);
				time += dt;
			}
			StdAudio.close();
			StdOut.printf("%d\n", P.length);
			StdOut.printf("%.2e\n", r);
			for (int i = 0; i < P.length; i++) {
				StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
			   		P[i].xxPos, P[i].yyPos, P[i].xxVel, P[i].yyVel, P[i].mass, P[i].imgFileName);	
			}		
			
		} else {
			System.out.print("arguments are needed.");
		}
	}
}
