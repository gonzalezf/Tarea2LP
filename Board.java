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

//Un pequeño comentario en como funcionara.
//Primero se mostrara el menu de bienvenida, en donde
//el usuario debera hacer click en Jugar o Salir
//Si hace click en jugar, saldra otro dialogo
//en donde debera decir cuantos bloques de cada
//color deben ser explotados para ganar.
//
//Luego comenzara el juego:
//1)Se llena completamente el tablero utilizadno refill();
//2)Verifica columna por columna, y en cada una, bloque por
//bloque si algun de ellos explota, si lo hace, se explota
//y se vuelve a verificar del comienzo.
//3)Si no hay mas posibles movimientos, se hace un refill.
//4)De otra forma, se espera al input del usuario
//5)Si el usuario explota algo, se verifica primero el bloque
//movido, si este explota se verifica en todos lados.
//Nota: Se llena luego de que dejan de haber explosiones.

//Para detectar en que panel hace click el mouse
public class Board implements MouseListener
{
	JFrame frame; //Ventana principal
	JPanel grid_panel; //Panel con los bloques
	JPanel score_panel; //Panel con el puntaje
	boolean canClick;
	Bloque firstClickedBlock; //Ultimo bloque clickeado
	Bloque secondClickedBlock;
	BloqueColor temp_block;
	Bloque blocks[][];
	public Board()
	{
		this.canClick = true;
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

		//Inicializamos el arreglo de bloques
		this.blocks = new Bloque[15][15];
		int c = 0; //Esto es solo para debug

		//Para mantener coordenadas cartesianas
		for(int y = 14; y >= 0; y--)
		{
			for(int x = 0; x <= 14; x++)
			{
				//Veamos la probabilidad de que salga un comodin o no
				Random rand1 = new Random();
				int n = rand1.nextInt(100);
				if( n < 95 )
				{
					//Lo que use aqui fue el metodo de la estrategia en wikipedia
					//Lee lo que sale ahi para entender como funciona esta parte
					this.blocks[x][y] = BloqueFactory.crearBloque( new ColorCreator() );
				}
				else
				{
					this.blocks[x][y] = BloqueFactory.crearBloque( new ComodinCreator() );
				}
				this.blocks[x][y].x = x;
				this.blocks[x][y].y = y;
				this.blocks[x][y].setPreferredSize(new Dimension(30, 30));
				this.blocks[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
				this.blocks[x][y].paintColor();
				this.blocks[x][y].setVisible(true);
				this.blocks[x][y].addMouseListener(this);
				this.blocks[x][y].add(new JLabel(""+c+""));

				//Añadir al grid
				this.grid_panel.add( blocks[x][y] );
			}
		}
	}

	public void OnExplosion()
	{

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
			return new Color(184, 134, 11);
		else if(color.equals("Y"))
			return Color.YELLOW;
		else if(color.equals("$"))
			return Color.GRAY;
		else if(color.equals("&"))
			return Color.BLACK;
		return Color.WHITE;

	}

	public void FillColumn(int columna){ //llenado automatico por columna
		for(int y = 0;y<14;y++){ //revisa en una columna desde abajo hacia arriba
			String colorpresente = 	this.blocks[columna][y].getColor(); //obtiene color
			if (colorpresente.equals("-") ){ //significa que esta en blanco...hay que reemplazar color
		//		System.out.println("Colorenblanco, reemplazar x:"+columna+"y:"+y+".");
				//hacer algo, en el caso que arriba aun queden colores...
				for(int j = y+1;j<15;j++){
					if(!this.blocks[columna][j].getColor().equals("-")){
						this.blocks[columna][y].setColor(this.blocks[columna][j].getColor()); //se hizo un reemplazo
						this.blocks[columna][j].setColor("-"); // el que tenia color ahora es blanco..!
						this.blocks[columna][y].paintColor();
						System.out.println("bloque:["+columna+"]["+y+"]color nuevo: "+this.blocks[columna][y].getColor()+".");
			
					}
					if(j ==14 && this.blocks[columna][j].getColor().equals("-")){
					//Si llego a la cima y es blanco hay que sacar colores nuevos
						for(int z = y;z<15;z++){ //aqui volvemos a revisar desde abajo..

							//crear nuevo bloque.. sacar el color //revisar si sigue cumplienado propiedad de comodin.
							if(this.blocks[columna][z].equals("-")){
								Random rand1 = new Random();
								int n = rand1.nextInt(100);
								if( n < 95 )
								{
									//Lo que use aqui fue el metodo de la estrategia en wikipedia
									//Lee lo que sale ahi para entender como funciona esta parte
				f					Bloque b1 = BloqueFactory.crearBloque( new ColorCreator() );
									this.blocks[columna][z].setColor(b1.getColor());
									this.blocks[columna][z].paintColor();
									System.out.println("bloque:["+columna+"]["+y+"]color nuevo: "+this.blocks[columna][y].getColor()+".");
									}
								else
								{
									Bloque b1 = BloqueFactory.crearBloque( new ComodinCreator() );
									this.blocks[columna][z].setColor(b1.getColor());
									this.blocks[columna][z].paintColor();
									System.out.println("bloque:["+columna+"]["+y+"]color nuevo: "+this.blocks[columna][y].getColor()+".");
							
								}


							}

						}


					} 


				}
			}


		}
		

	}
	public void FillBoard(){ //llenado automatico luego de cada insercion

		for(int x= 0; x<15;x++){
//			System.out.println("Felipe!!! entro!!!! columna"+x+".");
			FillColumn(x);

		}
	}
	public void draw()
	{
		this.frame.revalidate();
		this.frame.repaint();
	}
	/*
	public void fillEmptySpaces() // Terminar!!!!
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
	*/
	public void EliminadoAutomatico(){


		//por cada movimiento debe recorrer y eliminar todos los colores adyacentes. Recordar que es 
		//fila y columna!

	}

	public void LlenadoAutomatico(){

		//tras eliminarse debe bajar las cosas en forma de pila automaticamente...
		//desde arriba hacia abajo con colores distintos...

	}



	public void IsAnyMove(){
		Board b = World.board;
		int contador = 0; //cuando llega a tres significa que hay un motivimiento valido
		String color = "";
		for(int y=0;y<15;y++){
			for(int x = 0;x<15;x++){
				if(color.equals(b.blocks[x][y].getColor()) || b.blocks[x][y].getColor().equals("$") || b.blocks[x][y].getColor().equals("&")){
					contador+=1;

				}
				else{
					color = b.blocks[x][y].getColor();
					contador =0;
				}
				if(contador==2)
					return;//movimiento valido

			}			
		}
		contador =0;
		color = "";
		for(int x =0;x<15;x++){
			for(int y = 0;y<15;y++){
				if(color.equals(b.blocks[x][y].getColor()) || b.blocks[x][y].getColor().equals("$") || b.blocks[x][y].getColor().equals("&")){
					contador+=1;
				}
				else{
					color = b.blocks[x][y].getColor();
					contador =0;
				}
				if( contador ==2)
					return; //movimiento valido
			}
		}
		//no hay movimiento validos!
		b.refill(); // deberia reiniciarse!
		


	}

	//Reasigna y pinta todos los paneles, descartanto el arreglo de bloques
	//original

	public void refill() //LLENADO AL INICIO... terminado.
	{

		//Borro todos los bloques
		for(int y = 14; y >= 0; y--)
		{
			for(int x = 0; x <= 14; x++)
			{
				this.grid_panel.remove(blocks[x][y]);
			}
		}
		int c = 0;
		for(int y = 14; y >= 0; y--)
		{
			for(int x = 0; x <= 14; x++)
			{
				c++;
				//Veamos la probabilidad de que salga un comodin o no
				Random rand1 = new Random();
				int n = rand1.nextInt(100);
				if( n < 95 )
				{
					//Lo que use aqui fue el metodo de la estrategia en wikipedia
					//Lee lo que sale ahi para entender como funciona esta parte
					this.blocks[x][y] = BloqueFactory.crearBloque( new ColorCreator() );
				}
				else
				{
					this.blocks[x][y] = BloqueFactory.crearBloque( new ComodinCreator() );
				}
				this.blocks[x][y].x = x;
				this.blocks[x][y].y = y;
				this.blocks[x][y].setPreferredSize(new Dimension(30, 30));
				this.blocks[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
				this.blocks[x][y].paintColor();
				this.blocks[x][y].setVisible(true);
				this.blocks[x][y].addMouseListener(this);
				this.blocks[x][y].add(new JLabel(""+c+""));

				//Añadir al grid
				this.grid_panel.add( blocks[x][y] );
			}
		}
			
	}

	//Las coordenadas que se obtiene son del tipo (x, y);
	public void mouseClicked(MouseEvent me)
	{
		IsAnyMove(); // checkea si hay mas movimientos!
		if(!this.canClick)
		{
			return;
		}

		//No se ha seleccionado nada previamente
		if(this.firstClickedBlock == null)
		{
			//Le pongo un highlight al bloque y lo guardo
			this.firstClickedBlock = (Bloque) me.getSource();
			this.firstClickedBlock.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,2,true));

		}
		else if(this.firstClickedBlock == (Bloque) me.getSource())
		{
			this.firstClickedBlock.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
			this.firstClickedBlock = null;
		}
		else
		{
			this.canClick = false;
			//Reinicio el borde
			this.firstClickedBlock.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
			Bloque bloque1, bloque2;
			bloque1 = this.firstClickedBlock;
			bloque2 = (Bloque) me.getSource();
			this.secondClickedBlock = bloque2;
			//Es el movimiento valido?
			int x = bloque1.x;
			int y = bloque1.y;
			int v = bloque2.x;
			int w = bloque2.y;
			if( (x != v || ( (y-w) != 1 && (w-y) != 1)) 
			&& 	(y != w || ( (x-v) != 1 && (v-x) != 1)) )
			{
				this.firstClickedBlock = null;
				this.canClick = true;
				this.score_panel.add(new JLabel("Invalid move"));
				return;
			} 


			//Swap blocks
			//No es posible intercambiar fisicamente dos JPanel
			//por lo que les intercambiare su color, el background
			//las coordenadas internas y externas en la lista

			//temp <--- bloque1
			Color temp_background = bloque1.getBackground();
			String temp_color = bloque1.getColor();
			HabilityBehavior temp_hability = bloque1.habilidad;

			//bloque1 <---- bloque2
			bloque1.setBackground(bloque2.getBackground());
			bloque1.setColor(bloque2.getColor());
			bloque1.habilidad = bloque2.habilidad;

			//bloque2 <----- bloque 1
			bloque2.setBackground(temp_background);
			bloque2.setColor(temp_color);
			bloque2.habilidad = temp_hability;

			//Chequeo el swap luego de 700ms (modificable)
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

	public void HighLightNeighboors(Bloque bloque)
	{
		int x_count = 1; //contador para ver vecinos adyacentes. si son 3 explota.
		int y_count = 1;
		ArrayList <Bloque> block_list = new ArrayList<Bloque>();
		block_list.add(bloque);
		System.out.println("Verificar vecinos!\n");
		if(bloque.getColor().equals("-"))
			return;
		Bloque buffer = bloque;
		System.out.println("Vecinos arriba\n");
		while((buffer = getLeftBlock(buffer)) != null)
		{
			System.out.println(buffer.toString());
			if(!buffer.getColor().equals(bloque.getColor())
			&& !buffer.getColor().equals("N")
			&& !buffer.getColor().equals("GR"))
				break;
			x_count++;
			block_list.add(buffer);
		}
		buffer = bloque;
		System.out.println("Vecinos derecha\n");
		while((buffer = getRightBlock(buffer)) != null)
		{
			if(!buffer.getColor().equals(bloque.getColor())
			&& !buffer.getColor().equals("N")
			&& !buffer.getColor().equals("GR"))
				break;
			x_count++;
			block_list.add(buffer);
		}
		buffer = bloque;

		System.out.println("Vecinos arriba\n");
		while((buffer = getTopBlock(buffer)) != null)
		{
			if(!buffer.getColor().equals(bloque.getColor())
			&& !buffer.getColor().equals("N")
			&& !buffer.getColor().equals("GR"))
				break;
			y_count++;
			block_list.add(buffer);
		}
		buffer = bloque;
		System.out.println("Vecinos abajo\n");
		while((buffer = getBottomBlock(buffer)) != null)
		{
			if(!buffer.getColor().equals(bloque.getColor())
			&& !buffer.getColor().equals("N")
			&& !buffer.getColor().equals("GR"))
				break;
			y_count++;
			block_list.add(buffer);
		}
		System.out.println("The block ("+bloque.getColor()+") has "+x_count+" horizontal blocks and "+y_count+" vertical blocks");
		System.out.println("Vecinos: ");
		for(Bloque c : block_list)
		{
			c.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,2,true));
			System.out.println("("+c.x+", "+c.y+")");
			System.out.println("-------------------\n\n");
		}
	}

