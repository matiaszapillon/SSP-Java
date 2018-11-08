package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import data.*;
import entities.Employee;

public class EmployeeController {

	private data.EmployeeData employeeData;

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
		return getEmployeeData().getEmployeeByIdUser(id);
	}

	public ArrayList<Employee> getEmployeesWithoutUser() {
		try {
			return getEmployeeData().getEmployeesWithoutUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public Employee getEmployeeById(int idPerson) {
		// TODO Auto-generated method stub
		try {
			return this.getEmployeeData().getEmployeeById(idPerson);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public void addUser(Employee e) {
		// TODO Auto-generated method stub
		try {
			this.getEmployeeData().addUser(e);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
