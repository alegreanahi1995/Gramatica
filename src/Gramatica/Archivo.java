package Gramatica;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Archivo {

	public static List<String> devolverLineasdeArchivo(String nombredearchivo) {
		List<String> lines = new ArrayList<>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			File file = new File(nombredearchivo); 
			fr = new FileReader(file); 
			br = new BufferedReader(fr); 
			String line = "";
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			fr.close();
			br.close();
			return lines;
		}
		catch(Exception e) {
			return null;
		}
	}
}
