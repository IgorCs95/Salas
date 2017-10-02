package Rodar;

import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;
import fachada.Facade;

/**Classe para a execucao dos cas0s de teste.
 * @author Igor
 *
 */
public class PrincipalTestes{
	

	public static void main(String[] args) {
		List<String> arquivos = new ArrayList<String>();
		
		
		arquivos.add("testes/US1.txt");
		arquivos.add("testes/US2.txt");
		arquivos.add("testes/US3.txt");
		arquivos.add("testes/US4.txt");
		arquivos.add("testes/US5.txt");
		arquivos.add("testes/US6.txt");
		arquivos.add("testes/US7.txt");
//		
		
		
		// instanciar fachada
		 Facade fachada = Facade.getInstance();
		 
		 
		// instanciar fachada do EasyAccept
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(fachada, arquivos);
		
		// executar os testes
		eaFacade.executeTests();
		
		// imprimir
		System.out.println(eaFacade.getCompleteResults());
		
		
	}

}
