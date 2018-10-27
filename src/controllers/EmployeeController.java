package controllers;
import java.sql.SQLException;

import data.*;
import entities.Employee;

public class EmployeeController {

	private data.EmployeeData employeeData ;
	
	public EmployeeController() {
		employeeData = new data.EmployeeData();		
	}
	

	public data.EmployeeData getEmployeeData() {
		return employeeData;
	}


	public void setEmployeeData(data.EmployeeData employeeData) {
		this.employeeData = employeeData;
	}


	public Employee getEmployeeByIdUser(int id) {
		return getEmployeeData().getEmployeeByIdUser(id) ;
	}
	
	
	
}
