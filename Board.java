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

public class Board{

	JFrame frame;
	JPanel panel;
	Bloque[][] blocks;
	public void  Board()
	{
		frame = new JFrame();
		panel = new JPanel();

		//Un solo panel
		frame.setLayout(new GridLayout(1, 1));

		//Panel principal
		panel.setPreferredSize(new Dimension(455,455));
		panel.setLayout(new GridLayout(15, 15));

		//Añade el panel principal al frame.
		frame.add(panel);
	}

	public void fill()
	{
		blocks = new Bloque[15][15];
		for(int y = 0; y < 15; y++)
		{
			for(int x = 0; x < 15; x++)
			{
				blocks[x][y] = AbstractCreator.crearBloque(); //'porque se llama a abstractcreator, si llamamos altiro al bloque mejor?'
				blocks[x][y].setPreferredSize(new Dimension(30,30));
				blocks[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
				blocks[x][y].setBackground(Color.white);
				blocks[x][y].setVisible(true);
			}
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		for(int y = 0; y < 15; y++){
			for(int x = 0; x < 15; x++){
				panel.add(blocs[x][y]);
			}	
		}
	}
}