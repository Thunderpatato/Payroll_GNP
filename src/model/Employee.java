package model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "contractType", discriminatorType = DiscriminatorType.STRING)
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstName;
	private String lastName;
	private String paymentMethod;
	private String bankAccount;
	private String postalAddress;
	private float weeklyDues = 0;
	private float totalDues = 0;
	private boolean inUnion;
	private Date nextPayment;
	private Date lastPayment;

	@Column(name = "contractType", insertable = false, updatable = false)
	private String contractType;

	public Employee() {
		super();
	}

	public Employee(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getId(){
		return id;
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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public float getWeeklyDues() {
		return weeklyDues;
	}

	public void setWeeklyDues(float weeklyDues) {
		this.weeklyDues = weeklyDues;
	}

	public boolean isInUnion() {
		return inUnion;
	}

	public void setInUnion(boolean inUnion) {
		this.inUnion = inUnion;
	}

	public Date getNextPayment() {
		return nextPayment;
	}

	public void setNextPayment(Date nextPayment) {
		this.nextPayment = nextPayment;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public Date getLastPayment() {
		return lastPayment;
	}

	public void setLastPayment(Date lastPayment) {
		this.lastPayment = lastPayment;
	}

	public float getTotalDues() {
		return totalDues;
	}

	public void setTotalDues(float totalDues) {
		this.totalDues = totalDues;
	}

}
