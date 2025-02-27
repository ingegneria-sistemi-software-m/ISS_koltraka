package conway;


/*
 * Il core di game of life
 * Non ha dipendenze da dispositivi di input/output
 * Non ha regole di controllo del gioco 
 */

public class Life {
    //La struttura
    private Grid grid;
    private Grid nextGrid;
 
    public Life(int rowsNum, int colsNum) {
        this.grid = new Grid(rowsNum, colsNum);
        this.nextGrid = new Grid(rowsNum, colsNum);
    }

    protected int countNeighborsLive(int row, int col) {
        int count = 0;
        
        for(int i=-1; i<=1; i++) {
        	for(int j=-1; j<=1; j++) {
        		// devo rimanere dentro la griglia e non devo contare me stesso come vicino
        		if (!isFuoriGriglia(row+i, col+j) && !(i==0 && j==0))
	            	count += this.grid.getCellState(row+i, col+j) == true ? 1 : 0;
            }
        }
        
        return count;
    }

    private boolean isFuoriGriglia(int x, int y) {
    	return x < 0 || y < 0 || x >= grid.getRows() || y >= grid.getCols();
    }
    
    public void computeNextGen() {
        for (int i=0; i<this.grid.getRows(); i++) {
            for (int j=0; j<this.grid.getCols(); j++) {   	
                int n = countNeighborsLive(i,j);
                applyRules(i, j, n);
            }
        }
        
        copyAndResetGrid();
    }

    protected void copyAndResetGrid() {
        for (int i = 0; i < this.grid.getRows(); i++) {
            for (int j = 0; j < this.grid.getCols(); j++) {
            	boolean state = this.nextGrid.getCellState(i, j);
                this.grid.setCellState(i, j, state); 
            }
        }
        
        this.nextGrid.resetGrid();
    }

    protected void applyRules(int row, int col, int numNeighbors) {    	
        //CELLA VIVA
        if (this.grid.getCellState(row, col) == true) {
            if (numNeighbors < 2) { //muore per isolamento
                this.nextGrid.setCellState(row, col, false);
            } else if (numNeighbors == 2 || numNeighbors == 3) { //sopravvive
            	this.nextGrid.setCellState(row, col, true);
            } else if (numNeighbors > 3) { //muore per sovrappopolazione
            	this.nextGrid.setCellState(row, col, false);
            }
        }
        //CELLA MORTA
        else if (this.grid.getCellState(row, col) == false) {
            if (numNeighbors == 3) { //riproduzione
            	this.nextGrid.setCellState(row, col, true);
            }
        }
    }

	public Grid getGrid() {
		return grid;
	}

	public Grid getNextGrid() {
		return nextGrid;
	}
}
