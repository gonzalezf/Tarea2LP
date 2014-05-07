import java.util.*;


public class ColorCreator(){

	public void CrearBloque(){
	Bloque bloque = new Bloque(); //constructor..... ! hacer que funcione!

	Random rand = new Random();
	int x = rand.nextInt(5);
	if(x==0){
		bloque.color = "R";
	}
	if(x==1){
		bloque.color = "B"
	}

	if(x==2){
		bloque.color = "O"
	}
	
	if(x==3){
		bloque.color = "G"
	}

	if(x==4){
		bloque.color = "Y"
	}

	
	}


}