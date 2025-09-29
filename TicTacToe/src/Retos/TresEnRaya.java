package Retos;
import java.util.Random;

public class TresEnRaya 
{
	private int[][] tablero;
	private int[][][] tableroAnterior;
	private int fichaJugador1;
	private int fichaJugador2;
	
	public TresEnRaya() 
	{
		tablero = new int[3][3];
		tableroAnterior = new int[9][3][3];
		iniciar();
	}
	
	public int getTurnosGuardados() 
	{
		int turnos=0;
		for (int i = 0; i < 9; i++) 
		{
			for(int k=0;k<3;k++) 
			{
				boolean salir=false;
				for(int j=0;j<3;j++) 
				{
					if (tableroAnterior[i][k][j]!=0) 
					{
						turnos++;
						salir=true;
						break;
					}
				}
				if (salir) 
				{
					break;
				}
			}
		}
		return turnos;
	}
	
	public void elegirFichasJugador1(int ficha) 
	{
		fichaJugador1=(ficha==1)?1:2;
		fichaJugador2=(ficha==1)?2:1;
	}
	
	public void elegirFichasJugador2(int ficha) 
	{
		fichaJugador2=(ficha==1)?1:2;
		fichaJugador1=(ficha==1)?2:1;
	}
	
	public void mueveJugador(int pos, int jugador) 
	{
		if (movimientoValido(pos)) 
		{
			pos-=1;
			tablero[pos/3][pos%3]=(jugador==1)?fichaJugador1:fichaJugador2;
			dibujaTablero();			
		}
	}
	
	public void mueveOrdenador(int jugador) 
	{
		Random ran=new Random();
		int pos=ran.nextInt(9)+1;
		while(!movimientoValido(pos)) 
		{
			pos=ran.nextInt(9)+1;
		}
		pos-=1;
		tablero[pos/3][pos%3]=(jugador==1)?fichaJugador1:fichaJugador2;
		dibujaTablero();
	}
	
	public boolean movimientoValido(int pos) 
	{
		if(pos<1||pos>9) 
		{
			return false;
		}
		pos-=1;
		if(tablero[pos/3][pos%3] != 0) 
		{
			return false;
		}
		return true;
	}
	
	public void iniciar()
	{
		//Esto es para reiniciar el tablero
		for(int i=0;i<3;i++) 
		{
			for(int j=0;j<3;j++) 
			{
				tablero[i][j]=0;
			}
		}
		//Esto es para reiniciar los tableros anteriores
		for(int i=0;i<9;i++) 
		{
			for(int k=0;k<3;k++) 
			{
				for(int j=0;j<3;j++) 
				{
					tableroAnterior[i][k][j]=0;
				}
			}
		}
		//poner fichas en default
		elegirFichasJugador1(1);
	}
	
	public boolean quedanMovimientos()
	{
		for(int i=0;i<3;i++) 
		{
			for(int j=0;j<3;j++) 
			{
				if (tablero[i][j]==0) 
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void dibujaTablero()
	{
		System.out.println("-------------");
		for(int i=0;i < 3;i++) 
		{
			for(int j=0;j<3;j++) 
			{
				char simbolo = tablero[i][j]==1?'X':tablero[i][j]==2?'O':' ';
				System.out.print("| " + simbolo +" ");
			}
			System.out.println("|");
			System.out.println("-------------");
		}
	}
	
	public boolean ganaJugador1()
	{
		//todas las condiciones para poder ganar
		//lineas horizontal 
		if (tablero[0][0]==fichaJugador1&&tablero[0][1]==fichaJugador1&&tablero[0][2]==fichaJugador1
				||tablero[1][0]==fichaJugador1&&tablero[1][1]==fichaJugador1&&tablero[1][2]==fichaJugador1
					||tablero[2][0]==fichaJugador1&&tablero[2][1]==fichaJugador1&&tablero[2][2]==fichaJugador1)
		{
			return true;
		}
		//lineas verticales
		if (tablero[0][0]==fichaJugador1&&tablero[1][0]==fichaJugador1&&tablero[2][0]==fichaJugador1
				||tablero[0][1]==fichaJugador1&&tablero[1][1]==fichaJugador1&&tablero[2][1]==fichaJugador1
					||tablero[0][2]==fichaJugador1&&tablero[1][2]==fichaJugador1&&tablero[2][2]==fichaJugador1)
		{
			return true;
		}
		//lineas diagonales
		if (tablero[0][0]==fichaJugador1&&tablero[1][1]==fichaJugador1&&tablero[2][2]==fichaJugador1
				||tablero[0][2]==fichaJugador1&&tablero[1][1]==fichaJugador1&&tablero[2][0]==fichaJugador1) 
		{
			return true;
		}
		return false;	
	}
	
	public boolean ganaJugador2() 
	{
		//todas las condiciones para poder ganar
		//lineas horizontal 
		if (tablero[0][0]==fichaJugador2&&tablero[0][1]==fichaJugador2&&tablero[0][2]==fichaJugador2
				||tablero[1][0]==fichaJugador2&&tablero[1][1]==fichaJugador2&&tablero[1][2]==fichaJugador2
					||tablero[2][0]==fichaJugador2&&tablero[2][1]==fichaJugador2&&tablero[2][2]==fichaJugador2)
		{
			return true;
		}
		//lineas verticales
		if (tablero[0][0]==fichaJugador2&&tablero[1][0]==fichaJugador2&&tablero[2][0]==fichaJugador2
				||tablero[0][1]==fichaJugador2&&tablero[1][1]==fichaJugador2&&tablero[2][1]==fichaJugador2
					||tablero[0][2]==fichaJugador2&&tablero[1][2]==fichaJugador2&&tablero[2][2]==fichaJugador2)
		{
			return true;
		}
		//lineas diagonales
		if (tablero[0][0]==fichaJugador2&&tablero[1][1]==fichaJugador2&&tablero[2][2]==fichaJugador2
				||tablero[0][2]==fichaJugador2&&tablero[1][1]==fichaJugador2&&tablero[2][0]==fichaJugador2) 
		{
			return true;
		}
		return false;	
	}
	
	public void mostrarPosicionesTablero() 
	{
		System.out.println("Posiciones del Tablero");
		System.out.println("-------------");
		for(int i=0;i < 3;i++) 
		{
			for(int x=0;x<3;x++) 
			{
				System.out.print("| " + ((i*3)+x+1) +" ");
			}
			System.out.println("|");
			System.out.println("-------------");
		}
	}
	
	public void guardarTablero(int turno) 
	{
		for(int i=0;i<3;i++) 
		{
			for(int j=0;j<3;j++) 
			{
				tableroAnterior[turno][i][j]=tablero[i][j];
			}
		}
	}
	
	public void dibujaTableroAnterior(int turno)
	{
		System.out.println("Tablero en el Turno"+(turno+1));
		System.out.println("-------------");
		for(int i=0;i < 3;i++) 
		{
			for(int j=0;j<3;j++) 
			{
				char simbolo = tableroAnterior[turno][i][j]==1?'X':tableroAnterior[turno][i][j]==2?'O':' ';
				System.out.print("| " + simbolo +" ");
			}
			System.out.println("|");
			System.out.println("-------------");
		}
	}
}
