import java.util.*;
import java.util.Random;

public class ColorCreator extends AbstractCreator
{
	public Bloque crearBloque()
	{
		Random rand = new Random();
		int x = rand.nextInt(5);
		if(x==0){
			return (Bloque) new BloqueColor("R");		
		}
		if(x==1){
			return (Bloque) new BloqueColor("B");	
		}

		if(x==2){
			return (Bloque) new BloqueColor("O");
		}
		
		if(x==3){
			return (Bloque) new BloqueColor("G");
		}

		if(x==4){
			return (Bloque) new BloqueColor("Y");	
		}
		return null;
	}
}