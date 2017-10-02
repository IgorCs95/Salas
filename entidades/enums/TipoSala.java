package enums;

/** 
 * @author Igor
 *
 */
public enum TipoSala {
	
	NORMAL ("normal"), 
	INTELIGENTE("inteligente"), 
	VIDIOCONFERENCIA ("videoconferencia"), 
	QUIMICA("quimica"), 
	FISICA("fisica"), 
	BIOLOGIA ("biologia"), 
	COMPUTACAO("computacao");

	public String tipo;
	
	TipoSala(String tipo){
		this.tipo = tipo;
	}
}
