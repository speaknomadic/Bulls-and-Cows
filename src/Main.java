import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main {

	private static JTextField text[][] = { { new JTextField(), new JTextField(), new JTextField() },
			{ new JTextField(), new JTextField(), new JTextField() },
			{ new JTextField(), new JTextField(), new JTextField() },
			{ new JTextField(), new JTextField(), new JTextField() },
			{ new JTextField(), new JTextField(), new JTextField() },
			{ new JTextField(), new JTextField(), new JTextField() },
			{ new JTextField(), new JTextField(), new JTextField() },
			{ new JTextField(), new JTextField(), new JTextField() },
			{ new JTextField(), new JTextField(), new JTextField() },
			{ new JTextField(), new JTextField(), new JTextField() }, };

	private static JButton newGame = new JButton("New Game");
	private static JButton endTurn = new JButton("End Turn");

	private static Game game = new Game();

	public static void main(String[] args) {

		JFrame frame = new JFrame("Bulls & Cows");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLayout(new GridLayout(12, 3));

		frame.add(new JLabel("Number"));
		frame.add(new JLabel("Bulls"));
		frame.add(new JLabel("Cows"));

		for (int i = 0; i < text.length; i++) {
			for (int j = 0; j < text[i].length; j++) {
				// if (j == 0) {
				text[i][j].setEditable(false);
				// }
				frame.add(text[i][j]);
			}
		}

		frame.add(newGame);
		frame.add(endTurn);

		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.reset();

				for (int i = 0; i < text.length; i++) {
					for (int j = 0; j < text[i].length; j++) {
						text[i][j].setText("");
					}
				}
				text[game.getTurn()][0].setText(game.guess());
				text[game.getTurn()][1].setEditable(true);
				text[game.getTurn()][2].setEditable(true);

			}
		});

		endTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					int bulls = Integer.valueOf(text[game.getTurn()][1].getText());
					int cows = Integer.valueOf(text[game.getTurn()][2].getText());

					if ((bulls < 0) || (bulls > 4)) {
						JOptionPane.showMessageDialog(null, "Enter a valid value for bulls", "Invalid value",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if ((cows < 0) || (cows > 4)) {
						JOptionPane.showMessageDialog(null, "Enter a valid value for cows", "Invalid value",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					game.evaluate(text[game.getTurn()][0].getText(), bulls, cows);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Enter valid numerical values for bulls and cows", "Invalid value",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				game.nextTurn();
				text[game.getTurn()][0].setText(game.guess());
				text[game.getTurn()][1].setEditable(true);
				text[game.getTurn()][2].setEditable(true);

			}
		});

		// frame.pack();
		frame.setVisible(true);
	}
}
