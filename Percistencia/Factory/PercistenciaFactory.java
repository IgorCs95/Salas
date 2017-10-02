package Factory;

import PercistenciasTipos.Percistencias;

public abstract class PercistenciaFactory {
	
	public Percistencias pedir(String tipo){
		return criarPercistencia(tipo);
	}
	
	public abstract Percistencias criarPercistencia(String tipo);

}
