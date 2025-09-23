package Retos;
import java.util.Scanner;

public class Principal 
{

	static Scanner sc=new Scanner(System.in);
	static JuegoBuscaMinas juego=new JuegoBuscaMinas();

	public static void main(String[] args) 
	{
		boolean primerJuego=true;
		int op=0;
		System.out.println("====================\n"
				+ "     BUSCAMINAS\n"
				+ "====================");
		do 
		{
			menuOpciones();
			System.out.print("Introduce una opcion: ");
			EsUnNumero();
			op=sc.nextInt();
			sc.nextLine();
			switch (op) {
			case 1:
				tutoMensaje();
				if(primerJuego) 
				{
					primerJuego=false;
				}
				break;
			case 2:
				dificultad();
				break;
			case 3:
				if (primerJuego) 
				{
					System.out.println("\nInstrucciones");
					tutoMensaje();
					primerJuego=false;
				}
				flujoJuego();
				break;
			case 4:
				System.out.println("Hasta Luego!!!");
				break;
			default:
				System.out.println("Elige una opción correcta!");
				break;
			}
			
		} while (op!=4);
		sc.close();
	}
	
	private static void menuOpciones() 
	{
		System.out.println("\n1- Instrucciones"
				+ "\n2- Configura el juego "
				+ "\n3- Nuevo juego"
				+ "\n4- Salir.\n");
	}
	
	private static void menuDificultad() 
	{
		System.out.println("\n1- Principante"
				+ "\n2- Amateur "
				+ "\n3- Profesional "
				+ "\n4- Regresar.\n");
	}
	
	private static void dificultad() 
	{
		int op=0;
		do 
		{
			menuDificultad();
			System.out.print("Introduce una opcion: ");
			EsUnNumero();
			op=sc.nextInt();
			sc.nextLine();
			switch (op) 
			{
			case 1:
				System.out.println("La dificultad cambió a Principiante");
				juego.setDificultad(1);
				return;
			case 2:
				System.out.println("La dificultad cambió a Amateur");
				juego.setDificultad(2);
				return;
			case 3:
				System.out.println("La dificultad cambió a Profesional");
				juego.setDificultad(3);
				return;
			case 4:
				System.out.println("No se ha cambiado la dificultad.");
				break;
			default:
				System.out.println("Elige una opción correcta!");
				break;
			}
		} while (op!=4);
		
	}
	
	private static void flujoJuego() 
	{
		int causaTerminar=0;
		juego.iniciarTablero();
		String movimiento="";
		do 
		{
			System.out.println();
			juego.imprimirTablero();
			System.out.print("\nIntroduce movimiento: ");
			movimiento=sc.nextLine();
			while (!movimientoVálido(movimiento)) 
			{
				System.out.print("\nIntroduzca un movimiento válido: ");
				movimiento=sc.nextLine();
			}
			causaTerminar=juego.causaTerminacionJuego();
			mensajeFinalJuego(causaTerminar);
		} while (causaTerminar==0);
			
	}

	private static void mensajeFinalJuego(int causaTerminar) 
	{
		if (causaTerminar==1) 
		{
			System.out.println();
			juego.imprimirTablero();
			System.out.println("\nHa encontrado todas las minas, Felicidades");
		}
		if (causaTerminar==4) 
		{
			System.out.println();
			juego.imprimirTablero();
			System.out.println("\nHa esquivado todas las minas, Felicidades");
		}
		if (causaTerminar==2) 
		{
			System.out.println("\n¡BOOM! Ha explotado una mina. Fin del juego.");
		}
		if (causaTerminar==3) 
		{
			System.out.println("\nHa marcado un espacio vacio. Fin del juego.");
		}
	}
	
	private static void EsUnNumero() 
	{
		while (!sc.hasNextInt()) 
		{
			System.out.println("Introduzca un numero válido: ");
			sc.next();
		}
	}
	
	private static boolean movimientoVálido(String mov) 
	{
		if (!formatoValido(mov)) 
		{
			return false;
		}
		char movimiento=Character.toLowerCase(mov.charAt(0));
		mov=mov.substring(mov.indexOf(' ')+1);
		String[] numeros=mov.split(",");
		posicionesSonInt(numeros);
		if (!posicionesSonInt(numeros)) 
		{
			return false;
		}
		int intFila=Integer.parseInt(numeros[0])-1;
		int intColumna=Integer.parseInt(numeros[1])-1;
		if (movimiento=='d' && !juego.descubrirCasilla(intFila, intColumna)) 
		{
			return false;
		}
		if (movimiento=='m' && !juego.marcarCasilla(intFila, intColumna)) 
		{
			return false;
		}
		return true;
	}

	private static boolean posicionesSonInt(String[] numeros) 
	{
		if (numeros.length!=2) 
		{
			return false;
		}
		for (int j = 0; j < numeros.length; j++) 
		{
			for (int i = 0; i < numeros[j].length(); i++) 
			{
				if (!Character.isDigit(numeros[j].charAt(i))) 
				{
					return false;
				}
			}			
		}
		return true;
	}

	private static boolean formatoValido(String mov) 
	{
		if (mov.length()<5) 
		{
			return false;
		}
		if (mov.equals("")) 
		{
			return false;
		}
		if (mov.charAt(mov.length()-1)==' '||mov.charAt(0)==' ') 
		{
			return false;
		}
		char movimiento=Character.toLowerCase(mov.charAt(0));
		if (movimiento!='m' && movimiento!='d') 
		{
			return false;
		}
		if (mov.charAt(1)!=' ') 
		{
			return false;
		}
		if (!mov.contains(",")) 
		{
			return false;
		}
		return true;
	}
	
	private static void tutoMensaje() 
	{
		System.out.println("\nLos numeros a introducir tienen que estar en el rango de '"
				+ (1 + "-" + ((juego.getDificultad() * 4) + 4))
				+ "' y las opciones son solo 'm' para marcar y 'd' para descubrir\n"
				+ "La sintaxis para escribir el movimiento es: 'opcion espacio fila,columna' Ejemplo d 1,1\n"
				+ "\nIntroduciendo 'm' seguido de un espacio y luego de la fila y columna separadas\n"
				+ "por una coma para marcar una casilla con una bandera. Ejemplo: 'm 1,1'\n"
				+ "\nIntroduciendo 'd' seguido de un espacio y luego de la fila y columna separadas\n"
				+ "por una coma para descubrir una casilla. Ejemplo: 'd 1,1'");
	}
}
