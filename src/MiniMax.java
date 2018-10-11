
public class MiniMax {
	public static int miniMaxSearch(ProblemNode problem, boolean maximizingAI) {
		if(problem.isTerminatingNode()) {
			return problem.evaluateUtility();
		}
		if (maximizingAI) {
			int value = -1;
			for(ProblemNode child : ProblemNode.getNextPossibleStates(problem, ProblemNode.AI_State)) {
				value = Math.max(value, miniMaxSearch(child, false));
			}
	        return value;
		} else { // minimize AI or maximize the other side, human player. human player's decision
			int value = 1;
			for (ProblemNode child : ProblemNode.getNextPossibleStates(problem, ProblemNode.HUMAN_State)) {
				value = Math.min(value, miniMaxSearch(child, true));
			}
			return value;
		}
	}
}
