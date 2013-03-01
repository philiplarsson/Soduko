package Sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Gui extends JFrame {
	private List<JTextField> inputs;
	private JPanel sudoku;
	private JPanel buttons;
	private int field[][];
	private Sudoku s1;

	/*
	* Creates Gui.
	*/
	public Gui() {
		initUI();
	}

	/*
	* Initializes the user interface.
	*/
	private void initUI() {
		
		sudoku = new JPanel();
		buttons = new JPanel();

		sudoku.setLayout(new GridLayout(9, 9));
		buttons.setLayout(new FlowLayout());

		inputs = new ArrayList<JTextField>();
		for (int i = 0; i < 81; i++) {
			inputs.add(new JTextField());
		}
		for (int i = 0; i < inputs.size(); i++) {
			sudoku.add(inputs.get(i));
		}

		JButton bClear = new JButton("Clear");
		JButton bSolve = new JButton("Solve");

		bClear.setToolTipText("Clears the entire soduko");
		bSolve.setToolTipText("Solves the soduko");
		
		bClear.setMnemonic(KeyEvent.VK_C);
		bSolve.setMnemonic(KeyEvent.VK_S);
		
		buttons.add(bClear);
		buttons.add(bSolve);

		bSolve.addActionListener(new SolveButtonListener());
		bClear.addActionListener(new ClearButtonListener());

		add(sudoku, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);

		colorMiniSudoku();
		setTitle("Sudoku");
		setVisible(true);
		setSize(500, 500);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void colorMiniSudoku() {
		// to be done?
	}

	class SolveButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (!loadNbrsFromGui()) {
				return;
			}
			s1 = new Sudoku(field);
			if(!checkIfOk()){
				return;
			}
			solveSudoku();
			showNbrsInGui();

		}
		
		/*
		* Returns true if number input by user is valid.
		* Else returns false.
		*/
		private boolean checkIfOk() { 
			if(!s1.checkIfValuesOk()){
				JOptionPane.showMessageDialog(sudoku, "Value error",
						"Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		}

		/*
		* Comment needed.
		*/
		private void solveSudoku() {
			if (!s1.solve()) {
				JOptionPane.showMessageDialog(sudoku, "Sudoku is unsolvable",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		/*
		* Comment needed.
		*/
		private void showNbrsInGui() {
			field = s1.getField();
			int j = 0;
			for (int i = 0; i < 9; i++) {
				for (int k = 0; k < 9; k++) {

					JTextField tmp = inputs.get(j);
					tmp.setText(Integer.toString(field[i][k]));
					j++;
				}
			}

		}

		/*
		* Returns true if numbers were correctly loaded from the Gui.
		*/
		private boolean loadNbrsFromGui() {
			field = new int[9][9];
			int j = 0;
			int tmpNbr = 0;
			for (int i = 0; i < 9; i++) {
				for (int k = 0; k < 9; k++) {
					JTextField tmp = inputs.get(j);
					if (tmp.getText().trim().isEmpty()) {
						tmpNbr = 0;
					} else {
						try {
							tmpNbr = Integer.parseInt(tmp.getText());
						} catch (NumberFormatException error) {
							tmp.setBackground(Color.RED);
							JOptionPane.showMessageDialog(sudoku,
									"Not number in row " + (i + 1) + " col "
											+ (k + 1), "Error",
									JOptionPane.ERROR_MESSAGE);
							tmp.setBackground(Color.WHITE);
							return false;
						}

					}
					if (tmpNbr < 0 || tmpNbr > 9) {
						tmp.setBackground(Color.RED);
						JOptionPane.showMessageDialog(sudoku, "Wrong number "
								+ (i + 1) + " col " + (k + 1), "Error",
								JOptionPane.ERROR_MESSAGE);
						tmp.setBackground(Color.WHITE);
						return false;
					}
					field[i][k] = tmpNbr;
					j++;
				}
			}
			return true;
		}
	}

	/*
	* Comment needed.
	*/
	class ClearButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < inputs.size(); i++) {
				JTextField tmp = inputs.get(i);
				tmp.setText("");
			}
		}
	}

	/*
	* Starts the application.
	*/
	public static void main(String[] args) {
		Gui g1 = new Gui();
	}

}
