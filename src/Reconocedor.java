
	import java.util.ArrayList;
	import java.util.List;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	public  class Reconocedor {
		
		private static String expregular = "X_\\{\\d*\\}";
		
		
		
		public static String reconocerVariable(String linea) {
			String[] cadenas = linea.split("->");
			if(cadenas.length == 2) {
				return cadenas[0].trim();
			}
			return "";
		}

		public static String reconocerBody(String linea) {
			String[] cadenas = linea.split("->");
			if(cadenas.length == 2) {
				return cadenas[1].trim();
			}
			return "";
		}

	
		
		public static List<String> reconocerNoTerminalesxbody(String body){
			List<String> lista = new ArrayList<String>();
			
		    Pattern p = Pattern.compile(expregular);  
		    Matcher m = p.matcher(body);

		    while (m.find()) {
	    		if(!lista.contains(m.group()))
	    			lista.add(m.group());
	    	 }

			return lista;
		}
		
		public static List<Character> reconocerTerminalesxbody(String body) {
			List<Character> lista = new ArrayList<Character>();
			String body_n = body.toString();		
			
			body_n = body_n.replaceAll(expregular, "");
				
			for (Character character : body_n.toCharArray()) {
				if(!lista.contains(character))
					lista.add(character);
			}	    

			return lista;
		}

	
}
