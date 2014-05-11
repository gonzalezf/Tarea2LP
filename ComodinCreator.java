import java.util.*;


public class ComodinCreator extends AbstractCreator{
	public Bloque crearBloque(){

		Random rand = new Random();
		int x = rand.nextInt(2);
		if(x==0){
			return (Bloque) new BloqueComodin(1);
		}
		if(x==1){
			return (Bloque) new BloqueComodin(2);
		}
		return null;
	}
}