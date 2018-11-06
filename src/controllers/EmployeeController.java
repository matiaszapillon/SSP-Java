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

	public void deleteUser(int idUser) {
		this.getEmployeeData().deleteUser(idUser);

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

}
