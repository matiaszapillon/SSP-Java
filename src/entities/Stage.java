package entities;

public class Stage {
	
    public final static int NO_INICIADA = 1;
	public final static int EN_CURSO = 2;
	public final static int FINALIZADA = 3;
	
	private String name;
	private String description;
	private int state;

	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getState() {
		switch(state) {
		case NO_INICIADA: return "No iniciada"  ;
		case EN_CURSO: return "En curso" ;
		case FINALIZADA: return "Finalizada" ;
		}
		return "";
	}
}
