package Gramatica;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Inicio {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
	 System.out.println ("Por favor introduzca la ubicación del archivo:");
	     
	   Scanner entradaEscaner = new Scanner (System.in);

	   String entradaTeclado = entradaEscaner.nextLine (); 
	     
	   List<String> lineas = Archivo.devolverLineasdeArchivo(entradaTeclado);
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);//hay que verificar las excepciones cuando entra en un ciclo porque no esta bien la gramatica
	  
	   entradaEscaner = new Scanner (System.in);

	   entradaTeclado = entradaEscaner.nextLine (); 
	     
	
	   if(gestorgm.procedimiento(entradaTeclado))
			System.out.print("ACEPTA EL STRING");
	   else System.out.print("NO ACEPTA EL STRING");

		

	}		

	
}
