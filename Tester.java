import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Tester {
	public Tester() {
		CreateMap cp = new CreateMap(100,100);
		ArrayList<Person> persons = new ArrayList<>();
		try {
		      File myObj = new File("outputWithResponder.dat");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		for(int i = 0; i < 10; i++) {
			Point position = new Point();
			position.x = (int)(Math.random() * cp.getWidth());
			position.y = (int)(Math.random() * cp.getHeight());
			int x = 1, y = 1;
			while (!(cp.getMapsArray()[position.x][position.y].isPath())) {
				position.x += x;
				position.y += y;
				if (position.x < 0 || position.x > cp.getWidth()) {
					x = -1 * x;
					position.x += x;
				} else if (position.y < 0 || position.y > cp.getHeight()) {
					y = -1 * y;
					position.y += y;
				}
			}
			persons.add(new Civilian(position, 1));
			System.out.println( i + "person added");
		}
		persons.add(new responder(new Point(0, 0), 1, 0, 0, 50, 50));
		persons.add(new responder(new Point(100, 0), 1, 51, 0, 50, 50));
		persons.add(new responder(new Point(0, 100), 1, 0, 51, 50, 50));
		persons.add(new responder(new Point(100, 100), 1, 51, 51, 50, 50));
		try {
		      FileWriter myWriter = new FileWriter("outputWithResponder.dat");
		      myWriter.write("4\n");
		      myWriter.write(persons.size() + "\n");
		      myWriter.write("300\n");
		      myWriter.write("100 100\n");
		      myWriter.write("35 47 56 32 45 33 53 41 47 33 53 41 47 33\n");
		      myWriter.write("20 20 20 10 50 50 25 10 5 10 25 10 5 10\n");
		      for(Person p : persons) {
		    	  cp = new CreateMap(100,100);
					p.pathFinding(cp);
					 for (int i = 0; i < 100; i++) {
						 myWriter.write(p.getPosition().x + " " + p.getPosition().y + " ");
						 p.nextStep();
					 }
					 cp.genterateDisater(20, 50, 50);
					 p.pathFinding(cp);
					 for (int i = 0; i < 100; i++) {
						 myWriter.write(p.getPosition().x + " " + p.getPosition().y + " ");
						 p.nextStep();
					 }
					 cp.clearDisater();
					 p.pathFinding(cp);
					 for (int i = 0; i < 100; i++) {
						 myWriter.write(p.getPosition().x + " " + p.getPosition().y + " ");
						 p.nextStep();
					 }
					 myWriter.write("\n");
					
			  }
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
			
	}
	public static void main(String[] args) {
		new Tester();

	}

}
