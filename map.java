
public class map {
	//Properties
	private int x;
	private int y;
	private boolean isPath; 
	private boolean isBarrier;
	private boolean isWater;
	private boolean isGrass;
	private boolean isRiskArea;
	private boolean isRiskCentre;
	public map up;
	public map down;
	public map left;
	public map right;
	public map upLeft;
	public map upRight;
	public map downLeft;
	public map downRight;
	
	//Constructors 
	
	//**empty**
	public map() {
		x = 0;
		y = 0;
		this.isPath = false;
		this.isBarrier = false;
		this.isWater = false;
		this.isGrass = true;
		this.isRiskArea = false;
		this.isRiskCentre = false;
		this.up = null;
		this.down = null;
		this.left = null;
		this.right = null;
		this.upLeft = null;
		this.upRight = null;
		this.downLeft = null;
		this.downRight = null;
	}
	
	//**All properties**
	public map(boolean isPath, boolean isBarrier, boolean isWater, boolean isGrass, boolean isRiskArea,
			boolean isRiskCentre, map up, map down, map left, map right, map upLeft, map upRight, 
			map downLeft, map downRight, int x, int y) {
		this(isPath, isBarrier, isWater, isGrass, isRiskArea, isRiskCentre, x, y);
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.upLeft = upLeft;
		this.upRight = upRight;
		this.downLeft = downLeft;
		this.downRight = downRight;
		
	}
	
	//**Statement & x,y **
	public map(boolean isPath, boolean isBarrier, boolean isWater, boolean isGrass, boolean isRiskArea,
			boolean isRiskCentre, int x, int y) {
		this();
		this.isPath = isPath;
		this.isBarrier = isBarrier;
		this.isWater = isWater;
		this.isGrass = isGrass;
		this.isRiskArea = isRiskArea;
		this.isRiskCentre = isRiskCentre;
		this.x = x;
		this.y = y;
	}
	
	//** x & y **
	public map(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}

	//Getter and Setter
	//**Set all linked node**
	public void setMap(map up, map down, map left, map right, map upLeft, map upRight, 
			map downLeft, map downRight) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.upLeft = upLeft;
		this.upRight = upRight;
		this.downLeft = downLeft;
		this.downRight = downRight;
		
	}
	//**x & y**
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	//**all statement** 
	public boolean isPath() {
		return isPath;
	}
	public void setPath(boolean isPath) {
		this.isPath = isPath;
	}
	public boolean isBarrier() {
		return isBarrier;
	}
	public void setBarrier(boolean isBarrier) {
		this.isBarrier = isBarrier;
	}
	public boolean isWater() {
		return isWater;
	}
	public void setWater(boolean isWater) {
		this.isWater = isWater;
	}
	public boolean isGrass() {
		return isGrass;
	}
	public void setGrass(boolean isGrass) {
		this.isGrass = isGrass;
	}
	public boolean isRiskArea() {
		return isRiskArea;
	}
	public void setRiskArea(boolean isRiskArea) {
		this.isRiskArea = isRiskArea;
	}
	public boolean isRiskCentre() {
		return isRiskCentre;
	}
	public void setRiskCentre(boolean isRiskCentre) {
		this.isRiskCentre = isRiskCentre;
	}
	
	//toString
	@Override
	public String toString() {
		return "map [isPath=" + isPath + ", isBarrier=" + isBarrier + ", isWater=" + isWater + ", isGrass=" + isGrass
				+ ", isRiskArea=" + isRiskArea + ", isRiskCentre=" + isRiskCentre + "]";
	}
}