	//Verifico si debo explotar
	//Retorna true si explote y false si no...
	public Bloque getRightBlock(Bloque bloque)
	{
		int x = bloque.x;
		int y = bloque.y;
		if(x < 0 || x >= 14 || y < 0 || y > 14)
			return null;
		return this.blocks[x+1][y];
	}

	public Bloque getLeftBlock(Bloque bloque)
	{
		int x = bloque.x;
		int y = bloque.y;
		if(x <= 0 || x > 14 || y < 0 || y > 14)
			return null;
		return this.blocks[x-1][y];
	}

	public Bloque getTopBlock(Bloque bloque)
	{
		int x = bloque.x;
		int y = bloque.y;
		if(x < 0 || x > 14 || y < 0 || y >= 14)
			return null;
		return this.blocks[x][y+1];
	}

	public Bloque getBottomBlock(Bloque bloque)
	{
		int x = bloque.x;
		int y = bloque.y;
		if(x < 0 || x > 14 || y <= 0 || y > 14)
			return null;
		return this.blocks[x][y-1];
	}

	public Bloque getColorAtPos(int x, int y)
	{
		if(x < 0 || x > 14 || y < 0 || y > 14)
			return null;
		return this.blocks[x][y];
	}
	public boolean checkExplosions(Bloque bloque)// verificar o hacer explotar al inicio!
	{
		if(bloque.getColor().equals("-"))
			return false;
		int x_count = 1; //contador para ver vecinos adyacentes. si son 3 explota.
		int y_count = 1;
		ArrayList <Bloque> block_list = new ArrayList<Bloque>();
		block_list.add(bloque);
		if(bloque.getColor().equals("-"))
			return false;
		Bloque buffer = bloque;
		while((buffer = getLeftBlock(buffer)) != null)
		{
			if(!buffer.getColor().equals(bloque.getColor())
			&& !buffer.getColor().equals("N")
			&& !buffer.getColor().equals("GR"))
				break;
			x_count++;
			block_list.add(buffer);
		}
		buffer = bloque;
		while((buffer = getRightBlock(buffer)) != null)
		{
			if(!buffer.getColor().equals(bloque.getColor())
			&& !buffer.getColor().equals("N")
			&& !buffer.getColor().equals("GR"))
				break;
			x_count++;
			block_list.add(buffer);
		}
		buffer = bloque;
		while((buffer = getTopBlock(buffer)) != null)
		{
			if(!buffer.getColor().equals(bloque.getColor())
			&& !buffer.getColor().equals("N")
			&& !buffer.getColor().equals("GR"))
				break;
			y_count++;
			block_list.add(buffer);
		}
		buffer = bloque;
		while((buffer = getBottomBlock(buffer)) != null)
		{
			if(!buffer.getColor().equals(bloque.getColor())
			&& !buffer.getColor().equals("N")
			&& !buffer.getColor().equals("GR"))
				break;
			y_count++;
			block_list.add(buffer);
		}
		System.out.println("The block ("+bloque.getColor()+") has "+x_count+" horizontal blocks and "+y_count+" vertical blocks");
		if(x_count >= 3)
		{
			this.score_panel.add(new JLabel("BOOM!"));
			for(Bloque c : block_list)
			{
				if(c.y == bloque.y)
				{
					c.DestruirBloque();
				}
			}
			return true;
		}
		else if(y_count >= 3)
		{
			this.score_panel.add(new JLabel("BOOM!"));
			for(Bloque c: block_list) // recorre lista
			{	
				if(c.x == bloque.x)
				{
					c.DestruirBloque();
				}
			}
			return true;
		}
		return false;
	}

