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

public class BloqueComodin extends Bloque
{
	HabilityBehavior habilidad;

	public BloqueComodin()
	{
		//Falta random!!!
		int hability_type = 1;
		if(hability_type == 1)
		{
			habilidad = (HabilityBehavior) new HabilityT1();
			setBackground(Color.GRAY);
			color = "GR";
		}
		else
		{
			habilidad = (HabilityBehavior) new HabilityT2();
			setBackground(Color.BLACK);
			color = "N";
		}
	}
	public HabilityBehavior Habilidad()
	{
		return this.habilidad;
	}

	@Override
	public void DestruirBloque()
	{
		this.setBackground(Color.WHITE);
		this.color = "-";
		//Ejeecutar habilidad aca
	}

	public void paintColor()
	{
		//Do nothing...
	}
}