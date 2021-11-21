package Gramatica;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Inicio {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica.txt");
		
		GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);//hay que verificar las excepciones cuando entra en un ciclo porque no esta bien la gramatica
	   gestorgm.procedimiento("abcd");
		

	}		

	
}
