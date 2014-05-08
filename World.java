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



public class World{
	public int cantidadcasilleros;

	public void DibujarTablero(){
		Bloque [][] arreglo = new Bloque[15][15];

    	
		for(int i =0; i<15;i++){
		    
		    for(int j =0;j<15;j++){
			//	System.out.print(arreglo[i][j].getColor());	
				System.out.print("h");



				cantidadcasilleros = 225;


		    }


		    System.out.println("");


	   	}

        System.out.println("hola");



	}

    public static void main(String[] args) {
    	
    	DibujarTablero();


        }



}



 