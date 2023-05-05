package FocusGame;
import java.util.ArrayList;

public class Stack {
	private ArrayList<Pawn> pawns;
	
	public Stack(ArrayList<Pawn> pawns) {
		this.pawns = pawns;
	}
	
	public Pawn getTop() {
		return(pawns.get(0));
	}
	
	public ArrayList<Pawn> splitStack(int numberOfPawns) {
		ArrayList<Pawn> newStack = new ArrayList<>();
		for (int i = 0; i < numberOfPawns; i++) {
			Pawn pawn = pawns.remove(0);
			newStack.add(pawn);
		}
		return(newStack);
	}
}
