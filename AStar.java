import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;


public class AStar {
    private final List<Node> open;
    private final List<Node> closed;
    private final List<Node> path;
    private Node current;
    private final int xstart;
    private final int ystart;
    private int xend, yend;
    private Stack storage= new Stack(50);
    
    // Node class for convienience
		
    
    
    
    /*
     * 
     * Data Structure: Stack  
     * ���ݽṹ�� Stack
     * ������pop,push,clear,isempty����
     * Stack �ڴ����POINT��������
     * Ĭ��100��С
     * default 100size 
     */
    
    
    
    class Stack{
	    public Point data[];
	    public int index=0;
	    public int xpt;
	    public int ypt;
	    
		Stack(){
		Point[] data= (Point[]) new Object[100];
	}
		
		Stack(int input){
		Point[] data= (Point[]) new Object[input];
			
	}	
	public void push(Point newEntry) {
			// TODO Auto-generated method stub
			if(index<data.length){
				data[index]=newEntry;
				index++;
			}
			else{
				data=Arrays.copyOf(data, data.length*2);
				data[index]=newEntry;
				index++;	
			}
		}
		
	public Point pop() {
			// TODO Auto-generated method stub
			Point get;
			if(index==0)
				throw new EmptyStackException();
			
			if(index!=0){
				get=data[index-1];
				data[index-1]=null;
			index--;
			return get;
			}
		return null;
		}
		
		//
	public Point peek() {
			// TODO Auto-generated method stub
			if(index==0){
				throw new EmptyStackException();
			}
			return data[index-1];	
		}
		
	public boolean isEmpty() {
			// TODO Auto-generated method stub
			return index==0;
		}
		
	public void clear() {
			// TODO Auto-generated method stub
			for(int i=0;i<index;i++){
				data[i]=null;
			}
		}
	}

/* 
 * 
 * End 				
 */
    

    /*
   	* ���ݽṹ:Node
   	* ���ڱ�ʾ·����
    * �洢X,Y������ �Լ�g��h��������
    * previous����֮ǰ��Node����·����
    */
     class Node implements Comparable {
        public Node previous;
        public int x, y;
        public double g;
        public double h;
  
        Node(Node previous, int xpos, int ypos, double g, double h) {
            this.previous = previous;
            this.x = xpos;
            this.y = ypos;
            this.g = g;
            this.h = h;
       }
       // Compare by f value (g + h)
       @Override
       public int compareTo(Object o) {
           Node compare = (Node) o;
           return (int)((this.g + this.h) - (compare.g + compare.h));
       }
       
   }
 
     /*
      * AStar����
      */
    AStar(int xstart, int ystart) {
    	/*
    	 * Open��Close list ����·��ɸѡ
    	 * path����·��
    	 */
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.path = new ArrayList<>();
        this.current = new Node(null, xstart, ystart, 0, 0);
        this.xstart = xstart;
        this.ystart = ystart;
    }
    
	/*
	 * Open��Close list ����·��ɸѡ
	 * path����·��
	 */
    public List<Node> pathfinder(int xend, int yend, map [][]board) {

    	this.xend = xend;
        this.yend = yend;
        this.closed.add(this.current);
        addopen(board);
        while (this.current.x != this.xend || this.current.y != this.yend) {
            if (this.open.isEmpty()) { 
                return null;
            }
            /*
             * ��OPEN��ѡȡ��һ������Ϊɸѡ���OPEN�ĵ�һ��OBJECT��g+h��С��
             */
            this.current = this.open.get(0); 
            this.open.remove(0); 
            this.closed.add(this.current); 
            addopen(board);
        }
        this.path.add(0, this.current);
        while (this.current.x != this.xstart || this.current.y != this.ystart) {
            this.current = this.current.previous;
            this.path.add(0, this.current);
        }
        return this.path;
    }
    /*
     * ��OPEN����CLOSE��list����Ѱ�Ҷ�Ӧ��·����
     */
    boolean listfind(List<Node> array, Node node) {
        return array.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));
    }


    private double distance(int dx, int dy) {
       
            return Math.abs(this.current.x + dx - this.xend)+Math.abs(this.current.y + dy - this.yend); // return hypothenu
    }
    /*
     * ���һ��·�����Ƿ���Ч�����ܱ��ӵ�Node����
     * ��Ҫһ��board����ΪҪ��鵱ǰ·���Ƿ���н�
     */
    private void addopen(map [][]board) {
        Node node;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {

                node = new Node(this.current, this.current.x + x, this.current.y + y, this.current.g, this.distance(x, y));
                if ((x != 0 || y != 0) 
                	&& (x!=y)
                	/*
                	 * x!=y ���ǲ���Ѱ�ҷǴ�ֱ�����·���㣬���������ɾ��
                	 */
                    && this.current.x + x >= 0 && this.current.x + x < 100
                    && this.current.y + y >= 0 && this.current.y + y < 100
                    && board[this.current.x + x][this.current.y + y].isPath()
                    && !listfind(this.open, node) && !listfind(this.closed, node)) { // if not already done
                        node.g = node.previous.g + 1.; 
                        this.open.add(node);
                }
            }
        }
        /*
         * ��open�����·�������g+h��С����
         */
        Collections.sort(this.open);
    }

    /*
     *��PATH�� list �е�����Stackȥ
     */
    
    public Stack loadtostack(List<Node> path) {
		int NodeIndex=0;
		Point insert=new Point(0,0);
		while(NodeIndex<path.size()) {
			insert.setLocation(path.get(NodeIndex).x,path.get(NodeIndex).y);
			storage.push(insert);	
			NodeIndex++;
		}
		
    	return storage;
    }
    
 }
    
/*public static void main(String[] args) {
	// TODO Auto-generated method stub
int [][] board = new int[10][10];
AStar test = new AStar(0,0);
  board[1][0]=-1;
  board[1][1]=-1;
  board[1][2]=-1;
  List<AStar.Node> path = test.pathfinder(6, 2,board);
  path.forEach((n) -> {
        System.out.println(n.x+" "+n.y);
	        });
}
*/



