package entities;

public class Activity {
	
    public final static int NO_INICIADA = 1;
	public final static int EN_CURSO = 2;
	public final static int FINALIZADA = 3;
    

	private Stage stage;
	private int duration;
	private String description ;
	private int state ;
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		switch(state) {
		case NO_INICIADA: return "No iniciada"  ;
		case EN_CURSO: return "En curso" ;
		case FINALIZADA: return "Finalizada" ;
		}
		return "";
	}
	public void setState(int state) {
		this.state = state;
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	
}
