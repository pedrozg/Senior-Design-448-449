import java.awt.Point;

public class CreateMap {
	private map mapsArray[][];
	private int width;
	private int height;
	private boolean isEmergency = false; 
	private Point center;
	
	public CreateMap(int width, int height) {
		super();
		this.width = width+1;
		this.height = height+1;
		this.mapsArray = new map[this.width][this.height];
		creatMap();
	}
	
	public void creatMap(){ 
		if (width < 3 || height < 3) {
			System.out.println("Inavild map size");
			return;
		}
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if (x%10 == 0) {
					mapsArray[x][y] = new map(true, false, false, false, false, false, x, y);
				}else if (y%10 == 0) {
					mapsArray[x][y] = new map(true, false, false, false, false, false, x, y);
				}else {
					mapsArray[x][y] = new map(x,y);
				}
				  
			}
		}
		mapsArray[0][0].setMap(null, mapsArray[0][1], null, mapsArray[1][0], null, null, null, mapsArray[1][1]);
		
		mapsArray[width - 1][0].setMap(null, mapsArray[width - 1][1], mapsArray[width - 2][0], null, null, 
				null, mapsArray[width - 2][1], null);
		
		mapsArray[0][height - 1].setMap(mapsArray[0][height - 2], null, null, mapsArray[1][height - 1], 
				null, mapsArray[1][height - 3], null, null);
		
		mapsArray[width - 1][height - 1].setMap(mapsArray[width - 1][height - 2], null, mapsArray[width - 2][height - 1], 
				null, mapsArray[width - 2][height - 2], null, null, null);
		
		for(int y = 1; y < height-1; y++) {	
			mapsArray[0][y].setMap(mapsArray[0][y-1], mapsArray[0][y+1], null, mapsArray[1][y], 
					null, mapsArray[1][y-1], null, mapsArray[1][y+1]);
			
			mapsArray[width - 1][y].setMap(mapsArray[width - 1][y-1], mapsArray[width - 1][y+1], mapsArray[width - 1-1][y], 
					null, mapsArray[width - 1-1][y-1], null, mapsArray[width - 1-1][y+1], null);
			  
		}
		
		for(int x = 1; x < width-1; x++) {
			
			mapsArray[x][0].setMap(null, mapsArray[x][1], mapsArray[x-1][0], mapsArray[x+1][0], 
					null, null, mapsArray[x-1][1], mapsArray[x+1][1]);
			
			mapsArray[x][height - 1].setMap(mapsArray[x][height - 1-1], null, mapsArray[x-1][height - 1], 
					mapsArray[x+1][height - 1], mapsArray[x-1][height - 1-1], mapsArray[x+1][height - 1-1], null, null);			
			for(int y = 1; y < height-1; y++) {
				
				mapsArray[x][y].setMap(mapsArray[x][y-1], mapsArray[x][y+1], mapsArray[x-1][y], mapsArray[x+1][y], 
						mapsArray[x-1][y-1], mapsArray[x+1][y-1], mapsArray[x-1][y+1], mapsArray[x+1][y+1]);
				  
			}
		}
		return; 
	}
	
	public void genterateDisater(int size, int x, int y) {
		Point position = new Point();
		size = (size / 2) - 1;
		position.x = x;
		position.y = y;
		System.out.println("Cneter: " + position.toString());
		this.setCenter(position);
		for (int i = position.x - size; i < position.x + size; i++) {
			for (int j = position.y - size; j < position.y + size; j++) {
				this.setRiskPoint(new Point(i, j));
			}
		}
		
	}
	public void clearDisater() {
		this.setEmergency(false);
		mapsArray[center.x][center.y].setRiskCentre(false);
		for (int i = center.x - 10; i < center.x + 10; i++) {
			for (int j = center.y - 10; j < center.y + 10; j++) {
				mapsArray[center.x][center.y].setRiskCentre(false);
			}
		}
		this.center = null;
		
	}

	public map[][] getMapsArray() {

		return mapsArray;
	}

	public void setMapsArray(map[][] mapsArray) {
		this.mapsArray = mapsArray;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isEmergency() {
		return isEmergency;
	}

	public void setEmergency(boolean isEmergency) {
		this.isEmergency = isEmergency;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point position) {
		mapsArray[position.x][position.y].setRiskCentre(true);
		this.center = position;
		this.setEmergency(true);
	}
	
	public void setRiskPoint(Point position) {
		mapsArray[position.x][position.y].setRiskArea(true);
	}
	
}
