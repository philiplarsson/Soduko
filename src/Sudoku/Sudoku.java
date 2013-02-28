package Sudoku;

public class Sudoku {
	int[][] field;

	public Sudoku() {
		field = new int[9][9];
	}

	public boolean checkLegal(int row, int column, int n) {
		System.out.println(n);
		/*
		 * Kolla om n förekommer i det nuvarande rutfältet 3*3. Rundas int ner?
		 * Kan man i så fall bestämma de olika större fälten genom att dela med
		 * tre? Exempel: row/3 eller column/3 ger en int mellan 0-2.
		 */

		int startColumn = (column / 3) * 3;
		int startRow = (row / 3) * 3;
		
		//förhindra att den kollar redan inskrivet tal
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

	public boolean solve(int row, int column) {
		//System.out.println();
		//printField();
		//System.out.println("Row: " + row + " Col: " + column);
		// RUTAN ÄR TOM
		if (field[row][column] == 0) {
			if (column >= 9) {
				return solve(++row, 0);
			}
			if (row >= 9) {
				// vi är färdiga
				return true;
			}
			if (column < 0) {
				return solve(--row, 8);
			}
			if (row < 0) {
				// sudokun gick inte att lösa
				return false;
			}

			for (int i = 1; i <= 9; i++) {
				if (checkLegal(row, column, i) == true) {
					field[row][column] = i;
					return solve(row, ++column);
				}
			}
			// ingen av de 9 talen fungerar, gå tillbaka ett steg:
			return solve(row, --column);
		}
		// RUTAN ÄR IFYLLD AV ANVÄNDAREN
		if (field[row][column] != 0) {
			int n = field[row][column]; // sätt n till det värde som finns i
										// field[row][column]
			//System.out.println(n);
			if (checkLegal(row, column, n)) { // testa om värdet är ok
//				System.out.println("true");
				return solve(row, ++column);
			} else {
//				System.out.println("not true");
				return solve(row, --column);
				// om värdet inte är ok, returnera false
				// ska man först kolla om alla tal inmatade av användaren är ok?
				// return false;
			}
		}
		return false;
	}

	public int put(int nbr) {
		// Skriv in ett tal i rutan.
		return 0;
	}
	
	private void setup() {
		field[0][0] = 9;
		field[0][4] = 2;
		field[0][6] = 7;
		field[0][7] = 5;

		field[1][0] = 6;
		field[1][4] = 5;
		field[1][7] = 4;

		field[2][1] = 2;
		field[2][3] = 4;
		field[2][7] = 1;

		field[3][0] = 2;
		field[3][2] = 8;

		field[4][1] = 7;
		field[4][3] = 5;
		field[4][5] = 9;
		field[4][7] = 6;

		field[5][6] = 4;
		field[5][8] = 1;

		field[6][1] = 1;
		field[6][5] = 5;
		field[6][7] = 8;

		field[7][1] = 9;
		field[7][4] = 7;
		field[7][8] = 4;

		field[8][1] = 8;
		field[8][2] = 2;
		field[8][4] = 4;
		field[8][8] = 6;
		for(int i = 0; i < 9;i++){
			for(int j = 0; j < 9;j++){
				if(field[i][j] < 1){
					field[i][j] = 0;
				}
			}
			
		}

	}

	private void printField() {
		for(int i = 0; i < 9;i++){
			for(int j = 0; j < 9;j++){
				System.out.print(field[i][j] + " ");
				}
			System.out.println();
			}
		
	}
	
	public static void main(String[] args) {
		Sudoku s1 = new Sudoku();
		s1.setup();
		s1.printField();
		s1.solve(0,0);
		s1.printField();
	}

	

	
}