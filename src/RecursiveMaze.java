/**
 * @author Daniel Dowlin
 * @version 2.1.2022
 * A RecursiveMaze object consists of a 2D char array maze with empty space
 * represented with a '.' char and a wall represented with a '#'. The position
 * of a maze participant is represented with an "x". Entrances must be from the
 * left and exits must be from the right.
 */

import java.awt.Point;

public class RecursiveMaze 
{
	private char[][] maze;
	private Point pos;
	private String lastMove;
	
	//proves functionality with two given mazes
	public static void main(String[] args)
	{
		char[][] maze = 
		{
			{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
			{'#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#'},
			{'.', '.', '#', '.', '#', '.', '#', '#', '#', '#', '.', '#'},
			{'#', '#', '#', '.', '.', '.', '.', '.', '.', '#', '.', '#'},
			{'#', '.', '.', '.', '.', '#', '#', '#', '.', '#', '.', '#'},
			{'#', '#', '#', '#', '#', '#', '.', '#', '.', '#', '.', '#'},
			{'#', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
			{'#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
			{'#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '#'},
			{'#', '#', '#', '#', '#', '.', '#', '#', '#', '#', '.', '.'},
			{'#', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#'},
			{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
		};
		
		char[][] maze2 =
		{
			{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',},
			{'#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#',},
			{'.', '.', '#', '.', '#', '.', '#', '#', '#', '#', '.', '#',},
			{'#', '#', '#', '.', '#', '.', '.', '.', '.', '#', '.', '#',},
			{'#', '.', '.', '.', '.', '.', '#', '#', '.', '#', '.', '.',},
			{'#', '#', '#', '#', '#', '#', '.', '#', '.', '#', '.', '#',},
			{'#', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#',},
			{'#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#',},
			{'#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '#',},
			{'#', '#', '#', '#', '#', '.', '#', '#', '#', '#', '.', '#',},
			{'#', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#',},
			{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',}
		};
		
		RecursiveMaze rm1 = new RecursiveMaze(maze2);		
		rm1.traverseTheMaze();
	}
	
	/**
	 * Traverses a maze in an attempt to exit it with the "left-hand rule".
	 * Attempts to go up, if not up then right, if not up then down, and if
	 * not down then left as long as the opposite direction was not called prior.
	 * This is a recursive method which calls itself until the 'x' player is at the very
	 * right of the maze. The object's maze preconditions must be true.
	 * If an 'x' player is not present, it attempts to reach an open entrance
	 * on the very left side of the maze.
	 */
	public void traverseTheMaze()
	{
		try {
		System.out.println("=========================");
		//enters maze if 'x' not already present
		if(getPosition().equals(new Point(0,0)) && maze[0][0]!='x')
			for(int i=0; i<maze.length; i++)
				if (maze[i][0]=='.')
					maze[i][0]='x';
		
		if (getPosition().y==maze.length-1)
			System.out.println("Maze traversed and completed");
		else if (maze[getPosition().x-1][getPosition().y]=='.'
				&& !lastMove.equals("down"))
		{
			moveUp();
			System.out.println(this);
			traverseTheMaze();
		}
		else if (maze[getPosition().x][getPosition().y+1]=='.'
				&& !lastMove.equals("left"))
		{
			moveRight();
			System.out.println(this);
			traverseTheMaze();
		}
		else if (maze[getPosition().x+1][getPosition().y]=='.'
			&& !lastMove.equals("up"))
		{
			moveDown();
			System.out.println(this);
			traverseTheMaze();
		}
		else if (maze[getPosition().x][getPosition().y-1]=='.'
			&& !lastMove.equals("right"))
		{
			moveLeft();
			System.out.println(this);
			traverseTheMaze();
		}
		else
		{
			lastMove="";
			traverseTheMaze();
		} } catch (Exception e)
		{
			System.out.println("ERROR: Maze untraversable.");
		}
	}
	
	/**
	 * Creates a recursive maze object with the 2D char array. The maze
	 * must have exit on the right hand size of the maze. It also must have an
	 * 'x' player or else an entrance on the left hand side of the maze. The
	 * position of the 'x' player is tracked with the Point pos and its last
	 * move is tracked with a strong.
	 * @param maze
	 */
	public RecursiveMaze(char[][] maze)
	{
		this.maze=maze;
		pos = new Point();
		lastMove = "";
	}
	
	/**
	 * @return Current point of the 'x' player's
	 * array coordinates in the 2D char maze
	 * while updating the object's Point object.
	 */
	public Point getPosition()
	{
		for(int x = 0; x < maze.length; x++)
		{
			for(int y = 0; y < maze.length; y++)
			{
				if (maze[x][y]=='x')
				{
					pos.x=x;
					pos.y=y;
				}
			}
		}	
		return pos;
	}
	
	/**
	 * As long as the the 'x' player does not pass a border and
	 * there is a free space, it moves left(relative to us) and 
	 * updates the lastMove.
	 */
	public void moveLeft()
	{
		Point pos = getPosition();
		if (pos.x!=0 && maze[pos.x][pos.y-1]=='.')
		{
			maze[pos.x][pos.y-1]='x';
			maze[pos.x][pos.y]='.';
			lastMove = "left";
		}
	}
	
	/**
	 * As long as the the 'x' player does not pass a border and
	 * there is a free space, it moves right(relative to us) and 
	 * updates the lastMove.
	 */
	public void moveRight()
	{
		Point pos = getPosition();
		if (pos.x!=maze.length-1 && maze[pos.x][pos.y+1]=='.')
		{
			maze[pos.x][pos.y+1]='x';
			maze[pos.x][pos.y]='.';
			lastMove = "right";
		}
	}
	
	/**
	 * As long as the the 'x' player does not pass a border and
	 * there is a free space, it moves up(relative to us) 
	 */
	public void moveUp()
	{
		Point pos = getPosition();
		if (pos.y!=0 && maze[pos.x-1][pos.y]=='.')
		{
			maze[pos.x-1][pos.y]='x';
			maze[pos.x][pos.y]='.';
			lastMove = "up";
		}

	}
	
	/**
	 * As long as the the 'x' player does not pass a border and
	 * there is a free space, it moves down(relative to us) and 
	 * updates the lastMove.
	 */
	public void moveDown()
	{
		Point pos = getPosition();
		if (pos.y!=maze.length-1 && maze[pos.x+1][pos.y]=='.')
		{
			maze[pos.x+1][pos.y]='x';
			maze[pos.x][pos.y]='.';
			lastMove = "down";
		}
	}
	
	/**
	 * @return Last move of the 'x' player.
	 */
	public String getLastMove()
	{
		return lastMove;
	}
	
	public String toString()
	{
		String str = "";
		
		for(int x = 0; x < maze.length; x++)
		{
			for(int y = 0; y < maze.length; y++)
			{
				str+=(maze[x][y] + " "); 
			}
			str+="\n";
		}	
		
		return str;
	}
}



