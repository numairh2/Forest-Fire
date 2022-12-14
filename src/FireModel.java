public class FireModel {
	public static int SIZE = 47;
	private FireCell[][] myGrid;
	private FireView myView;

	public FireModel(FireView view) {
		myGrid = new FireCell[SIZE][SIZE];
		int setNum = 0;
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				myGrid[r][c] = new FireCell();
			}
		}
		myView = view;
		myView.updateView(myGrid);
	}

	/**
	 * Sets the fires onto the forest and determines either if Onett is in trouble
	 * or not
	 * 
	 * @throws InterruptedException if anything disturbs the program
	 */
	public void solve() throws InterruptedException {

		for (int i = 0; i < myGrid.length; i++) { // sets the bottom most layer to fire (and spreads it <-- helper
													// method)
			if (myGrid[myGrid.length - 1][i].getStatus() == 1) {
				myGrid[myGrid.length - 1][i].setStatus(2);
				setFire(myGrid.length - 2, i);
			}
		}

		boolean isTrouble = false;
		for (int i = 0; i < myGrid[0].length; i++) {
			if (myGrid[0][i].getStatus() == 2) {
				isTrouble = true;
				break;
			}

		}

		if (!isTrouble)
			System.out.println("Onett is not trouble. Happy people ;)");
		else
			System.out.println("Onett is trouble!");

		myView.updateView(myGrid);

	}

	/**
	 * Sets the fire cells by checking every possibility of a Tree. By checking each
	 * possible tree.
	 * 
	 * @param myGrid the 2D that contains each cell from the LifeModel
	 * @param x      the x coordinates of the cell
	 * @param y      the y coordinates of the cell
	 * @throws InterruptedException
	 */
	private void setFire(int x, int y) throws InterruptedException {
		if (!isInBound(x, y) || myGrid[x][y].getStatus() != 1)
			return;

		else if (myGrid[x - 1][y].getStatus() > 0 || myGrid[x][y - 1].getStatus() > 0
				|| myGrid[x + 1][y].getStatus() > 0 || myGrid[x][y + 1].getStatus() > 0) {
			myGrid[x][y].setStatus(2);
			setFire(x + 1, y);
			setFire(x - 1, y);
			setFire(x, y + 1);
			setFire(x, y - 1);
		}

		myGrid[x][y].setStatus(2);
		setFire(x - 1, y);
		setFire(x + 1, y);
		setFire(x, y - 1);
		setFire(x, y + 1);

	}
	/**
	 * Checks the if the x and y coordinates are valid 
	 * by checking if the coordinates pass the constrains 
	 * @param x	 the x coordinates of the cell
	 * @param y	the y coordinates of the cell
	 * @return true or false if the passes the constrains.
	 */
	private boolean isInBound(int x, int y) {
		return x + 1 < myGrid.length && x - 1 > -1 && y + 1 < myGrid.length && y - 1 > -1;
	}

}
