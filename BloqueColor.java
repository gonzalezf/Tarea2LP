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
 

public class BloqueColor extends Bloque{

	String color;

	public void DestruirBloque()
	{	
	}
	
	public BloqueColor()
	{
		Random rand = new Random();
		int x = rand.nextInt(5);
		if(x==0){
			this.color = "R";		
		}
		if(x==1){
			this.color = "B";
		}

		if(x==2){
			this.color = "O";
		}
		
		if(x==3){
			this.color = "G";
		}

		if(x==4){
			this.color = "Y";
		}
	}

	public BloqueColor(String color)
	{
		this.color = color;
	}
	
	public String getColor()
	{
		return this.color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}
}
