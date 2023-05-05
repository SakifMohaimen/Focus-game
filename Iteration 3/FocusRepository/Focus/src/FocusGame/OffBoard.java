package FocusGame;

public class OffBoard {
	private int[] reserve = {0,0,0,0};
	private int[] captured = {0,0,0,0};
	
	public OffBoard(int[] reserve, int[] captured) {
		this.reserve = reserve;
		this.captured = captured;
	}
	
	public void addToReserve(int player) {
		reserve[player]++;
	}
	
	public void removeFromReserve(int player) {
		reserve[player]--;
	}
	
	public void addToCaptured(int player) {
		captured[player]++;
	}
}
