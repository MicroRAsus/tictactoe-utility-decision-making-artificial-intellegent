import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		ProblemNode game = new ProblemNode();
		Scanner rin = new Scanner(System.in);
		do {
			game.printBoard();
			System.out.println("Your move: ");
			int move = rin.nextInt();
			while(!game.updateBoard(move)) {
				System.out.println("Move invalid. Pick another move: ");
				move = rin.nextInt();
			}
			ArrayList<ProblemNode> nextMovesAI = ProblemNode.getNextPossibleStates(game, ProblemNode.AI_State);
			if(nextMovesAI.size() > 0) {
				int maxIndex = 0;
				int maxUtility = -1;
				for(int i = 0; i < nextMovesAI.size(); i++) {
					int utility = MiniMax.miniMaxSearch(nextMovesAI.get(i), false); //next move search human player's decision (minimize AI). select the highest utility choice as AI'S next move.
					if(utility > maxUtility) {
						maxUtility = utility;
						maxIndex = i;
					}
				}
				game = nextMovesAI.get(maxIndex); //maximize AI utility
			}
		} while(!game.isTerminatingNode());
		
		game.printBoard();
		if(game.winner == State.O) {
			System.out.println("Player O won!");
		} else if(game.winner == State.X) {
			System.out.println("Player X won!");
		} else {
			System.out.println("Tie!");
		}
		rin.close();
	}
}