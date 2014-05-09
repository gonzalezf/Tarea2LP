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

public class World
{
	/*
	Bloque[][] board;
	int required_blocks = 3;
	public static void DibujarTablero(){


		int cantidadcasilleros;
		BloqueColor [][] arreglo = new BloqueColor[15][15]; //quizas hay que hacer un for...! no estoy muy seguro!
		//Inicializar tablero!!! 
		InicializarTablero(arreglo);




    	//System.out.println("llegue!");
    	// Imprimir tablero...! 
		for(int i =0; i<15;i++){
		    
		    for(int j =0;j<15;j++){
				//System.out.print(arreglo[i][j].getColor());
				System.out.print(arreglo[i][j]);	
				//System.out.println("llegue!");

				//System.out.print("h");
		    }
		    System.out.println("");


	   	}
	   	cantidadcasilleros = 225;

      //  System.out.println("hola");



	}
	*/
	//Ventana
	JFrame frame;
	JPanel panel;

	//Tablero
	Bloque[][] board;
	public World()
	{
		//Nuevo panel
		frame = new JFrame();
		panel = new JPanel();
		//Para panel que contiene grid
		frame.setLayout(new GridLayout(1, 1));
		//Panel que conitene grid
		panel.setPreferredSize(new Dimension(450,450));
		panel.setLayout(new GridLayout(15, 15));
		frame.add(panel);
		grid= new JPanel[15][15];
		for(int y = 0; y < 15; y++){
			for(int x = 0; x < 15; x++){
				grid[x][y] = new Bloque();
				grid[x][y].setPreferredSize(new Dimension(30,30));
				grid[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
				grid[x][y].setBackground(Color.white);
				grid[x][y].setVisible(true);
			}
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		for(int y = 0; y < 15; y++){
			for(int x = 0; x < 15; x++){
				panel.add(grid[x][y]);
			}	
		}
	}

    public static void main(String[] args)
    {
  		Board board = new Board();
  		board.fill();
    }



}



 