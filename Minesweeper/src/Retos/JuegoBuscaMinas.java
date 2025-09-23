package Retos;

import java.util.Random;

public class JuegoBuscaMinas 
{
	private Casilla[][] tablero;
	private int minas;
	private int minasMarcadas;
	private int dificultad;
	
	public JuegoBuscaMinas() 
	{
		setDificultad(1);
		iniciarTablero();
	}
	
	public int getDificultad() 
	{
		return dificultad;
	}
	
	public void setDificultad(int nivel) 
	{
		if (nivel<=3 && nivel>=1) 
		{
			dificultad=nivel;
		}
	}
	
	public int getLongitudTablero() 
	{
		return (dificultad*4)+4;
	}
	
	public void iniciarTablero() 
	{
		minas=0;
		minasMarcadas=0;
		switch (dificultad) 
		{
		case 1:
			tablero=new Casilla[8][8];
			minas=10;
			break;
		case 2:
			tablero=new Casilla[12][12];
			minas=30;
			break;
		case 3:
			tablero=new Casilla[16][16];
			minas=60;
			break;
		default:
			break;
		}
		generarMinas();
		for(int i=0;i<tablero.length;i++) 
		{
			for(int j=0;j<tablero.length;j++) 
			{
				if (tablero[i][j]==null) 
				{
					tablero[i][j]=new Casilla(false,calcularMinasCercanas(i,j));
				}
			}
		}
	}

	private void generarMinas() {
		Random random=new Random();
		for(int i=0;i<minas;i++) 
		{
			int num=random.nextInt(tablero.length);
			int num1=random.nextInt(tablero.length);
			while (tablero[num][num1]!=null) 
			{
				num=random.nextInt(tablero.length);
				num1=random.nextInt(tablero.length);
			}
			tablero[num][num1]=new Casilla(true, 9);
		}
	}
	
	public void imprimirTablero() 
	{
		String tablero1="  ";
		String divisor="  ";
		for(int i=1;i<=tablero.length;i++) 
		{
			divisor+="---";
			tablero1+=(i<10)?"  "+i:" "+i;
		}
		System.out.println(tablero1);
		System.out.println(divisor+"--");
		String car="";
		for(int i=1;i<=tablero.length;i++) 
		{
			tablero1=(i<10)?i+" |":i+"|";
			for(int j=0;j<tablero.length;j++) 
			{
				if (tablero[i-1][j].getEstaOculta()) 
				{
					car=tablero[i-1][j].getEstaMarcada()?"F":"-";
					//para ver la ubicacion de las minas
					//car=tablero[i-1][j].getEstaMarcada()?"F":tablero[i-1][j].getTieneMina()?"*":"-";
				}
				if (!tablero[i-1][j].getEstaOculta())
				{
					car=tablero[i-1][j].getNumMinasCercanas()+"";
				}
				tablero1+=" "+car+" ";
			}
			System.out.println(tablero1);
		}
	}
	
	public boolean descubrirCasilla(int columna,int fila)
	{
		if (!casillaValida(columna, fila)) 
		{
			return false;
		}
		if (tablero[columna][fila].getNumMinasCercanas()==0) 
		{
			tablero[columna][fila].setEstaOculta(false);
			descubrirCasilla(columna-1, fila);
			descubrirCasilla(columna+1, fila);
			descubrirCasilla(columna, fila-1);
			descubrirCasilla(columna, fila+1);
			descubrirCasilla(columna-1, fila-1);
			descubrirCasilla(columna+1, fila-1);
			descubrirCasilla(columna-1, fila+1);
			descubrirCasilla(columna+1, fila+1);
		}
		else 
		{			
			tablero[columna][fila].setEstaOculta(false);
		}
		return true;
	}
	
	public boolean marcarCasilla(int columna,int fila) 
	{
		if (!casillaValida(columna, fila)) 
		{
			return false;
		}
		tablero[columna][fila].setEstaMarcada(true);
		if (tablero[columna][fila].getTieneMina()) 
		{
			minasMarcadas++;
		}
		return true;
	}

	private boolean casillaValida(int columna,int fila) 
	{
		if ((fila>=tablero.length || fila<0 || columna>=tablero.length || columna<0)||
				!tablero[columna][fila].getEstaOculta() || tablero[columna][fila].getEstaMarcada()) 
		{
			return false;
		}
		return true;
	}
	
	private int calcularMinasCercanas(int column,int fila) 
	{
		int cont=0;
		for(int i=-1;i<2;i++) 
		{
			for(int j=-1;j<2;j++) 
			{
				if (i==0&&j==0) 
				{
					continue;
				}
				if(fila+j<0||column+i<0||fila+j>=tablero.length||column+i>=tablero.length)
				{
					continue;
				}
				if (tablero[column+i][fila+j]!=null && tablero[column+i][fila+j].getTieneMina())
				{
					cont++;
				}
			}
		}
		return cont;
	}
	
	public int causaTerminacionJuego() 
	{
		int contador=0;
		for(int i=0;i<tablero.length;i++)
		{
			for(int j=0;j<tablero.length;j++) 
			{
				if (tablero[i][j].getTieneMina()&&!tablero[i][j].getEstaOculta())
				{
					return 2;
				}
				if (!tablero[i][j].getTieneMina()&&tablero[i][j].getEstaMarcada()) 
				{
					return 3;
				}
				if (!tablero[i][j].getEstaOculta()) 
				{
					contador++;
				}
			}
		}
		int casillasTotales=(int)Math.pow(tablero.length, 2);
		if (minas==minasMarcadas) 
		{
			return 1;
		}
		if (casillasTotales-minas==contador) 
		{
			return 4;
		}
		return 0;
	}
}
