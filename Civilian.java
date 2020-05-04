import java.awt.Point;


public class Civilian extends Person{
	private Point destination = new Point();
	private AStar as = new AStar(0, 0);
	private CreateMap cp;
	public Civilian(Point position, int speed) {
		super(position);
		super.setSpeeds(speed);
	}

	@Override
	void pathFinding(CreateMap cp) {
		this.cp = cp;
		if(cp.isEmergency()) {
		
			if (cp.getMapsArray()[super.getPosition().x][super.getPosition().y].isRiskArea()) {
				int x = super.getPosition().x - cp.getCenter().x;
				int y = super.getPosition().y - cp.getCenter().y;
				destination.x = super.getPosition().x;
				destination.y = super.getPosition().y;
				while (true) {
					destination.x += x;
					destination.y += y;
					if (destination.x < 0 || destination.x > cp.getWidth()) {
						x = -1 * x;
					} else if (destination.y < 0 || destination.y > cp.getHeight()) {
						y = -1 * y;
					} else if (!(cp.getMapsArray()[destination.x][destination.y].isRiskArea())) {
						break;
					}
					
				}
			} else {
				destination.x = (int)(Math.random() * cp.getWidth());
				destination.y = (int)(Math.random() * cp.getHeight());
			}

		} else {
			destination.x = (int)(Math.random() * cp.getWidth());
			destination.y = (int)(Math.random() * cp.getHeight());
		}
		int x = 1, y = 1;
		while (!(cp.getMapsArray()[destination.x][destination.y].isPath()) ) {
			destination.x += x;
			destination.y += y;
			if (destination.x < 0 || destination.x > cp.getWidth()) {
				x = -1 * x;
				destination.x += x;
			} else if (destination.y < 0 || destination.y > cp.getHeight()) {
				y = -1 * y;
				destination.y += y;
			}
		}

		System.out.println(destination.x+" "+ destination.y);
		as = new AStar(super.getPosition().x, super.getPosition().y, 
				destination.x, destination.y, cp.getMapsArray());
		
	}

	@Override
	Point nextStep() {
		if (as.storage.isEmpty()) {
			pathFinding(cp);
		} 

		super.setPosition(as.storage.pop());

		return super.getPosition();
	}

}
