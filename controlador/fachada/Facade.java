package fachada;

import execoes.RoomsAllocationException;
import manipuladores.ManipulacaoXml;
import manipuladores.ManipuladorEventos;
import manipuladores.ManipuladorSalas;
import manipuladores.ManipuladorSistema;

public class Facade implements IFacadeRoomsAllocation {

	ManipulacaoXml a = new ManipulacaoXml();
	ManipuladorSalas gerenciarSalas = new ManipuladorSalas(a);
	ManipuladorEventos gerenciarEventos = new ManipuladorEventos(a);
	ManipuladorSistema gerenciarSistema = new ManipuladorSistema(a);

	public void zerarSistema() {
		gerenciarSistema.zerarSistema();

	}

	public void adicionarSala(String id, int capacidade, String finalidade, String tipo)
			throws RoomsAllocationException {

		try {
			gerenciarSalas.adicionarSala(id, capacidade, finalidade, tipo);
		} catch (RoomsAllocationException e) {
			throw new RoomsAllocationException(e.getMessage());
		}

	}

	public void adicionarSala(String id, int capacidade, String finalidade, String tipo, String apelido)
			throws RoomsAllocationException {
		try {
			gerenciarSalas.adicionarSala(id, capacidade, finalidade, tipo, apelido);
		} catch (RoomsAllocationException e) {
			throw new RoomsAllocationException(e.getMessage());
		}

	}

	public void adicionarSala(String id, int capacidade, String finalidade, String tipo, String apelido, boolean aberto)
			throws RoomsAllocationException {

		try {
			gerenciarSalas.adicionarSala(id, capacidade, finalidade, tipo, apelido, aberto);
		} catch (RoomsAllocationException e) {
			throw new RoomsAllocationException(e.getMessage());
		}

	}

	public String getAtributoSala(String idSala, String atributo) throws RoomsAllocationException {
		try {
			return gerenciarSalas.getAtributoSala(idSala, atributo);
		} catch (RoomsAllocationException e) {
			throw new RoomsAllocationException(e.getMessage());
		}
	}

	public void adicionarEvento(String id, String nome, String inicio, String fim, String area, String contato,
			int repeticoes) throws RoomsAllocationException {
		try {
			gerenciarEventos.adicionarEvento(id, nome, inicio, fim, area, contato, repeticoes);
		} catch (RoomsAllocationException e) {
			throw new RoomsAllocationException(e.getMessage());
		}

	}

	public void adicionarEvento(String id, String nome, String inicio, String fim, String area, String contato)
			throws RoomsAllocationException {

		try {
			gerenciarEventos.adicionarEvento(id, nome, inicio, fim, area, contato);
		} catch (RoomsAllocationException e) {
			throw new RoomsAllocationException(e.getMessage());
		}

	}

	public Object getAtributoEvento(String idEvento, String atributo) throws RoomsAllocationException {
		try {
			return gerenciarEventos.getAtributoEvento(idEvento, atributo);
		} catch (RoomsAllocationException e) {
			throw new RoomsAllocationException(e.getMessage());
		}

	}

	public void alocarEvento(String idEvento, String idSala) throws RoomsAllocationException {
		try {
			gerenciarSistema.alocarEvento(idEvento, idSala);
		} catch (RoomsAllocationException e) {

			throw new RoomsAllocationException(e.getMessage());
		}
	}

	public String localizarEvento(String atributo, String valor) throws RoomsAllocationException {
		try {

			return gerenciarSistema.localizarEvento(atributo, valor);
		} catch (RoomsAllocationException e) {
			throw new RoomsAllocationException(e.getMessage());
		}
	}

	public void desalocarEvento(String idEvento) throws RoomsAllocationException {
		try {
			gerenciarSistema.desalocarEvento(idEvento);
		} catch (RoomsAllocationException e) {
			throw new RoomsAllocationException(e.getMessage());
		}
	}

	public void cancelarEvento(String idEvento) throws RoomsAllocationException {
		try {
			gerenciarSistema.cancelarEvento(idEvento);
		} catch (RoomsAllocationException e) {
			throw new RoomsAllocationException(e.getMessage());
		}
	}

	public void removerSala(String idSala) throws RoomsAllocationException {
		try {
			gerenciarSistema.removerSala(idSala);
		} catch (RoomsAllocationException e) {
			throw new RoomsAllocationException(e.getMessage());
		}
	}

}
