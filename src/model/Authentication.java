package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Authentication")
public class Authentication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "idEmp", referencedColumnName = "id")
	@OneToOne(cascade = CascadeType.ALL)
	private Employee employee;

	private String password;
	private String isAdmin;

	public Authentication() {
	}

	public Authentication(Employee employee, String password, String isAdmin) {
		this.employee = employee;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public Employee getEmployee(){
		return employee;
	}
	
	public void setEmployee(Employee employee){
		this.employee = employee;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String isAdmin() {
		return isAdmin;
	}

	public void setAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
}
