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
		

		System.out.println();
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("abcd")==false);
		

	}
	
	

	@Test
	void test1() {
		
		System.out.println();
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("abc"));
		

	}

	
	
	@Test
	void test2() {

		System.out.println();
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("ab")==false);
		

	}
	
	
	@Test
	void test3() {
		
		System.out.println();
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica2.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("i+(i+i)"));
		

	}
	
	
	
	@Test
	void test4() {
		
		System.out.println();
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica3.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("abba"));
		

	}
	
	
	
	@Test
	void test5() {
		
		System.out.println();
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica4.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("aaaaa"));
		

	}
	
	
	@Test
	void test6() {
		
		System.out.println();
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica5.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("ab"));
		

	}
	
	
	@Test
	void test7() {
		
		System.out.println();
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica6.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("bbdd"));
		

	}
	
	
	@Test
	void test8() {
		
		System.out.println();
	   List<String> lineas = Archivo.devolverLineasdeArchivo(".\\Archivos\\gramatica6.txt");
		
	   GestorGramatica gestorgm = new GestorGramatica(lineas);
		
	   System.out.println("Terminales: " + gestorgm.getTerminales());
	   System.out.println("No Terminales " + gestorgm.getNoTerminales());
	   gestorgm.crearTabladeParsing(lineas);
	   assertTrue(gestorgm.procedimiento("bdd")==false);
		

	}
}
