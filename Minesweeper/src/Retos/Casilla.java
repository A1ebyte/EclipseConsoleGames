package Retos;

public class Casilla 
{
	private boolean tieneMina, estaMarcada, estaOculta;
	private int numMinasCercanas;
	
	public Casilla(boolean tieneMina, int numMinasCercanas) 
	{
		this.tieneMina = tieneMina;
		this.numMinasCercanas = numMinasCercanas;
		setEstaMarcada(false);
		setEstaOculta(true);
	}
	
	public boolean getTieneMina() 
	{
		return tieneMina;
	}
	
	public boolean getEstaMarcada() 
	{
		return estaMarcada;
	}
	
	public void setEstaMarcada(boolean estaMarcada) 
	{
		this.estaMarcada = estaMarcada;
	}
	
	public boolean getEstaOculta() 
	{
		return estaOculta;
	}
	
	public void setEstaOculta(boolean estaOculta) 
	{
		this.estaOculta = estaOculta;
	}
	
	public int getNumMinasCercanas() 
	{
		return numMinasCercanas;
	}
	
	public String toString() 
	{
		return "tieneMina=" + tieneMina + ", estaMarcada=" + estaMarcada + ", estaOculta=" + estaOculta
				+ ", numMinasCercanas=" + numMinasCercanas;
	}
}
