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
@Table(name = "SalesCard")
public class SalesCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEmp", referencedColumnName = "id")
	private FlatPaidEmployee flatPaidEmployee;

	private Date selectedData;
	private float sale;

	public SalesCard() {
	}

	public Date getSelectedData() {
		return selectedData;
	}

	public void setSelectedData(Date selectedData) {
		this.selectedData = selectedData;
	}

	public float getSale() {
		return sale;
	}

	public void setSale(float sale) {
		this.sale = sale;
	}

	public FlatPaidEmployee getFlatPaidEmployee() {
		return flatPaidEmployee;
	}

	public void setFlatPaidEmployee(FlatPaidEmployee flatPaidEmployee) {
		this.flatPaidEmployee = flatPaidEmployee;
	}

}
