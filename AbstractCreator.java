
import java.util.Random; 
import java.util.*;

abstract class AbstractCreator {

    public abstract Bloque crearBloque();
}













/*
class Guitarra extends Instrumento {
    public void tocar() {
	System.out.println("Guitarra.tocar()");
    }
    public String tipo() { return "Guitarra"; }
    public void afinar() {}
}

class Piano extends Instrumento {
    public void tocar() {
	System.out.println("Piano.tocar()");
    }
    public String tipo() { return "Piano"; }
    public void afinar() {}
}

class Saxofon extends Instrumento {
    public void tocar() {
	System.out.println("Saxofon.tocar()");
    }
    public String tipo() { return "Saxofon"; }
    public void afinar() {}
}

// Un tipo de Guitarra 
class Guzla extends Guitarra {
       public void tocar() {
         System.out.println("Guzla.tocar()");
       }
       public void afinar() {
         System.out.println("Guzla.afinar()");
       }
     }

// Un tipo de Guitarra 
class Ukelele extends Guitarra {
    public void tocar() {
	System.out.println("Ukelele.tocar()");
    }
    public String tipo() { return "Ukelele"; }
}

public class Musica2 {

    // No importa el tipo de Instrumento,
    // seguirá funcionando debido a Polimorfismo:
    static void afinar(Instrumento i) {
	// ...
	i.tocar();
    }

    static void afinarTodo(Instrumento[] e) {
	
	for(int i = 0; i < e.length; i++)
	    afinar(e[i]);
    }


    public static void main(String[] args) {

    }
}*/
