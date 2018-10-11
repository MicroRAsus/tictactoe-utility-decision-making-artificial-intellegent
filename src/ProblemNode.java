import java.util.ArrayList;
import java.util.Arrays;

public class ProblemNode {
	private final static int TERMINATING_DEPTH = 9;
	public final static State AI_State = State.O;
	public final static State HUMAN_State = State.X;
	public int currentDepth;
	public State[] board = new State[TERMINATING_DEPTH];
	public State winner;
	
	public ProblemNode() {
		currentDepth = 0;
		Arrays.fill(this.board, State.unset);
		winner = State.unset;
	}
	
	public ProblemNode(ProblemNode node) {
		this.currentDepth = node.currentDepth;
		System.arraycopy(node.board, 0, this.board, 0, TERMINATING_DEPTH);
		winner = node.winner;
	}
	
	public static ArrayList<ProblemNode> getNextPossibleStates(ProblemNode problem, State player) {
		ArrayList<ProblemNode> possibleStates = new ArrayList<ProblemNode>();
		int filledSpot = problem.currentDepth;
		for(int i = 0; i < TERMINATING_DEPTH && filledSpot < TERMINATING_DEPTH; i++) {
			if(problem.board[i] == State.unset) {
				ProblemNode nextState = new ProblemNode(problem);
				nextState.board[i] = player;
				nextState.currentDepth++;
				possibleStates.add(nextState);
				filledSpot++;
			}
		}
		return possibleStates;
	}
	
	public boolean isTerminatingNode() {
		this.winner = this.findWinner();
		return currentDepth == TERMINATING_DEPTH || this.winner != State.unset;
	}
	
	private char stateToChar(int index) {
		if(this.board[index] == State.O) {
			return 'O';
		} else if(this.board[index] == State.X) {
			return 'X';
		}
		
		return (char)(index + '0');
	}
	
	public void printBoard() {
		System.out.println("+---+---+---+");
		for(int i = 0; i < TERMINATING_DEPTH; i+=3) {
			System.out.println(String.format("| %c | %c | %c |", this.stateToChar(i), this.stateToChar(i+1), this.stateToChar(i+2)));
			System.out.println("+---+---+---+");
		}
	}
	
	private State findWinner() {
		if(this.board[6] == this.board[7] && this.board[7] == this.board[8]) {
			return this.board[6];
		} else if(this.board[1] == this.board[4] && this.board[4] == this.board[7]) {
			return this.board[1];
		} else if(this.board[2] == this.board[5] && this.board[5] == this.board[8] || this.board[2] == this.board[4] && this.board[4] == this.board[6]) {
			return this.board[2];
		} else if(this.board[3] == this.board[4] && this.board[4] == this.board[5]) {
			return this.board[3];
		} else if(this.board[0] == this.board[1] && this.board[1] == this.board[2] || this.board[0] == this.board[4] && this.board[4] == this.board[8] || this.board[0] == this.board[3] && this.board[3] == this.board[6]) {
			return this.board[0];
		}
		return State.unset;
	}
	
	public int evaluateUtility() { //utility of O, AI player.
		if(this.winner == State.X) {
			return -1;
		} else if(this.winner == State.O) {
			return 1;
		}
		return 0;
	}
	
	public boolean updateBoard(int move) {
		if(this.board[move] == State.unset) {
			this.board[move] = HUMAN_State;
			this.currentDepth++;
			return true;
		}
		return false;
	}
}
