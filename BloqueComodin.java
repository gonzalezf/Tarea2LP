
public class BloqueComodin extends Bloque
{
	HabilityBehavior habilidad;

	public void DestruirBloque()
	{
		
	}

	public BloqueComodin(int hability_type)
	{
		if(hability_type == 1)
			habilidad = (HabilityBehavior) new HabilityT1();
		else
			habilidad = (HabilityBehavior) new HabilityT2();
	}
	public HabilityBehavior Habilidad()
	{
		return this.habilidad;
	}
}