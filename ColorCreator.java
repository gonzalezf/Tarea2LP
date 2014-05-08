import java.util.*;
import java.util.Random;

public class ColorCreator extends AbstractCreator{

	public static BloqueColor crearBloque()
	{
		System.out.println("ALOO!!!!!!!- Color Creator!");
		Random rand = new Random();
		int x = rand.nextInt(5);
		if(x==0){
			return new BloqueColor("R");		
		}
		if(x==1){
			return new BloqueColor("B");	
		}

		if(x==2){
			return new BloqueColor("O");
		}
		
		if(x==3){
			return new BloqueColor("G");
		}

		if(x==4){
			return new BloqueColor("Y");	
		}
		return null;
	}
}