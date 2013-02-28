package Sudoku;

public class Sudoku {
	int[][] field;

	public Sudoku() {
		field = new int[9][9];
	}
	
	public Sudoku(int[][] field){
		this.field = field;
	}

	public boolean solve() {
		return solve(0, 0);
	}
	
	public int[][] getField(){
		return field;
	}

	public boolean checkLegal(int row, int column, int n) {
		/*
		 * Kolla om n förekommer i det nuvarande rutfältet 3*3.
		 */

		int startColumn = (column / 3) * 3;
		int startRow = (row / 3) * 3;

		// förhindra att den kollar redan inskrivet tal
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

		// Kolla sin rad om n förekommer.
		for (int i = 0; i < 9; i++) {
			if (field[row][i] == n) {
				field[row][column] = n;
				return false;
			}
		}

		// Kolla om n förekommer i kolumnen.
		for (int i = 0; i < 9; i++) {
			if (field[i][column] == n) {
				field[row][column] = n;
				return false;
			}
		}

		/*
		 * Om allt ok: sätta in talet i de grafiska gränssnittet returnera true
		 */
		field[row][column] = n;
		return true;
	}

	private boolean solve(int row, int column) {
		if (column >= 9) {
			return solve(++row, 0);
		}
		if (row >= 9) {
			return true;
		}
		// RUTAN ÄR TOM
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

		// RUTAN ÄR IFYLLD AV ANVÄNDAREN
		if (field[row][column] != 0) {
			int n = field[row][column]; // sätt n till det värde som finns i
										// field[row][column]

			if (checkLegal(row, column, n)) { // testa om värdet är ok
				return solve(row, column + 1);
			}
		}
		return false;
	}



	public static void main(String[] args) {
		/*Sudoku s1 = new Sudoku();
		s1.setup();
		s1.printField();
		System.out.println();
		System.out.println("Sudokun status: " + s1.solve());
		// s1.solve(0,0);
		System.out.println();
		s1.printField();
		*/
	}

}