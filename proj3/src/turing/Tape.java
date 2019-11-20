package turing;

/**
 * This class creates a doubly-linked list
 * to be used as the tape in a turing machine.
 * @author Anonymous
 */

public class Tape
{
	//This is used as a pointer to the current cell.
	private Cell cell;
	
	/**
	 * This is the constructor for initiating the tape with a
	 * single blank cell and reference to empty cells on either side.
	 */
	public Tape()
	{
		Cell newCell = new Cell();
		newCell.content = ' ';
		newCell.prev = null;
		newCell.next = null;
		cell = newCell;
	}
	
	/**
	 * Simply returns the cell pointer to the caller
	 * @return cell A pointer to the current cell.
	 */
	public Cell getCurrentCell()
	{
		return cell;
	}
	
	/**
	 * Returns the character contained within the cell.
	 * @return cell.content The character contained within the cell.
	 */
	public char getContent()
	{
		return cell.content;
	}
	
	/**
	 * Sets the current cell's content to the given parameter.
	 * @param ch Used to set the cell's current content.
	 */
	public void setContent(char ch)
	{
		cell.content = ch;
	}
	
	/**
	 * Moves the tape forward along the turing machine by one cell.
	 */
	public void moveLeft()
	{
		if (cell.prev == null)
		{
			Cell newCell = new Cell();
			newCell.content = ' ';
			newCell.prev = null;
			newCell.next = cell;
			cell.prev = newCell;
		}
		
		cell = cell.prev;
	}
	
	/**
	 * Moves the tape back along the turing machine by one cell.
	 */
	public void moveRight()
	{
		if (cell.next == null)
		{
			Cell newCell = new Cell();
			newCell.content = ' ';
			newCell.next = null;
			newCell.prev = cell;
			cell.next = newCell;
		}
		
		cell = cell.next;
	}
	
	/**
	 * Processes each cell in the tape from left to right
	 * and places all values within each cell into a string.
	 * @return fullContent Contains a string of all characters of all cells.
	 */
	public String getTapeContents()
	{
		Cell runner = cell;
		
		while (runner.prev != null)
		{
			runner = runner.prev;
		}
		
		String fullContent = "";
		
		while (runner != null)
		{
			fullContent += runner.content;
			runner = runner.next;
		}
		
		fullContent = fullContent.trim();
		return fullContent;
	}
}