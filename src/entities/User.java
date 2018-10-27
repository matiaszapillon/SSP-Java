package entities;

import java.io.Serializable;

/**
 * @author mat
 *
 */
	public class User implements Serializable{
	public final static int EMPLOYEE = 1;
	public final static int CLIENT = 2;
	public final static int ADMINISTRATOR = 3;

	private int id;
	private String email;
	private String username;
	private String password;
	private int type;
	private Employee employee = null;
	private Client client = null;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return this.email;

	}

	public void setEmail(String em) {
		this.email = em;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public String getType() {
		switch (type) {
		case EMPLOYEE:
			return "Empleado";
		case CLIENT:
			return "Cliente";
		case ADMINISTRATOR:
			return "Administrador";
		}
		return "";
	} */

	


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
