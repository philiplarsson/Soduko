package Sudoku;

public class Sudoku {
	int[][] field;
	
	/*
	* Creates a new Sudoku field.
	*/
	public Sudoku() {
		field = new int[9][9];
	}
	
	/*
	* Creates a new Sudoku field with the parameter int[][] field.
	*/
	public Sudoku(int[][] field) {
		this.field = field;
	}
	
	/*
	* Solves the Sudoku.
	*/
	public boolean solve() {
		return solve(0, 0);
	}
	
	/*
	* Returns the field.
	*/
	public int[][] getField() {
		return field;
	}
	
	/*
	* Returns true if inserting int n into field[row][column] is allowed.
	* Else returns false.
	*/	
	public boolean checkLegal(int row, int column, int n) {

		int startColumn = (column / 3) * 3;
		int startRow = (row / 3) * 3;
		field[row][column] = 0;

		for (int i = 0; i < 3; i++) {
			startRow = (row / 3) * 3;
			for (int k = 0; k < 3; k++) {
				if (field[startRow][startColumn] == n) {
					field[row][column] = n;
					return false;
				}
				startRow++;
			}
			startColumn++;
		}

		for (int i = 0; i < 9; i++) {
			if (field[row][i] == n) {
				field[row][column] = n;
				return false;
			}
		}

		for (int i = 0; i < 9; i++) {
			if (field[i][column] == n) {
				field[row][column] = n;
				return false;
			}
		}

		field[row][column] = n;
		return true;
	}

	/*
	* Returns true if values input by user are valid.
	*/
	public boolean checkIfValuesOk() {
		int tal;
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				tal = field[i][k];
				if(tal == 0){
					// do nothing
				} else if (!checkLegal(i, k, tal)) {
					return false;
				}
			}
		}

		return true;
	}
	
	/*
	* Returns true if the sudoku is solvable.
	* Else returns false.
	*/
	private boolean solve(int row, int column) {
		if (column >= 9) {
			return solve(++row, 0);
		}
		if (row >= 9) {
			return true;
		}

		if (field[row][column] == 0) {

			for (int i = 1; i <= 9; i++) {
				if (checkLegal(row, column, i)) {
					field[row][column] = i;
					if (solve(row, column + 1)) {
						return true;
					}
				}
			}
			field[row][column] = 0;
			return false;
		}

		// RUTAN �R IFYLLD AV ANV�NDAREN
		if (field[row][column] != 0) {
			int n = field[row][column]; // s�tt n till det v�rde som finns i
										// field[row][column]

			if (checkLegal(row, column, n)) { // testa om v�rdet �r ok
				return solve(row, column + 1);
			}
		}
		return false;
	}

	

	/*
	 * public static void main(String[] args) { Sudoku s1 = new Sudoku();
	 * s1.setup(); s1.printField(); System.out.println();
	 * System.out.println("Sudokun status: " + s1.solve()); // s1.solve(0,0);
	 * System.out.println(); s1.printField();
	 * 
	 * }
	 */

}
