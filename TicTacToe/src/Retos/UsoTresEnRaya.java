package Retos;
import java.util.Random;
import java.util.Scanner;

public class UsoTresEnRaya 
{
	public static Scanner in=new Scanner(System.in);
	private static TresEnRaya tablero=new TresEnRaya();
	private static int partidasTotales,jugador1PvPWin,jugador2PvPWin,partidasPvP,
							jugadorPvPCWin,PcPvPCWin,partidasPvPC,Pc1PCvPCWin,Pc2PCvPCWin;
	
	public static void main(String[] args) 
	{
		int opcion;
		System.out.println("---  Tres en Raya  ---");
		do 
		{
			System.out.print(mostraMenu());
			opcion=opcionValida(4,1);
			switch (opcion) 
			{
			case 1:
				modoDeJuego();
				break;
			case 2:
				mostrarEstadisticas();
				break;
			case 3:
				if(partidasTotales<1) 
				{
					System.out.println("\nNo hay partida para mostrar");
					break;
				}
				recrearPartida();
				break;
			case 4:
				System.out.println("\nGracias por Jugar");
				break;
			}
		} 
		while (opcion!=4);
	}
	
	public static String mostraMenu() 
	{
		return("\n1. Jugar una partida")+"\n"+("2. Mostrar estadísticas")+"\n"+("3. Recrear partida")
				+"\n"+("4. Salir")+"\n"+("Seleccione una opción: ");
	}
	
	public static String mostraMenuModosJuego() 
	{
		return("\n1. Jugador VS Maquina")+"\n"+("2. Jugador VS Jugador")+"\n"+("3. Maquina VS Maquina")+"\n"+
				("4. Regresar")+"\n"+("Seleccione una opción: ");
	}
	
	private static void modoDeJuego() 
	{
		int opcion=0;
		do {
			System.out.print(mostraMenuModosJuego());
			opcion=opcionValida(4,1);
			switch (opcion) 
			{
			case 1:
				System.out.println("\nJugador VS Maquina");
				partidasTotales+=1;
				partidaJugadorVSPc();
				mensajeFinalPartida(opcion);
				break;
			case 2:
				System.out.println("\nJugador VS Jugador");
				partidasTotales+=1;
				partidaJugadorVSJugador();
				mensajeFinalPartida(opcion);
				break;
			case 3:
				System.out.println("\nMaquina VS Maquina");
				partidasTotales+=1;
				partidaPcVSPc();
				mensajeFinalPartida(opcion);
				break;
			case 4:
				System.out.println("\nRegresando...");
				break;
			}
			opcion=4;
		} 
		while (opcion!=4);
	}
	
	public static int opcionValida(int valMax, int valMin)
	{
		boolean valida = false;
		int opcion=0;
		while(!valida) 
		{
			if (!in.hasNextInt() || (opcion=in.nextInt())>valMax || opcion<valMin) 
			{
				System.out.print("Opcion no valida, Escribe otra: ");
				in.nextLine();
				continue;
			}
			in.nextLine();
			valida=true;
		}
		return opcion;
	}
	
	private static void partidaJugadorVSPc() 
	{
		partidasPvPC+=1;
		tablero.iniciar();
		int turno=0;
		boolean turnoJugador1=new Random().nextBoolean();
		System.out.println("Las fichas a elegir son:\n1. X\n2. O");
		System.out.print("Seleccione una opción: ");
		tablero.elegirFichasJugador1(opcionValida(2, 1));
		System.out.println();
		tablero.mostrarPosicionesTablero();
		while(!terminaPartida()) 
		{
			if (!terminaPartida() && turnoJugador1) 
			{
				int posicion;
				System.out.print("\nTurno Jugador \nEscribe movimiento: ");
				while(!in.hasNextInt() || !tablero.movimientoValido(posicion=in.nextInt())) 
				{
					System.out.print("Opcion no valida, Escribe otra:");
					in.nextLine();
				}
				tablero.mueveJugador(posicion,1);
				in.nextLine();
				tablero.guardarTablero(turno);
				turno++;
				turnoJugador1=false;
			}
			if (!terminaPartida() && !turnoJugador1) 
			{
				System.out.println("\nTurno Maquina");
				tablero.mueveOrdenador(2);
				tablero.guardarTablero(turno);
				turno++;
				turnoJugador1=true;
			}
		}
	}
	
	private static void partidaJugadorVSJugador() 
	{
		partidasPvPC+=1;
		tablero.iniciar();
		boolean turnoJugador1=new Random().nextBoolean();
		int turno=0;
		elegirFichasJugadorVSJugador(turnoJugador1);
		System.out.println();
		tablero.mostrarPosicionesTablero();
		while(!terminaPartida()) 
		{
			int posicion;
			if (!terminaPartida() && turnoJugador1) 
			{
				System.out.print("\nTurno Jugador 1\nEscribe movimiento: ");
				while(!in.hasNextInt() || !tablero.movimientoValido(posicion=in.nextInt())) 
				{
					System.out.print("Opcion no valida, Escribe otra:");
					in.nextLine();
				}
				tablero.mueveJugador(posicion,1);
				in.nextLine();
				tablero.guardarTablero(turno);
				turno++;
				turnoJugador1=false;
			}
			if (!terminaPartida() && !turnoJugador1) 
			{
				System.out.print("\nTurno Jugador 2\nEscribe movimiento: ");
				while(!in.hasNextInt() || !tablero.movimientoValido(posicion=in.nextInt())) 
				{
					System.out.print("Opcion no valida, Escribe otra:");
					in.nextLine();
				}
				tablero.mueveJugador(posicion,2);
				in.nextLine();
				tablero.guardarTablero(turno);
				turno++;
				turnoJugador1=true;
			}
		}
	}
	
