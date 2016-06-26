package model;

import org.springframework.data.annotation.Id;

public class Employee {
	@Id
	String employee_Id;
	
//	boolean checked;
//	public boolean isChecked() {
//		return checked;
//	}
//
//	public void setChecked(boolean checked) {
//		this.checked = checked;
//	}
	String firstName;
	String lastName;
	String role;
	
	public Employee(String firstName, String lastName, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	
	public Employee() {

	}
	
	@Override
	public String toString() {
		return "Employee [employee_Id=" + employee_Id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", role=" + role + "]";
	}

	public String getEmployee_Id() {
		return employee_Id;
	}

	public void setEmployee_Id(String employee_Id) {
		this.employee_Id = employee_Id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
