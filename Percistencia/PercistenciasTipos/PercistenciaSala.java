package PercistenciasTipos;

import java.util.ArrayList;

import Persistencia.PersistenciaXml;
import entidades.Sala;

public class PercistenciaSala implements Percistencias<Sala>{
	
	private ArrayList<Sala> listaSalas = new ArrayList<Sala>();
	private PersistenciaXml<Sala> salas = new PersistenciaXml<Sala>();
	private String urlSalas="Salas.xml";
	
	public PercistenciaSala() {
		listaSalas = salas.recuperar(urlSalas);
	}
	
	public void salvar(Sala objeto) {
		listaSalas.add(objeto);
		salas.salvar(listaSalas, urlSalas);
	}

	public void excluir(String IdObjeto) {
		for(Sala sala : listaSalas){
			if(sala.getId().equalsIgnoreCase(IdObjeto)){
				listaSalas.remove(sala);
				break;
			}
		}
		salas.salvar(listaSalas, urlSalas);
		
		
	}

	public ArrayList<Sala> todos() {
		return listaSalas;
	}

	public Sala recuperar(String IdObjeto) {
		for(Sala sala: listaSalas){
			if(sala.getId().equalsIgnoreCase(IdObjeto)){
				return sala;
			}
		}
		return null;
	}

	public void zerar() {
		listaSalas.clear();
		salas.salvar(listaSalas, urlSalas);
	}

	public void excluirAlocarSala(String IdObjeto) {
		
	}

	public void excluirAlocarEvento(String IdObjeto) {
		
	}


}
