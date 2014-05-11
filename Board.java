import java.util.*;
import java.io.*;
import java.io.Console;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Para detectar en que panel hace click el mouse
public class Board implements MouseListener
{
	JFrame frame;
	JPanel grid_panel;
	JPanel score_panel;
	JPanel[][] visual_blocks;
	BloqueColor[][] blocks;
	boolean canClick;
	int[] lastClickcoords;
	JPanel lastClick;
	BloqueColor temp_block;
	int[] clickCoords;
	public Board()
	{
		this.canClick = true;
		this.lastClickcoords = new int[2];
		//Esta es la ventana (teorica) que almacena todo
		this.frame = new JFrame();

		//Este es el panel que contiene tanto el grid
		//como el scoreboard
		this.grid_panel = new JPanel();
		this.score_panel = new JPanel();

		//Tendremos dos paneles
		this.frame.setLayout(new GridLayout(1, 2));

		//Este panel tiene el scoreboard
		this.score_panel.setPreferredSize(new Dimension(50, 455));

		//Este panel tiene el grid
		this.grid_panel.setPreferredSize(new Dimension(455,455));
		this.grid_panel.setLayout(new GridLayout(15, 15));

		//Añade el panel principal al frame.
		this.frame.add(score_panel);
		this.frame.add(grid_panel);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setVisible(true);
	}

	public static Color getActualColor(String color)
	{
		if(color.equals("R"))
			return Color.RED;
		else if(color.equals("B"))
			return Color.BLUE;
		else if(color.equals("G"))
			return Color.GREEN;
		else if(color.equals("O"))
			return Color.ORANGE;
		else if(color.equals("Y"))
			return Color.YELLOW;
		return Color.WHITE;
	}

	public void draw()
	{
		this.frame.revalidate();
		this.frame.repaint();
	}

