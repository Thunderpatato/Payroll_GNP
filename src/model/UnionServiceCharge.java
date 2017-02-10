package model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UnionServiceCharge")
public class UnionServiceCharge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date dateOfCharge;
	private float dues;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEmp", referencedColumnName = "id")
	private Employee employee;
	
	public UnionServiceCharge(){
	}

	public Date getDateOfCharge() {
		return dateOfCharge;
	}

	public void setDateOfCharge(Date dateOfCharge) {
		this.dateOfCharge = dateOfCharge;
	}

	public float getDues() {
		return dues;
	}

	public void setDues(float dues) {
		this.dues = dues;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
