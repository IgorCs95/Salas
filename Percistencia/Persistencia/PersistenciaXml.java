package Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**Classe reponsavel por buscar e salvar um arquivo xml.
 * 
 * @author Igor
 *
 * @param <T> Tipo da lista passada.
 */
public class PersistenciaXml<T> {
	
	/** Metodo generico responsalvel por salvar um object em um arquivo xml.
	 * @param object e o objeto para ser salvo dento do arquivo.
	 * @param nomeArquivo caminho do arquivo xml.
	 */
	public void salvar(Object object, String nomeArquivo){
		XStream xStream = new XStream(new DomDriver());
		String xml = xStream.toXML(object);
		
		try {
			File file = new File(nomeArquivo);
			
			if (!file.exists())
				file.createNewFile();
			
			PrintWriter escritor = new PrintWriter(file);
			escritor.write(xml);
			escritor.flush();
			escritor.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	/** Recupera o arquivo para que possa ser trabalhado.
	 * @param nomeArquivo caminho do arquivo a ser aberto.
	 * @return Lista de objeto definidos quando intanciado a clase.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<T> recuperar(String nomeArquivo){
		File file = new File(nomeArquivo);
		XStream xStream = new XStream(new DomDriver());
		ArrayList<T> lista = new ArrayList<T>();
		try {
			if (!file.exists()){
				salvar(new ArrayList<T>(), nomeArquivo);
			}
			
			FileReader ler = new FileReader(file);
			lista = (ArrayList<T>) xStream.fromXML(ler);
			
		} catch (FileNotFoundException e) {
			return lista;
		}
			
		return lista;
	}
	
	
}