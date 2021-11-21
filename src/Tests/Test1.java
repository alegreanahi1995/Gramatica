package Tests;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import Gramatica.GestorGramatica;


import Gramatica.Archivo;
class Test1 {

	@Test
	void test() {
		
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("abcd")==false);
		

	}
	
	

	@Test
	void test1() {
		
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("abc"));
		

	}

	
	
	@Test
	void test2() {
		
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("ab")==false);
		

	}
}
