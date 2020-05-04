import java.awt.Point;

public abstract class Person{
	private int speed, vspeed;
	private Point position;
	private boolean isVehicle = false;

	public Person(Point position) {
		this.setPosition(position);
	}
	
	
	public void setSpeeds(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}

	public int getVspeed() {
		return vspeed;
	}

	public void setVehicle(int speed) {
		this.vspeed = speed;
	}
	
	public Point getPosition() {
		return position;
	}


	public void setPosition(Point position) {
		this.position = position;
	}
	
	abstract void pathFinding(CreateMap cp);
	abstract Point nextStep();			
}