	public void fillEmptySpaces()
	{
		while(true)
		{
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 15; y++)
				{
					//Este slot esta vacio
					if(this.blocks[x][y] == null)
					{
						this.blocks[x][y] = new BloqueColor();
						this.visual_blocks[x][y] = new JPanel();
						this.visual_blocks[x][y].setPreferredSize(new Dimension(30, 30));
						this.visual_blocks[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
						this.visual_blocks[x][y].setBackground(Board.getActualColor(this.blocks[x][y].getColor()));
						this.visual_blocks[x][y].setVisible(true);
						this.visual_blocks[x][y].addMouseListener(this);
						try{
							Thread.sleep(400);	
						} catch (Exception e) {

						}
					}
				}
			}
			break;
		}
	}

	public void fill()
	{
		this.visual_blocks = new JPanel[15][15];
		this.blocks = new BloqueColor[15][15];
		for(int y = 0; y < 15; y++)
		{
			for(int x = 0; x < 15; x++)
			{
				this.blocks[x][y] = new BloqueColor();
				this.visual_blocks[x][y] = new JPanel();
				this.visual_blocks[x][y].setPreferredSize(new Dimension(30, 30));
				this.visual_blocks[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
				this.visual_blocks[x][y].setBackground(Board.getActualColor(this.blocks[x][y].getColor()));
				this.visual_blocks[x][y].setVisible(true);
				this.visual_blocks[x][y].addMouseListener(this);
			}
		}
		System.out.println(grid_panel);
		System.out.println(score_panel);
		for(int y = 0; y < 15; y++){
			for(int x = 0; x < 15; x++){
				this.grid_panel.add( visual_blocks[x][y] );
			}	
		}
	}

	//Las coordenadas que se obtiene son del tipo (y, x);
	public void mouseClicked(MouseEvent me)
	{
		if(!this.canClick)
		{
			return;
		}
		if(this.lastClick == null)
		{
			this.lastClick = (JPanel) me.getSource();
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 15; y++)
				{
					if(this.visual_blocks[x][y] == this.lastClick)
					{
						this.lastClickcoords[0] = x;
						this.lastClickcoords[1] = y;
						this.visual_blocks[x][y].setBorder(BorderFactory.createLineBorder(Color.MAGENTA,2,true));
						return;
					}
				}
			}
		}
		else if(this.lastClick == (JPanel) me.getSource())
		{
			this.lastClick.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
			this.lastClick = null;
		}
		else
		{
			this.canClick = false;
			this.lastClick.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
			this.lastClick = (JPanel) me.getSource();
			clickCoords = new int[2];
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 15; y++)
				{
					if(this.visual_blocks[x][y] == this.lastClick)
					{
						clickCoords[0] = x;
						clickCoords[1] = y;
						break;
					}
				}
			}
			//Es el movimiento valido?
			int x = this.lastClickcoords[0];
			int y = this.lastClickcoords[1];
			int v = this.clickCoords[0];
			int w = this.clickCoords[1];
			System.out.println("("+x+", "+y+")|("+v+", "+w+")");
			if( (x != v || ( (y-w) != 1 && (w-y) != 1)) 
			&& 	(y != w || ( (x-v) != 1 && (v-x) != 1)) )
			{
				this.lastClick = null;
				this.canClick = true;
				this.score_panel.add(new JLabel("Invalid move"));
				return;
			} 


			//Swap blocks
			temp_block = this.blocks[this.lastClickcoords[0]][this.lastClickcoords[1]];
			this.blocks[this.lastClickcoords[0]][this.lastClickcoords[1]] = this.blocks[clickCoords[0]][clickCoords[1]];
			this.blocks[clickCoords[0]][clickCoords[1]] = temp_block;

			//Swap panel colors;
			this.visual_blocks[this.lastClickcoords[0]][this.lastClickcoords[1]].setBackground(Board.getActualColor(this.blocks[this.lastClickcoords[0]][this.lastClickcoords[1]].getColor()));
			this.visual_blocks[clickCoords[0]][clickCoords[1]].setBackground(Board.getActualColor(this.blocks[clickCoords[0]][clickCoords[1]].getColor()));
			this.canClick = false;
			new java.util.Timer().schedule( 
	        new java.util.TimerTask(){
	            @Override
	            public void run() {
	                checkSwap();
	            }
	        }, 
	        700);
		}
	}

	//Verifico si debo explotar
	//Retorna true si explote y false si no...
	public BloqueColor getRightBlock(int[] coord)
	{
		int x = coord[0];
		int y = coord[1];
		if(x < 0 || x >= 14 || y < 0 || y > 14)
			return null;
		return this.blocks[x+1][y];
	}

	public BloqueColor getLeftBlock(int[] coord)
	{
		int x = coord[0];
		int y = coord[1];
		if(x <= 0 || x > 14 || y < 0 || y > 14)
			return null;
		return this.blocks[x-1][y];
	}

	public BloqueColor getTopBlock(int[] coord)
	{
		int x = coord[0];
		int y = coord[1];
		if(x < 0 || x > 14 || y <= 0 || y > 14)
			return null;
		return this.blocks[x][y-1];
	}

	public BloqueColor getBottomBlock(int[] coord)
	{
		int x = coord[0];
		int y = coord[1];
		if(x < 0 || x > 14 || y < 0 || y >= 14)
			return null;
		return this.blocks[x][y+1];
	}

	public BloqueColor getColorAtPos(int x, int y)
	{
		if(x < 0 || x > 14 || y < 0 || y > 14)
			return null;
		return this.blocks[x][y];
	}

	public boolean checkExplosions(int[] coord)
	{
		if(coord != null)
		{
			int[] org_coord = coord.clone();
			int x = coord[0];
			int y = coord[1];
			int x_count = 1;
			int y_count = 1;
			ArrayList <int[]> block_list = new ArrayList<int[]>();
			block_list.add(coord.clone());
			BloqueColor original = getColorAtPos(coord[0], coord[1]);
			BloqueColor buffer;
			while((buffer = getLeftBlock(coord)) != null)
			{
				if(!buffer.getColor().equals(original.getColor()))
					break;
				x_count++;
				coord[0]--;
				block_list.add(coord.clone());
			}
			while((buffer = getRightBlock(coord)) != null)
			{
				if(!buffer.getColor().equals(original.getColor()))
					break;
				x_count++;
				coord[0]++;
				block_list.add(coord.clone());
			}
			while((buffer = getTopBlock(coord)) != null)
			{
				if(!buffer.getColor().equals(original.getColor()))
					break;
				y_count++;
				coord[1]--;
				block_list.add(coord.clone());
			}
			while((buffer = getBottomBlock(coord)) != null)
			{
				if(!buffer.getColor().equals(original.getColor()))
					break;
				y_count++;
				coord[1]++;
				block_list.add(coord.clone());
			}
			System.out.println("The block ("+original.getColor()+") has "+x_count+" horizontal blocks and "+y_count+" vertical blocks");
			if(x_count >= 3 || y_count >= 3)
			{
				this.score_panel.add(new JLabel("BOOM!"));
				for(int[] c : block_list)
				{
					this.visual_blocks[c[0]][c[1]].setBackground(Color.WHITE);
					this.blocks[c[0]][c[1]].setColor("-");
				}
				return true;
			}
		}
		return false;
	}

	//Verifico si el movimiento fue exitoso
	public void checkSwap()
	{
		boolean worked = checkExplosions(this.lastClickcoords);
		boolean worked2 = checkExplosions(this.clickCoords);
		if(!worked && !worked2)
		{
			temp_block = blocks[this.lastClickcoords[0]][this.lastClickcoords[1]];
			blocks[this.lastClickcoords[0]][this.lastClickcoords[1]] = blocks[clickCoords[0]][clickCoords[1]];
			blocks[clickCoords[0]][clickCoords[1]] = temp_block;
			this.draw();

			this.visual_blocks[this.lastClickcoords[0]][this.lastClickcoords[1]].setBackground(Board.getActualColor(this.blocks[this.lastClickcoords[0]][this.lastClickcoords[1]].getColor()));
			this.visual_blocks[clickCoords[0]][clickCoords[1]].setBackground(Board.getActualColor(this.blocks[clickCoords[0]][clickCoords[1]].getColor()));
			this.fillEmptySpaces();
		}
		this.lastClick = null;
		this.canClick = true;
	}

	public void mouseEntered(MouseEvent me)
	{
	}

	public void mouseExited(MouseEvent me)
	{
	}

	public void mouseReleased(MouseEvent me) { }

	public void mousePressed(MouseEvent me)
	{
	}
}