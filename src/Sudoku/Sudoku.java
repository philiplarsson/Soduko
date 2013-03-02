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
	* Returns true if the Sudoku is solved.
	* Else returns false.
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
		
		/*
		* StartColumn and StartRow are used to move between
		* the nine larger fields.
		*/
		int startColumn = (column / 3) * 3;
		int startRow = (row / 3) * 3;
		field[row][column] = 0;

		/*
		* Checks the 3*3 field if n already exists.
		* Returns false if n already exists within this area.
		*/
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

		/*
		* Checks the row if n already exists.
		* Returns false if row already contains n.
		*/
		for (int i = 0; i < 9; i++) {
			if (field[row][i] == n) {
				field[row][column] = n;
				return false;
			}
		}
		
		/*
		* Checks the column if n already exists.
		* Returns false if column already contains n.
		*/
		for (int i = 0; i < 9; i++) {
			if (field[i][column] == n) {
				field[row][column] = n;
				return false;
			}
		}

		/*
		* Insertion of int n in field[row][column] is valid.
		* Changes the values and returns true.
		*/
		field[row][column] = n;
		return true;
	}

	/*
	* Returns true if values input by user are valid.
	*/
	public boolean checkIfValuesOk() {
		int nbr;
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				nbr = field[i][k];
				if(nbr == 0){
					// do nothing
				} else if (!checkLegal(i, k, nbr)) {
					return false;
				}
			}
		}

		return true;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 0; i++){
			for(int k = 0; k < 9; k++){
				sb.append(field[i][k]);
			}
			sb.append("\n");
		}
		
		return sb.toString();
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

		if (field[row][column] != 0) {
			int n = field[row][column]; 

			if (checkLegal(row, column, n)) {
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
