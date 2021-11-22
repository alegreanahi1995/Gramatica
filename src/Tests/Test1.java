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
	
	
	@Test
	void test3() {
		
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica2.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("i+(i+i)"));
		

	}
	
	
	
	@Test
	void test4() {
		
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica3.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("abba"));
		

	}
	
	
	
	@Test
	void test5() {
		
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica4.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("aaaaa"));
		

	}
}
