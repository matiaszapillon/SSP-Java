package entities;

import java.util.Date;

public class Employee_training {
	
	private String mode ;
	private Boolean outsourced;
	private String state ;
	private float cost;
	private Date startDate;
	private Date endDate;
	private Training training;
	private Employee employee;
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Boolean getOutsourced() {
		return outsourced;
	}
	public void setOutsourced(Boolean outsourced) {
		this.outsourced = outsourced;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

	
	

}
