import java.util.*;
import java.util.Random;

public class ColorCreator extends AbstractCreator{

	public BloqueColor crearBloque(){
		System.out.println("ALOO!!!!!!!- Color Creator!");
		Random rand = new Random();
		int x = rand.nextInt(5);
		if(x==0){
			BloqueColor b1 = new BloqueColor("R");
			return b1;			
		}
		if(x==1){
			BloqueColor b1 = new BloqueColor("B");
			return b1;			
		}

		if(x==2){
			BloqueColor b1 = new BloqueColor("O");
			return b1;			
		}
		
		if(x==3){
			BloqueColor b1 = new BloqueColor("G");	
			return b1;		
		}

		if(x==4){
			BloqueColor b1 = new BloqueColor("Y");	
			return b1;		
		}
		BloqueColor b1 = new BloqueColor("-");
		return b1;

		

	}
		public BloqueColor crearBloque(int numero){
		System.out.println("ALOO!!!!!!!- Color Creator!");
		
		if(numero==0){
			BloqueColor b1 = new BloqueColor("R");
			return b1;			
		}
		if(numero==1){
			BloqueColor b1 = new BloqueColor("B");
			return b1;			
		}

		if(numero==2){
			BloqueColor b1 = new BloqueColor("O");
			return b1;			
		}
		
		if(numero==3){
			BloqueColor b1 = new BloqueColor("G");	
			return b1;		
		}

		if(numero==4){
			BloqueColor b1 = new BloqueColor("Y");	
			return b1;		
		}
		BloqueColor b1 = new BloqueColor("-");
		return b1;

		

	}


	public InicializarTablero(BloqueColor arreglo[int a][int b]){
		for(int i=0;i<a;i++){

			for(int j= 0;j<b;j++){

				arreglo[i][j] = crearBloque(); //No funciona .-. ¿como inicializamos?!

			}

		}




	}


}