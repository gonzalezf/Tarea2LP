import java.util.*;


public class ComodinCreator extends AbstractCreator{

	public void CrearBloque(){
		Bloque bloque = new Bloque(); //constructor..... ! hacer que funcione!

		Random rand = new Random();
		int x = rand.nextInt(2);
		if(x==0){
			bloque.hability = 1;
		}
		if(x==1){
			bloque.hability = 2;
		}


		
		}


}