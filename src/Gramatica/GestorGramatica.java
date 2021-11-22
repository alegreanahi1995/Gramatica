package Gramatica;
import java.util.ArrayList;
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

	
    //agrega los no terminales 

	private void agregarNoTerminal(String noTerminal) {
		if(!noTerminal.isEmpty() && !noTerminales.contains(noTerminal)) {
			noTerminales.add(noTerminal);				
		}
	}
	
	//agrega lo no terminales segun el body
	
	private void agregarNoTerminales(List<String> noTerminales) {
		for (String noTerminal : noTerminales) {
			agregarNoTerminal(noTerminal);
		}
	}
	
	//agrega terminales segun el body
	private void agregarTerminal(Character terminal) {
		
	}
	
	//agrega terminales
	private void agregarTerminales(List<Character> reconocerTerminalesEnBody) {
		for (Character terminal : reconocerTerminalesEnBody) {
			if(!terminales.contains(terminal)) {
				terminales.add(terminal);				
			}
		}
		
	}
	
	//devuelve los no terminales
	public List<String> getNoTerminales(){
		return this.noTerminales;
	}
	
	//devuelve los terminales
	public List<Character> getTerminales(){
		return this.terminales;
	}
	
	//obtiene primeros de cada variable
	public void obtenerPrimeros(List<String> lineas) {
		primero=new ArrayList<List<String>>();
		for(String noterminales: this.noTerminales)
		{

			primero.add(obtenerPrimeros(lineas,noterminales));
		}
		
	
		 

		System.out.println();
		System.out.println(".............PRIMEROS..........");
	for(int i=0;i<this.primero.size();i++)
	{


		System.out.println();
		System.out.print("VARIABLE:"+this.noTerminales.get(i)+"Primeros: ");
		for(int j=0;j<this.primero.get(i).size();j++)
		{
			System.out.print(this.primero.get(i).get(j));
		}
	}
		
	}
	
	//obtiene primeros dado una variable
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
	


	
	
	

	
//devuelve la posicion en la que se encuentra una variable en la lista de no terminales
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
	
	
	//obtiene segundos de las variables
	public void obtenerSegundos(List<String> lineas) {


		segundos=new ArrayList<List<String>>();
		String body="";
		int sincambios=1;
		StringBuilder str = new StringBuilder();
		for (Character s : terminales) {
			str.append(s);
		}
		
		for(int i=0;i<this.noTerminales.size();i++)
		{

			segundos.add(new ArrayList<String>());
		}
		

		while(sincambios>0) {
			sincambios=0;
		for(String noterminales: this.noTerminales)
		{

			String variable=noterminales;
			Set<String> set = new HashSet<>();

			for(String produccion: lineas)
			{
				body=Reconocedor.reconocerBody(produccion);
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
					    	}
					    
					    }
					 }
					 else {

						 
					Set<String>saux=new HashSet(segundos.get(this.posicion(Reconocedor.reconocerVariable(produccion))));
					saux.addAll(set);
					set=saux;		
					 }
					}    
				
			}


			set.add("$");
			Set<String> s=new HashSet(segundos.get(this.posicion(variable)));

			s.addAll(set);
			
			Set<String> conjacomparar=new HashSet<String>(segundos.get(this.posicion(variable)));
			
			
			if(!conjacomparar.containsAll(s))
			{

				conjacomparar.addAll(s);
				List<String> primeros=new ArrayList<String>(conjacomparar);
				int posvar=this.posicion(variable);
				segundos.remove(posvar);
				segundos.add(posvar,primeros);


				sincambios++;
			}
			
		}
		}
		
	
		
		System.out.println();
		System.out.println(".............SEGUNDOS..........");
	for(int i=0;i<this.segundos.size();i++)
	{


		System.out.println();
		System.out.print("VARIABLE:"+this.noTerminales.get(i)+"Segundos: ");
		for(int j=0;j<this.segundos.get(i).size();j++)
		{
			System.out.print(this.segundos.get(i).get(j));
		}
	}
		
		
	}
	
	
	//crea la tabla de parsing antes obtiene primeros y segundos de cada variable y en base a eso va construyendo.
	
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

						if(this.segundos.get(i).contains(String.valueOf(this.simbolosdeEntrada.get(j)))){
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
		   
		   
		   System.out.println();
		System.out.println(".......................TABLA DE PARSING...............");
		   for (int i=0;i<tabla.size();i++) {
			   System.out.println();
			   System.out.print("VARIABLE:"+this.noTerminales.get(i));
			   
					for(int j=0;j<tabla.get(i).size();j++)
					{

						System.out.print("Caracter:"+this.simbolosdeEntrada.get(j)+" ");
						System.out.print("Produccion:"+tabla.get(i).get(j)+ " ");
						
					}
					System.out.println();
		
				}
		   
	
	}
	

	//verifica que produccion coincide dado una variable y un caracter 
	
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

	//devuelve la produccion que debe tomar dado una variable y caracter. esto lo obtiene de la tabla de parsing
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
	
	
	//realiza el procedimiento para verificar si una cadena es aceptada por la gramatica 
	public boolean procedimiento(String n)
	{
		
		System.out.println();
		System.out.println(".....ALGORITMO PARA VER SI ACEPTA LA CADENA...");
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

			System.out.println();
			System.out.print("TOPE: "+tope+" ENTRADA: "+entrada+" A:"+a+" PILA: ");
			
			for(int i=0;i<pila.size();i++)
			{
				System.out.print(pila.get(i));
			}
			System.out.print("  ");
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

					System.out.print("Produccion: "+produccion+"  ");

					int posicion=0;
					String body=Reconocedor.reconocerBody(produccion);
					if(body.compareTo("E")!=0) {
					pila.remove(pila.size()-1);

					int tamaño=body.length();
					List<String> pilaalreves=new ArrayList<String>();
					while(posicion<tamaño) {
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
				    	    body=body.substring(1,body.length());
				    		String mt=m.group();
				    		pilaalreves.add(mt);
					    	}
					    }
					
					}
					
					
					ListIterator iter = pilaalreves.listIterator(pilaalreves.size());
					while (iter.hasPrevious())
					      pila.add(iter.previous().toString());
					
					
					}
					
					else {



							pila.remove(pila.size()-1);
							//entrada.deleteCharAt(0);
							//a=entrada.charAt(0);
						
					}
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
			System.out.print("NO ES ACEPTADO");
			return false;
		}
		
	}
}
