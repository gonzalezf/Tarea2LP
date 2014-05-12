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


//La clase bloque sera ahora en concreto un panel.
public abstract class Bloque extends JPanel
{
	public int x;
	public int y;
	public String color;
	public abstract void DestruirBloque();
	public HabilityBehavior habilidad;
	public void paintColor()
	{
		setBackground(Color.WHITE);
	}
	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}
}