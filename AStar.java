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
    private int timer;
    public Stack<Point> storage= new Stack<>(50);
    
    // Node class for convienience
		
    /*
     * Constructor
     * AStar正文
     */
   AStar(int xstart, int ystart) {
   	/*
   	 * Open和Close list 用作路径筛选
   	 * path存贮路径
   	 */
       this.open = new ArrayList<>();
       this.closed = new ArrayList<>();
       this.path = new ArrayList<>();
       this.current = new Node(null, xstart, ystart, 0, 0);
       this.xstart = xstart;
       this.ystart = ystart;
   }
   AStar(int xstart, int ystart, int xend, int yend, map [][]board) {
	   	/*
	   	 * Open和Close list 用作路径筛选
	   	 * path存贮路径
	   	 */
	       this.open = new ArrayList<>();
	       this.closed = new ArrayList<>();
	       this.path = new ArrayList<>();
	       this.current = new Node(null, xstart, ystart, 0, 0);
	       this.xstart = xstart;
	       this.ystart = ystart;
	       loadtostack(pathfinder(xend, yend, board));
	
	       
	}
	   
    
    
    /*
     * 
     * Data Structure: Stack  
     * 数据结构： Stack
     * 基本的pop,push,clear,isempty功能
     * Stack 内存的是POINT数据类型
     * 默认100大小
     * default 100size 
     */
    
    
    
    class Stack<T>{
	    public T data[];
	    public int index=0;
	    public int xpt;
	    public int ypt;
	    
		Stack(){
		data=(T[])new Object[100];
	}
		
		Stack(int input){
		data= (T[]) new Object[input];		
	}	
	public void push(T newEntry) {
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
		
	public T pop() {
			// TODO Auto-generated method stub
			T get;

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
   	* 数据结构:Node
   	* 用于表示路径点
    * 存储X,Y坐标轴 以及g和h用来评估
    * previous连接之前的Node或者路径点
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
	 * Open和Close list 用作路径筛选
	 * path存贮路径
	 */
    public List<Node> pathfinder(int xend, int yend, map [][]board) {
    	System.out.println("find path now (" + xend + " "+yend);
    	this.xend = xend;
        this.yend = yend;
        this.closed.add(this.current);
        addopen(board);
 
        while (this.current.x != this.xend || this.current.y != this.yend) {
            if (this.open.isEmpty()) { 
                return this.path;
            }
            /*
             * 从OPEN中选取第一个，因为筛选后的OPEN的第一个OBJECT是g+h最小的
             */
            this.current = this.open.get(0); 
            this.open.remove(0); 
            this.closed.add(this.current); 
            addopen(board);
        }

        while (this.current.x != this.xstart || this.current.y != this.ystart) {
  
        	this.current = this.current.previous;
            this.path.add(0, this.current);
        }
        return this.path;
    }
    /*
     * 在OPEN或者CLOSE的list里面寻找对应的路径点
     */
    boolean listfind(List<Node> array, Node node) {
        return array.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));
    }


    private double distance(int dx, int dy) {
       
            return Math.abs(this.current.x + dx - this.xend)+Math.abs(this.current.y + dy - this.yend); // return hypothenu
    }
    /*
     * 检查一个路径点是否有效，并能被加到Node点来
     * 需要一个board是因为要检查当前路径是否可行进
     */
    private void addopen(map [][]board) {
        Node node;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {

                node = new Node(this.current, this.current.x + x, this.current.y + y, this.current.g, this.distance(x, y));
                if ((x != 0 || y != 0) 
                	&& (x!=y)
                	/*
                	 * x!=y 就是不会寻找非垂直方向的路径点，根据情况可删除
                	 */
                    && this.current.x + x >= 0 && this.current.x + x < 101
                    && this.current.y + y >= 0 && this.current.y + y < 101
                    && board[this.current.x + x][this.current.y + y].isPath()
                    && !board[this.current.x + x][this.current.y + y].isRiskArea()
                    && !listfind(this.open, node) && !listfind(this.closed, node)) { // if not already done
                        node.g = node.previous.g + 1.; 
                        this.open.add(node);
                }
            }
        }
        /*
         * 将open里面的路径点根据g+h大小排序
         */
        Collections.sort(this.open);
    }

    /*
     *将PATH从 list 中导出到Stack去
     */
    
    public Stack loadtostack(List<Node> path) {
		 System.out.println("Loading the stack : "+path.size());
    	int NodeIndex=path.size()-1;
		while(NodeIndex>=0) {
			storage.push(new Point(path.get(NodeIndex).x,path.get(NodeIndex).y));	
			NodeIndex--;
		}
		System.out.println("storage size: " + storage.isEmpty());
    	return storage;
    } 
    
    public List<Node> getPathList(){
    	return this.path;
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



