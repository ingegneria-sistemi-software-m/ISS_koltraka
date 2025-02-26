package conway;

public class Grid {
	private int rows=0;
    private int cols=0;
    
	private Cell[][] grid;
	
	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.grid = new Cell[rows][cols];
		
		for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.cols; j++) {
            	this.grid[i][j] = new Cell(false);
            }
		} 
	}
	
    protected void resetGrid() {
    	for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.cols; j++) {
            	this.grid[i][j].setVita(false);
            }
		}
    }
    
    public void displayGrid(IOutDev outdev) {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				if (this.grid[i][j].isVita() == false) {
					outdev.displayCell("0");
                } else {
                	outdev.displayCell("1");
                }				 
			}
			outdev.displayCell("\n");
		}
	}
    
    public void switchCellState(int i, int j){
        if( this.grid[i][j].isVita() == true )
        	this.grid[i][j].setVita(false);
        else
        	this.grid[i][j].setVita(true);
    }
    
    public void setCellState(int i, int j, boolean state) {
        this.grid[i][j].setVita(state);
    }
    
    public boolean getCellState(int i, int j) {
        return this.grid[i][j].isVita();
    }
    
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
}