	//Verifico si el movimiento fue exitoso
	public void checkSwap()
	{
		//Esto resuelve el siguiente problema:
		//Si se mueve un comodin, este buscara cualquier color y causara que se elimine
		//toda la fila. Para esto, lo que se hace es chequear las exploiones de los vecinos.
		//Nota: Si el vecino es otro comodin, pasara el problema anterior igual. Se debe arreglar.
		boolean worked = false, worked2 = false;

		Bloque bloque1, bloque2;
		bloque1 = this.firstClickedBlock;
		bloque2 = this.secondClickedBlock;
		System.out.println("POSTSWAP: ("+bloque1.x+", "+bloque1.y+")"+"("+bloque2.x+", "+bloque2.y+")");

		//Para el primero que se intercambio
		if(bloque1.getColor().equals("GR")
		|| bloque1.getColor().equals("N"))
		{
			System.out.println("bloque1 es comodin");
			worked = checkExplosions(getLeftBlock(bloque1));
			if(!worked)
			{
				worked = checkExplosions(getRightBlock(bloque1));
			}
			if(!worked)
			{
				worked = checkExplosions(getTopBlock(bloque1));
			}
			if(!worked)
			{
				worked = checkExplosions(getBottomBlock(bloque1));
			}
		}
		else
		{
			worked = checkExplosions(bloque1);
		}
		if(bloque2.getColor().equals("GR")
		|| bloque2.getColor().equals("N"))
		{

			worked2 = checkExplosions(getLeftBlock(bloque2));
			if(!worked2)
			{
				worked2 = checkExplosions(getRightBlock(bloque2));
			}
			if(!worked2)
			{
				worked2 = checkExplosions(getTopBlock(bloque2));;
			}
			if(!worked2)
			{
				worked2 = checkExplosions(getBottomBlock(bloque2));
			}
		}
		else
		{
			worked2 = checkExplosions(bloque2);
		}
		//No hubo explosiones, deshacer intercambio
		if(!worked && !worked2)
		{
			//temp <--- bloque1
			Color temp_background = bloque1.getBackground();
			String temp_color = bloque1.getColor();

			//bloque1 <---- bloque2
			bloque1.setBackground(bloque2.getBackground());
			bloque1.setColor(bloque2.getColor());

			//bloque2 <----- bloque 1
			bloque2.setBackground(temp_background);
			bloque2.setColor(temp_color);
		}
		else{
			FillBoard();
		}
		this.firstClickedBlock = null;
		this.secondClickedBlock = null; 

		//El usuario ya puede clickear.
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