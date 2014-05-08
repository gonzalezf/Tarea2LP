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


	public static void DibujarTablero(){


		int cantidadcasilleros;
		BloqueColor [][] arreglo = new BloqueColor[15][15]; //quizas hay que hacer un for...! no estoy muy seguro!
		//Inicializar tablero!!! 
		InicializarTablero(arreglo);




    	//System.out.println("llegue!");
    	/* Imprimir tablero...! */
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

    public static void main(String[] args) {
    	
    	DibujarTablero();


        }



}



 