package PercistenciasTipos;

import java.util.ArrayList;

import Persistencia.PersistenciaXml;
import entidades.Alocar;

public class PercistenciaAlocar implements Percistencias<Alocar> {
	
	private ArrayList<Alocar> listaAlocar = new ArrayList<Alocar>();
	private PersistenciaXml<Alocar> alocar = new PersistenciaXml<Alocar>();
	private String urlAlocar="Alocar.xml";
	
	public PercistenciaAlocar() {
		listaAlocar = alocar.recuperar(urlAlocar);
	}

	public void salvar(Alocar objeto) {
		listaAlocar.add(objeto);
		alocar.salvar(listaAlocar,urlAlocar);
	}

	public void excluir(String idEvento) {
		for(Alocar aloc : listaAlocar){
			if(aloc.getIdEvento().equalsIgnoreCase(idEvento)){
				listaAlocar.remove(aloc);
				break;
			}
		}
		alocar.salvar(listaAlocar,urlAlocar);
	}
	

	public ArrayList<Alocar> todos() {
		return listaAlocar;
	}

	public Alocar recuperar(String idEvento) {
		for(Alocar aloc : listaAlocar){
			if(aloc.getIdEvento().equalsIgnoreCase(idEvento)){
				return aloc;
			}
		}
		return null;
	}

	public void zerar() {
		listaAlocar.clear();
		alocar.salvar(listaAlocar, urlAlocar);
	}

	public void excluirAlocarSala(String IdObjeto) {
		for(Alocar alo : listaAlocar){
			if(alo.getIdSala().equalsIgnoreCase(IdObjeto)){
				listaAlocar.remove(alo);
				break;
			}
		}
		alocar.salvar(listaAlocar,urlAlocar);
	}

	public void excluirAlocarEvento(String IdObjeto) {
		for(Alocar alo : listaAlocar){
			if(alo.getIdEvento().equalsIgnoreCase(IdObjeto)){
				listaAlocar.remove(alo);
				break;
			}
		}
		alocar.salvar(listaAlocar,urlAlocar);
	}

	
	

}
