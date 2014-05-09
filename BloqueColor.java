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
	
	public BloqueColor(String color){ //Constructor!
		this.color = color;
	}
	
	public String getColor(){
		return this.color;
	}

	public String setColor(String color){
		this.color = color;
	}


}
