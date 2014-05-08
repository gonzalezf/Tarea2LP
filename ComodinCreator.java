import java.util.*;


public class ComodinCreator extends AbstractCreator{

	public BloqueComodin crearBloque(){

		Random rand = new Random();
		int x = rand.nextInt(2);
		if(x==0){
			return new BloqueComodin(1);
		}
		if(x==1){
			return new BloqueComodin(2);
		}


}