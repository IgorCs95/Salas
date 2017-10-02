package PercistenciasTipos;

import java.util.ArrayList;

import Persistencia.PersistenciaXml;
import entidades.Alocar;
import entidades.Evento;

public class PercistenciaEvento implements Percistencias<Evento>{
	
	private ArrayList<Evento> listaEvento = new ArrayList<Evento>();
	private PersistenciaXml<Evento> eventos = new PersistenciaXml<Evento>();
	private String urlEventos="Eventos.xml";
	
	public PercistenciaEvento() {
		listaEvento = eventos.recuperar(urlEventos);
	}

	public void salvar(Evento evento) {
		listaEvento.add(evento);
		eventos.salvar(listaEvento,urlEventos);
	}

	public void excluir(String IdObjeto) {
		for(Evento evento : listaEvento){
			if(evento.getId().equals(IdObjeto)){
				listaEvento.remove(evento);
				break;
			}
		}
		eventos.salvar(listaEvento, urlEventos);
	}

	public ArrayList<Evento> todos() {
		return listaEvento;
	}

	public Evento recuperar(String IdObjeto) {
		for(Evento eve:listaEvento){
			if(eve.getId().equals(IdObjeto)){
				return eve;
			}
		}
		return null;
	}

	public void zerar() {
		listaEvento.clear();
		eventos.salvar(listaEvento, urlEventos);
	}

	public void excluirAlocarSala(String IdObjeto) {
		
	}

	public void excluirAlocarEvento(String IdObjeto) {
		
	}

	
}
