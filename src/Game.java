import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	
	private static final Random PRNG = new Random();

	private int turn = 0;
	private boolean isFirstOnTurn;

	private List<String> possibilities = new ArrayList<String>();

	private boolean toKeepIt(String option, String number, int bulls, int cows) {
		int b = 0;
		for (int i = 0; i < option.length() && i < number.length(); i++) {
			if (option.charAt(i) == number.charAt(i)) {
				b++;
			}
		}

		int c = 0;
		for (int k = 0; k < option.length(); k++) {
			for (int l = 0; l < number.length(); l++) {
				if (k == l) {
					continue;
				}

				if (option.charAt(k) == number.charAt(l)) {
					c++;
				}
			}
		}

		if (bulls == b && cows == c) {
			return true;
		} else {
			return false;
		}
	}

	private void allPossible() {
		possibilities.clear();

		for (int number = 1000; number <= 9999; number++) {
			if (isValidGuess("" + number) == false) {
				continue;
			}

			possibilities.add("" + number);
		}
	}

	private boolean isValidGuess(String number) {
		if (number == null) {
			return false;
		}

		if (number.length() != 4) {
			return false;
		}

		for (int a = 0; a < number.length() - 1; a++) {
			for (int b = a + 1; b < number.length(); b++) {
				if (number.charAt(a) == number.charAt(b)) {
					return false;
				}
			}
		}

		return true;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void nextTurn() {
		turn++;
	}

	public void reset() {
		turn = 0;
		allPossible();
	}

	public String guess() {
		// String number = "";
		//
		// do {
		// number = "" + (int) (1000 + Math.random() * 9000);
		// } while (isValidGuess(number) == false);
		int r = PRNG.nextInt(possibilities.size());

		return possibilities.get(r);
	}

	public void evaluate(String number, int bulls, int cows) {
		
		for (int i = possibilities.size() - 1; i >= 0; i--) {
			if (toKeepIt(possibilities.get(i), number, bulls, cows) == false) {
				possibilities.remove(i);
			}
		}
	}
}
