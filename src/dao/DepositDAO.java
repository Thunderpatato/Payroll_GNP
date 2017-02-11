package dao;

import java.sql.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Deposit;
import model.Employee;

@Stateless
public class DepositDAO {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void add(Deposit deposit){
		Employee emp = entityManager.find(Employee.class, deposit.getEmployee().getId());
		deposit.setEmployee(emp);
		entityManager.persist(deposit);
		entityManager.flush();
	}
	
	public Deposit findByDate(int idEmp, Date selectedDate) {
		Deposit deposit = entityManager.createQuery("select p from Deposit p where "
				+ "idEmp ="+ idEmp +" and date = '" + selectedDate + "'",Deposit.class).getSingleResult();
		return deposit;
	}

}
