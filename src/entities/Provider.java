package entities;

public class Provider {

	public final static int CATEGORY_A = 1;
	public final static int CATEGORY_B = 2;
	public final static int CATEGORY_C = 3;
	
	public final static int APROBADO = 1;
	public final static int DESAPROBADO = 2;

	
	
	private String business_name;
	private String name;
	private String surname;
	private String description;
	private int state;
	private int category;
	private String email;
	private String address;
	private String phone;
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
        switch (state) {
        case APROBADO:
            return "APROBADO";
        case DESAPROBADO:
            return "DESAPROBADO";
    }
    return "";
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCategory() {
        switch (category) {
        case CATEGORY_A:
            return "A";
        case CATEGORY_B:
            return "B";
        case CATEGORY_C:
            return "C";
    }
    return "";
		
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
}
