package Gramatica;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestorGramatica {
	
	private List<String> noTerminales;
	
	private List<Character> terminales;

	private List<Character> simbolosdeEntrada;
	private List<List<String>> segundos;

	private List<List<String>> primero;

	private List<List<String>> tabla;
	
public GestorGramatica(List<String> lineas) {
		
		this.noTerminales = new ArrayList<String>();
		this.terminales = new ArrayList<Character>();
		
		for (String line : lineas) {
			
			agregarNoTerminal(Reconocedor.reconocerVariable(line));
						
			String body = Reconocedor.reconocerBody(line);
			agregarNoTerminales(Reconocedor.reconocerNoTerminalesxbody(body));
			agregarTerminales(Reconocedor.reconocerTerminalesxbody(body));

		}
	}

	

	private void agregarNoTerminal(String noTerminal) {
		if(!noTerminal.isEmpty() && !noTerminales.contains(noTerminal)) {
			noTerminales.add(noTerminal);				
		}
	}
	
	private void agregarNoTerminales(List<String> noTerminales) {
		for (String noTerminal : noTerminales) {
			agregarNoTerminal(noTerminal);
		}
	}
	
	private void agregarTerminal(Character terminal) {
		if(!terminales.contains(terminal)) {
			terminales.add(terminal);				
		}
	}
	
	private void agregarTerminales(List<Character> reconocerTerminalesEnBody) {
		for (Character terminal : reconocerTerminalesEnBody) {
			agregarTerminal(terminal);
		}
		
	}
	
	public List<String> getNoTerminales(){
		return this.noTerminales;
	}
	
	public List<Character> getTerminales(){
		return this.terminales;
	}
	
	public void obtenerPrimeros(List<String> lineas) {
		primero=new ArrayList<List<String>>();
		for(String noterminales: this.noTerminales)
		{

			primero.add(obtenerPrimeros(lineas,noterminales));
		}
		
	
		 
		
	}
	public List<String> obtenerPrimeros(List<String> lineas,String variable) {
	List<String> primeros=new ArrayList<String>();
	StringBuilder str = new StringBuilder();
	for (Character s : terminales) {
		str.append(s);
	}
	String body="";
		for(String produccion: lineas)
		{
			
			if(Reconocedor.reconocerVariable(produccion).compareTo(variable)==0)
		{	String expregular = "^X_\\{\\d*\\}.*";
			List<String> lista = new ArrayList<String>();
			
		    Pattern p = Pattern.compile(expregular);  
		    body=Reconocedor.reconocerBody(produccion);
		    Matcher m = p.matcher(body);
		   
		    if(m.find())
		    	{
		    	int pos=body.indexOf("}");
		    	primeros.addAll(obtenerPrimeros(lineas,body.substring(0,pos+1)));
		    	}

			expregular = "^["+str+"].*";
			lista = new ArrayList<String>();
			
		     p = Pattern.compile(expregular);  
		     body=Reconocedor.reconocerBody(produccion);
		     m = p.matcher(body);
		   
		    if(m.find())
		    	{
		    	primeros.add(body.substring(0, 1));
		    	}

		   
		}    
		
		}

		return primeros;
	}
	


	
	
	

	

	public int posicion(String variable)
	{
		int pos=0;
		for(String noterminales: this.noTerminales)
		{

			if(noterminales.compareTo(variable)==0)
			{
				return pos;
			}
			pos++;
		}
		return -1;
	}
	
	public void obtenerSegundos(List<String> lineas) {


		tabla=new ArrayList<List<String>>();
		String body="";
		int sincambios=1;
		StringBuilder str = new StringBuilder();
		for (Character s : terminales) {
			str.append(s);
		}
		
		for(int i=0;i<this.noTerminales.size();i++)
		{
			tabla.add(new ArrayList<String>());
		}
		

		while(sincambios>0) {
			sincambios=0;
		for(String noterminales: this.noTerminales)
		{

			String variable=noterminales;
			Set<String> set = new HashSet<>();

			List<String> notermagregarsig_x=new ArrayList<String>();
			for(String produccion: lineas)
			{
				body=Reconocedor.reconocerBody(produccion);
				String varproduc=Reconocedor.reconocerVariable(produccion);
				if(Reconocedor.reconocerNoTerminalesxbody(body).contains(variable))
			{	

					
					if(!body.endsWith(variable))
					 {	
						String expregular = "X_\\{\\d*\\}["+str+"]";
						List<String> lista = new ArrayList<String>();
							
						Pattern p = Pattern.compile(expregular);  
						    
						Matcher m = p.matcher(body);
						int pos=body.indexOf("}",body.indexOf(variable));

				    	
					    if(m.find())
					    	{
					    	set.add(body.substring(pos+1,pos+2));
					    	}
					    
					    else {
					    	int pos3=body.indexOf("}",body.indexOf(variable));
					    //aca debe entrar tambien si la variable que esta atras tiene primeros vacios.
					   //si es asi entonces debe actuar como si estuviera la variable al final
					   List<String> lista2=this.obtenerPrimeros(lineas, body.substring(pos3+1,body.indexOf("}", pos3+2)+1));
					    	if(!lista2.contains("E"))
					    	{
					    		set.addAll(lista2);
								   
					    	}
					    	else
					    	{
					    	
					    		lista2.remove("E");
					    		set.addAll(lista2);
					    	//	notermagregarsig_x.add(body.substring(pos3+1,body.indexOf("}", pos3+2)+1));
					    	}
					    
					    }
					 }
					 else {

						 
					Set<String>s=new HashSet(tabla.get(this.posicion(Reconocedor.reconocerVariable(produccion))));
					s.addAll(set);
					set=s;		
					 }
					}    
				
			}


			set.add("$");
			Set<String> s=new HashSet(tabla.get(this.posicion(variable)));

			s.addAll(set);
			
			Set<String> conjacomparar=new HashSet<String>(tabla.get(this.posicion(variable)));
			
			
			if(!conjacomparar.containsAll(s))
			{

				conjacomparar.addAll(s);
				List<String> primeros=new ArrayList<String>(conjacomparar);
				int posvar=this.posicion(variable);
				tabla.remove(posvar);
				tabla.add(posvar,primeros);


				sincambios++;
			}
			
		}
		}
		
	

	}
	
	
	
	
	public void crearTabladeParsing(List<String> lineas)
	{
		this.obtenerPrimeros(lineas);
		this.obtenerSegundos(lineas);
		simbolosdeEntrada=new ArrayList<>();
		for(Character c: terminales)
		{
			if(c!='E')
			simbolosdeEntrada.add(c);
		}
		simbolosdeEntrada.add('$');
		tabla=new ArrayList<List<String>>();
	    

		
		   for (int i=0;i<this.noTerminales.size();i++) {
			   List<String> produccionxsimbolo=new ArrayList<String>();
				for(int j=0;j<this.simbolosdeEntrada.size();j++)
				{
				
					if(this.primero.get(i).contains(String.valueOf(simbolosdeEntrada.get(j))))
					{
						List<String> produccion=new ArrayList<String>();
						
						produccion=this.produccionquecoincide(lineas,noTerminales.get(i),simbolosdeEntrada.get(j));
						
					if(produccion.size()<=1) {

							if(produccion.size()==1) {
							produccionxsimbolo.addAll(produccion);
							}
							else
							{
								produccionxsimbolo.add(" ");
							}
						}
						else {
							System.out.print("NO es LL1");
							return;
					}
					}
					else
					{
						if(this.simbolosdeEntrada.get(j)=='$'){
							List<String> produccion=new ArrayList<String>();
							
							produccion=this.produccionquecoincide(lineas,noTerminales.get(i),'E');
							if(produccion.size()!=0)
							produccionxsimbolo.addAll(produccion);
							else produccionxsimbolo.add(" ");
						}
						
						else produccionxsimbolo.add(" ");
					}
						
					
				}
				tabla.add(produccionxsimbolo);
	
			}
		
		
		   
		
	}
	

	
	public List<String> produccionquecoincide(List<String> lineas,String variable,Character c)
	{
		List<String>proddevariableconcaracterc=new ArrayList<String>();
		String body="";
		for(String produccion: lineas)
		{
			

			body=Reconocedor.reconocerBody(produccion);
		if(Reconocedor.reconocerVariable(produccion).compareTo(variable)==0) {
		  
		  
		    String expregular = "^X_\\{\\d*\\}.*";
			List<String> lista = new ArrayList<String>();
			
		    Pattern p = Pattern.compile(expregular);  
		    body=Reconocedor.reconocerBody(produccion);
		    Matcher m = p.matcher(body);
		   
		    if(m.find())
		    	{
		    	int pos=body.indexOf("}");
		    	if(obtenerPrimeros(lineas,body.substring(0,pos+1)).contains(String.valueOf(c)))
		    	{
		    		proddevariableconcaracterc.add(produccion);
		 		   
		    	}
		    	}
		    
			expregular = "^["+c+"].*";
			lista = new ArrayList<String>();
			
		     p = Pattern.compile(expregular);  
		     body=Reconocedor.reconocerBody(produccion);
		     m = p.matcher(body);
		   
		    if(m.find())
		    	{
	    		proddevariableconcaracterc.add(produccion);
		    	}

		  
		  
		}
		}
		return proddevariableconcaracterc;
}

	public String devolverProduccion(String variable,Character caracter)
	{
		
		for (int i=0;i<tabla.size();i++) {
					for(int j=0;j<tabla.get(i).size();j++)
					{

						if(this.simbolosdeEntrada.get(j)==caracter && 
								this.noTerminales.get(i).compareTo(variable)==0)

						return tabla.get(i).get(j);
						
					}
		
				}
		
		return null;
	}
	
	
	public boolean procedimiento(String n)
	{
		
		
		List<String> pila=new ArrayList<String>();
		pila.add("$");
		pila.add("X_{1}");
		String tope="X_{1}";
		n+="$";
		StringBuilder entrada = new StringBuilder(n);
		Character a=entrada.charAt(0);

		StringBuilder str = new StringBuilder();
		for (Character s : terminales) {
			str.append(s);
		}
		
		while(tope.compareTo("$")!=0)
		{
			
			
			if(tope.compareTo(Character.toString(a))==0)
			{
				pila.remove(pila.size()-1);
				entrada.deleteCharAt(0);
				a=entrada.charAt(0);
			}
			else
			{
				

		
				if(tope.compareTo(Character.toString(a))!=0 && tope.length()==1)
				{
					System.out.print("ERROR");
					return false;
				}

				String produccion=devolverProduccion(pila.get(pila.size()-1), a);
				if(produccion.compareTo(" ")==0)
				{
					System.out.print("ERROR");
					return false;
				}
				else
				{

					System.out.print(produccion);

					pila.remove(pila.size()-1);

					int posicion=0;
					String body=Reconocedor.reconocerBody(produccion);
				
					List<String> pilaalreves=new ArrayList<String>();
					while(posicion<body.length()) {
					  String expregular = "^X_\\{\\d*\\}";
						List<String> lista = new ArrayList<String>();
						
					    Pattern p = Pattern.compile(expregular);  
					    Matcher m = p.matcher(body);
					    if(m.find())
					    	{

					    	
					    	int pos=body.indexOf("}");
					    	pilaalreves.add(body.substring(0,pos+1));
					    	posicion+=pos+1;
					    	body=body.replace(m.group(),"");
							    
					    	}
					 
					    else { 
						expregular = "^["+str+"]";
						lista = new ArrayList<String>();
						
					     p = Pattern.compile(expregular);  
					     m = p.matcher(body);
					    if(m.find())
					    	{
					    	
				    		posicion=posicion+1;
				    		body=body.replaceFirst(Character.toString(body.charAt(0)), "");
				    		pilaalreves.add(m.group());
					    	}
					    }
					
					}
					ListIterator iter = pilaalreves.listIterator(pilaalreves.size());
					while (iter.hasPrevious())
					      pila.add(iter.previous().toString());
				}
			}
			
			tope=pila.get(pila.size()-1);
		}


		if(entrada.length()==1)
		{
			System.out.print("ES ACEPTADO");
			return true;
		}
		else {
			System.out.print("NO ES ACEPTADO"+entrada);
			return false;
		}
		
	}
}
