import java.awt.Point;

public class responder extends Person{

	private Point destination = new Point();
	private AStar as = new AStar(0, 0);
	private CreateMap cp;
	private int x, y, xl, yl;
	private boolean isInner;
	private boolean isStay = false;
	public responder(Point position, int speed) {
		super(position);
		super.setSpeeds(speed);
	}
	
	public responder(Point position, int speed, int x, int y, int xl, int yl) {
		this(position, speed);
		this.x = x;
		this.y = y;
		this.xl = xl;
		this.yl = yl;
		super.setSpeeds(speed);
	}

	@Override
	void pathFinding(CreateMap cp) {
		this.cp = cp;
		if(cp.isEmergency()) {
			double xs = super.getPosition().x - cp.getCenter().x;
			double ys = super.getPosition().y - cp.getCenter().y;
			if (Math.abs(xs) > Math.abs(ys)) {
				xs =  xs/Math.abs(xs);
				ys =  ys/Math.abs(xs);
			} else {
				xs =  xs/Math.abs(ys);
				ys =  ys/Math.abs(ys);
			}
			System.out.println("xs=" + xs + " ys=" + ys);
			double xc = super.getPosition().x;
			double yc = super.getPosition().y;
			if (cp.getMapsArray()[super.getPosition().x][super.getPosition().y].isRiskArea()) {
				isInner = true;
				while (cp.getMapsArray()[(int)xc][(int)yc].isRiskArea()) {
					xc += xs;
					yc += ys;			
				}
			} else {	
				isInner = false;
				xc = cp.getCenter().x;
				yc = cp.getCenter().y;
			} 
			
			destination.x = (int)xc;
			destination.y = (int)yc;
		} else {
			destination.x = (int)(Math.random() * (xl-1)) + x;
			destination.y = (int)(Math.random() * (yl-1)) + y;
		}
		int xs = 1, ys = 1;
		while (!(cp.getMapsArray()[destination.x][destination.y].isPath())) {
			destination.x += xs;
			destination.y += ys;
			if (destination.x < 0 || destination.x > cp.getWidth()) {
				x = -1 * xs;
				destination.x += xs;
			} else if (destination.y < 0 || destination.y > cp.getHeight()) {
				y = -1 * ys;
				destination.y += ys;
			}
		}
		
		as = new AStar(super.getPosition().x, super.getPosition().y, 
				destination.x, destination.y, cp.getMapsArray());
		
	}

	@Override
	Point nextStep() {	 
		if (cp.isEmergency()) {
			if (isStay) {
				return super.getPosition();
			}
			if (!isInner) {
				super.setPosition(as.storage.pop());
				if (cp.getMapsArray()[super.getPosition().x][super.getPosition().y].isRiskArea()){
					isStay = true;
				}} 
			else if (as.storage.isEmpty()) {
				isStay = true;			
			} else {
				super.setPosition(as.storage.pop());
			}		
			return super.getPosition();
		} else if (as.storage.isEmpty()) {
			pathFinding(cp);
		}
		super.setPosition(as.storage.pop());
		isStay = false;
		return super.getPosition();
	}

}
