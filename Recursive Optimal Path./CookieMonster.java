//Matthew Paul and Isaiah Jeter
import java.util.ArrayDeque;
import java.util.PriorityQueue;

/* YOU ARE ALLOWED (AND EXPECTED) TO USE THE JAVA ARRAYDEQUE CLASS */

public class CookieMonster {

    private int [][] cookieGrid;
    private int numRows;
    private int numCols;
    private int mostCookies = -1;
    private int maxCallStackDepth = -1; //Used only for recursive technique

    public CookieMonster(int [][] cookieGrid) {
        this.cookieGrid = cookieGrid;
        this.numRows    = cookieGrid.length;
        this.numCols    = cookieGrid[0].length;
    }

    /* Return the calculated most cookies edible on the optimal path. */
    public int getMostCookies() {
        return mostCookies;
    }

    private boolean goodPoint(int row, int col) {
        return (row >= 0 && row < numRows && col >= 0 && col < numCols && cookieGrid[row][col] >= 0);
    }

	/* RECURSIVELY calculates the route which grants the most cookies, and returns the maximum depth the call stack reached during the operation. */
	public int maxCallStackDepth() {
		this.mostCookies = recursiveOptimalPath(0, 0, 1);
		return maxCallStackDepth;
	}	

	/* Helper function for the above, which returns the maximum number of cookies edible starting at coordinate (row, col). */
	/* From any given position, always check right before checking down */
	/* Also, updates the maxCallStackDepth variable, to find out how deep the recursion call stack got during the operation. 
	 * I've implemented this part already (you should add 1 to the depth when it calls itself). */ 

	private int recursiveOptimalPath(int row, int col, int depth) {
		if (depth > this.maxCallStackDepth) {
			this.maxCallStackDepth = depth;
		}
		if (!goodPoint(row, col)) {
			return 0;
		}
		else {
            int a = recursiveOptimalPath(row, col + 1, depth + 1);
            int b = recursiveOptimalPath(row + 1, col, depth + 1);
			if (goodPoint (row, col + 1) && goodPoint (row + 1, col)) {
				if (a > b) {
					return cookieGrid [row][col] + a;
				}
				else {
					return cookieGrid [row][col] + b;
				}
			}
            else if (goodPoint (row, col + 1) && !goodPoint (row + 1, col)) {
                return cookieGrid [row][col] + a;
            }
			else if (goodPoint (row + 1, col) && !goodPoint (row, col + 1)) {
				return cookieGrid [row][col] + b;
			}
			else {
				return cookieGrid [row][col];
			}
		}
	}
   
	/* Calculate the route which grants the most cookies, and return the maximum queue size during the operation. */
    /* From any given position, always check right before checking down */
    /* Set this.mostCookies before returning */
    public int maxQueueSize() {
        ArrayDeque<PathMarker> queue = new ArrayDeque<PathMarker>();
        int maxQueueSize = 0;
        int mostCookiesSoFar = -1;
        if (cookieGrid[0][0] == -1) {
            this.mostCookies = mostCookiesSoFar;
            return maxQueueSize;
        }
        queue.addFirst(new PathMarker(0, 0, cookieGrid[0][0]));
        maxQueueSize++;

        while(!queue.isEmpty()) {
        	int r = queue.peek().row;
        	int c = queue.peek().col;
        	int t = queue.peek().total;
        	if (t > mostCookiesSoFar){
       			mostCookiesSoFar = t;
       		}
            if (goodPoint (r, c+1)){
                queue.add (new PathMarker (r, c + 1, t + cookieGrid[r][c+1]));
            }
        	if (goodPoint (r+1, c)) {
        		queue.add (new PathMarker (r + 1, c, t + cookieGrid[r+1][c]));
        	}
            if (queue.size () > maxQueueSize) {
                maxQueueSize = queue.size();
            }
        	queue.remove ();
        }
         this.mostCookies = mostCookiesSoFar;
         return maxQueueSize;
    }

    /* Calculate the route which grants the most cookies, and return the maximum stack size during the operation. */
    /* From any given position, always check right before checking down */
    /* Set this.mostCookies before returning */
    public int maxStackSize() {
        ArrayDeque<PathMarker> stack = new ArrayDeque<PathMarker>();
        int maxStackSize = 0;
        int mostCookiesSoFar = -1;
         if (cookieGrid[0][0] == -1) {
            this.mostCookies = mostCookiesSoFar;
            return maxStackSize;
        }
        stack.addFirst(new PathMarker(0, 0, cookieGrid[0][0]));
        maxStackSize++;
        int i = 0;
         while(!stack.isEmpty()) {
            int r = stack.peekLast().row;
            int c = stack.peekLast().col;
            int t = stack.peekLast().total;
            stack.removeLast ();
            if (t > mostCookiesSoFar){
                mostCookiesSoFar = t;
            }
            if (goodPoint (r, c+1)){
                stack.add (new PathMarker (r, c + 1, t + cookieGrid[r][c+1]));
            }
            if (goodPoint (r+1, c)) {
                stack.add (new PathMarker (r + 1, c, t + cookieGrid[r+1][c]));
            }
            if (stack.size () > maxStackSize) {
                maxStackSize = stack.size();
            }

        }
         this.mostCookies = mostCookiesSoFar;
         return maxStackSize;
    }
}
