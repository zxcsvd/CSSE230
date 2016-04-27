import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class Location {
	public Coordinate c;
	public Hashtable<String, Location> nearByLocation = new Hashtable<String, Location>();
	public String name;
	public boolean isCross = false;
	public Location(Coordinate c, String name, boolean isCross, double rate, double time){
		this.c = c;
		this.name = name;
		this.isCross = isCross;
		this.time = time;
		this.rate = rate;
	}
	public int getX(){
		return this.c.getX();
	}
	public int getY(){
		return this.c.getY();
	}
	
	public String getName(){
		return this.name;
	}
	@Override
	public String toString(){
		return this.name;
	}
	public Coordinate getCoordinate(){
		return this.c;
	}
	
	public double getTime(){
		return this.time;
	}
	
	public double getRate(){
		return this.rate;
	}
	
	public void setNearByLocation(ArrayList<Location> listOfNearByLocations){
		for  (Location tempLocation : listOfNearByLocations){
			String tempString = tempLocation.getName();
			nearByLocation.put(tempString, tempLocation);
		}
	}
	
	public void setNearByLocation(Location l){
			String tempString = l.getName();
			nearByLocation.put(tempString, l);
	
	}
	
	public double time;
	public double rate;
	public double rateTotal;
	private double guess;
	private double total;
    public Location father;
    public Location tempLink;
    private double real;
    public double timeTotal;
	public double distance;

    public void calcguess(Location destination) {
    	int temx = destination.c.x;
    	int temy = destination.c.y;
    	int rx   = this.c.x;
    	int ry   = this.c.y;
    	
        this.guess = Math.sqrt(Math.pow(Math.abs(temx-rx),2)+Math.pow(Math.abs(temy-ry),2));
    } 

    public double getguess() {
        return guess;
    }
    public void settotal(double a){
    	this.total = a;
    }

	public double gettotal() {
		return this.total;
	}
    
//	public void setAVGRate(double avgrate){
//		this.avgrate = avgrate;
//	}
	
    public void setreal(double a){
        this.real = a;
        }

    public double getreal() {
               return this.real;
          }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
