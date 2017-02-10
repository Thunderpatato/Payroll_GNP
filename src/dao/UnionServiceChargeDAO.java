package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Employee;
import model.UnionServiceCharge;

@Stateless
public class UnionServiceChargeDAO {

	@PersistenceContext
	EntityManager entityManager;

	public void submitUnionServiceCharge(UnionServiceCharge unionServiceCharge) {
		Employee employee = entityManager.find(Employee.class, unionServiceCharge.getEmployee().getId());
		unionServiceCharge.setEmployee(employee);
		entityManager.persist(unionServiceCharge);
		entityManager.flush();
	}

	public List<UnionServiceCharge> findAllUnionServiceCharges() {
		List<UnionServiceCharge> unionChargeList = entityManager
				.createQuery("select e from UnionServiceCharge e", UnionServiceCharge.class).getResultList();
		return unionChargeList;
	}

	public List<UnionServiceCharge> findAllTheUnionServiceCharges(int idEmp) {
		List<UnionServiceCharge> unionChargeList = entityManager
				.createQuery("select e from UnionServiceCharge e where idEmp = " + idEmp, UnionServiceCharge.class)
				.getResultList();

		return unionChargeList;
	}
}
