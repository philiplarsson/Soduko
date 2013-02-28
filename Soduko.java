package Sudoku;

public class Sudoku {
  int[][] field;

	public Sudoku() {
		field = new int[9][9];
	}

	public boolean checkLegal(int row, int column, int n) {

		// Kolla om n förekommer i det nuvarande rutfältet 3*3.
//Rundas int ner? Kan man i så fall bestämma de olika större fälten genom att dela med tre?
//Exempel: row/3 eller column/3 ger en int mellan 0-2.

		

		// Kolla sin rad om n förekommer.
		for (int i = 0; i <= 8; i++) {
			if (field[row][i] == n) {
				return false;
			}
		}

		//Kolla om n förekommer i kolumnen.
		for (int i = 0; i <= 8; i++) {
			if (field[i][column] == n) {
				return false;
			}
		}

		/*
		 * Om allt ok: 
		 * sätta in talet i de grafiska gränssnittet
		 * returnera true
		 */

		/*
		 * annars, returnera false
		 */
		return false;
	}

	public boolean solve(int row, int column) {
		// RUTAN ÄR TOM
		if (true) {
			if (column >= 9) {
				return solve(row++, 0);
			}
			if (row >= 9) {
				// vi är färdiga
				return true;
			}
			if (column < 0) {
				return solve(row--, 8);
			}
			if (row < 0) {
				// sudokun gick inte att lösa
				return false;
			}

			for (int i = 1; i <= 9; i++) {
				if (checkLegal(row, column, i) == true) {
					return solve(row, column++);
				}
			}
			// ingen av de 9 talen fungerar, gå tillbaka ett steg:
			return solve(row, column--);
		}
		// RUTAN ÄR IFYLLD AV ANVÄNDAREN
		if (true) {
			int n = 0; // sätt n till det värde som finns i row, column
			if (checkLegal(row, column, n)) { // testa om värdet är ok
				return solve(row, column++);
			} else {
				return solve(row, column--);
				// om värdet inte är ok, returnera false
				// ska man först kolla om alla tal inmatade av användaren är ok?
				// return false;
			}
		}
		// return checkLegal(row, column, i);
	}

	public int put(int nbr) {
		// Skriv in ett tal i rutan.
		return 0;
	}

	public static void main(String[] args) {
		Sudoku s1 = new Sudoku();
	}
}