	private static void partidaPcVSPc() 
	{
		tablero.mostrarPosicionesTablero();
		tablero.iniciar();
		boolean turnoJugador1=new Random().nextBoolean();
		int turno=0;
		while(!terminaPartida()) 
		{
			if (!terminaPartida() && turnoJugador1) 
			{
				System.out.println("\nTurno Maquina1");
				tablero.mueveOrdenador(1);
				tablero.guardarTablero(turno);
				turno++;
				turnoJugador1=false;
			}
			if (!terminaPartida() && !turnoJugador1) 
			{
				System.out.println("\nTurno Maquina2");
				tablero.mueveOrdenador(2);
				tablero.guardarTablero(turno);
				turno++;
				turnoJugador1=true;
			}
		}
	}
	
	private static void recrearPartida() {
		int indx=0;
		int subOpcion=0;
		System.out.println("\nRecreando partida\n");
		do 
		{
			tablero.dibujaTableroAnterior(indx);
			System.out.println("1. Siguiente Turno\n2. Anterior Turno\n3. Salir");
			System.out.print("Seleccione una opción: ");
			subOpcion=opcionValida(3, 1);
			if (subOpcion==1) 
			{
				indx++;
				if (indx>tablero.getTurnosGuardados()-1) 
				{
					indx=0;
				}
			}
			if (subOpcion==2) 
			{
				indx--;
				if (indx<0) 
				{
					indx=tablero.getTurnosGuardados()-1;
				}
			}
			if (subOpcion==3)
			{
				System.out.println("\nRegresando...");
			}
		} 
		while (subOpcion!=3);
	}
	
	private static void elegirFichasJugadorVSJugador(boolean turnoJugador1) 
	{
		System.out.println("Las fichas a elegir son:\n1. X\n2. O");
		if (turnoJugador1) 
		{
			System.out.print("Seleccione una opción Jugador1: ");
			tablero.elegirFichasJugador1(opcionValida(2, 1));
		}
		else 
		{
			System.out.print("Seleccione una opción Jugador2: ");
			tablero.elegirFichasJugador2(opcionValida(2, 1));
		}	
	}
	
	private static boolean terminaPartida() 
	{
		return !(tablero.ganaJugador1()==tablero.ganaJugador2() && tablero.quedanMovimientos());
	}
	
	private static void mensajeFinalPartida(int modalidad) 
	{
		if (tablero.ganaJugador1()) 
		{
			if (modalidad==1) 
			{
				jugadorPvPCWin+=1;
				System.out.println("Has Ganado contra la Pc, Felicidades");
			}
			if (modalidad==2) 
			{
				jugador1PvPWin+=1;
				System.out.println("Ha Ganado el Jugador1, Felicidades\nMejor suerte para la proxima Jugador2");
			}
			if (modalidad==3) 
			{
				Pc1PCvPCWin+=1;
				System.out.println("Ha Ganado la Pc1");				
			}
			return;
		}
		if (tablero.ganaJugador2()) 
		{
			if (modalidad==1) 
			{
				PcPvPCWin+=1;
				System.out.println("Has Perdido contra la Pc");					
			}
			if (modalidad==2) 
			{
				jugador2PvPWin+=1;
				System.out.println("Ha Ganado el Jugador2, Felicidades \nMejor suerte para la proxima Jugador1");				
			}
			if (modalidad==3) 
			{
				Pc2PCvPCWin+=1;
				System.out.println("Ha Ganado la Pc2");
			}
			return;
		}
		System.out.println("Empate nadie Gana, nadie Pierde");			

	}
	
	private static void mostrarEstadisticas() 
	{
		System.out.println("\nEstadisticas: ${partidasTotales}");
		System.out.println("Partidas Totales: "+partidasTotales+"\n"+"---------------"+"\n"+
		"Partidas Jugador vs Maquina: "+partidasPvPC+"\n"+"Victorias Jugador: "+jugadorPvPCWin+"\n"+"Victorias Maquina: "+PcPvPCWin+
		"\n"+"Empates: "+(partidasPvP-(jugadorPvPCWin+PcPvPCWin))+"\n"+"---------------"+"\n"+
		"Partidas Jugador vs Jugador: "+partidasPvP+"\n"+"---------------"+"\n"+
		
		"Partidas Maquina vs Maquina: "+(partidasTotales-(partidasPvP+partidasPvPC)));
	}
}
