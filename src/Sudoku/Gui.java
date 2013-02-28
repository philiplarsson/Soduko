package Sudoku;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Gui extends JFrame {
	List<JTextField> inputs;
	JPanel sudoku;
	
	
	public Gui() {
		initUI();
	}

	private void initUI() {

		sudoku = new JPanel();
		JPanel buttons = new JPanel();
		
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

		buttons.add(bClear);
		buttons.add(bSolve);

		bSolve.addActionListener(new SolveButtonListener());
		bClear.addActionListener(new ClearButtonListener());

		add(sudoku, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		setTitle("Sudoku");
		setVisible(true);
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	class SolveButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			int[][] field = new int[9][9];
			int j = 0;
			int tmpTal = 0;
			for (int i = 0; i < 9; i++) {
				for (int k = 0; k < 9; k++) {
					JTextField tmp = inputs.get(j);
					if (tmp.getText().trim().isEmpty()) {
						tmpTal = 0;
					} else {
						//tmpTal = Integer.parseInt(tmp.getText());
						try {
							tmpTal = Integer.parseInt(tmp.getText());
						}
						catch (NumberFormatException error) {
							JOptionPane.showMessageDialog(sudoku, "Not number in row " + (i+1) + " col " + (k+1) , 
								    "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
					}
					//tmpTal = Integer.parseInt(tmp.getText());
					field[i][k] = tmpTal;
					j++;
				}
			}
			
			Sudoku s1 = new Sudoku(field);
			if(!s1.solve()){
				JOptionPane.showMessageDialog(sudoku, "Sudoku is unsolvable", 
					    "Error", JOptionPane.ERROR_MESSAGE);
			}

			field = s1.getField();
			j = 0;
			for (int i = 0; i < 9; i++) {
				for (int k = 0; k < 9; k++) {

					JTextField tmp = inputs.get(j);
					tmp.setText(Integer.toString(field[i][k]));
					j++;
				}
			}

		}
	}

	class ClearButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < inputs.size(); i++) {
				JTextField tmp = inputs.get(i);
				tmp.setText("");
			}
		}
	}

	public static void main(String[] args) {
		Gui g1 = new Gui();
	}

}
