//Matthew Paul and Isaiah Jeter
import java.util.ArrayList;

public class HanoiSolver {
	
	private int numDisks;
	private ArrayList<HanoiMove> movelist;
	
	public HanoiSolver(int numDisks) {
		this.numDisks = numDisks;
		this.movelist = new ArrayList<HanoiMove>();
	}

	// Returns an arraylist of HanoiMoves that specify how to solve the Towers of Hanoi problem.
	// A pyramid of numDisks disks starts on peg 0.  The goal is to move all of them to peg 1, obeying
	// the rule that we move one disk at a time, and that you may not place a larger disk on top
	// of a smaller disk.
	public ArrayList<HanoiMove> solveHanoi() {
		solveHanoiHelper (numDisks, 0, 1);
		return movelist;
	}
	
	// Returns an arraylist of HanoiMoves that specify how to solve the Towers of Hanoi problem
	// for a pyramid of n disks, starting on the source peg, and ending on the destination peg.
	private void solveHanoiHelper(int n, int source, int destination) {
		if (n == 1) {
			movelist.add (new HanoiMove (source, destination));
		}
		else {
			solveHanoiHelper (n-1, source, freePeg (source, destination));
			movelist.add (new HanoiMove (source, destination));
			solveHanoiHelper (n-1, freePeg (source, destination), destination);
		}
	}
	private int freePeg (int source, int destination) {
		if ((source == 0 && destination == 1) || (source == 1 && destination == 0)) {
			return 2;
		}
		else if ((source == 0 && destination == 2) || (source == 2 && destination == 0)) {
			return 1;
		}
		else  {
			return 0;
		}
	}
}
