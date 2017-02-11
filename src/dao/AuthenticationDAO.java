package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Authentication;
import model.Employee;

@Stateless
public class AuthenticationDAO {
	@PersistenceContext
	EntityManager entityManager;

	public void add(Authentication authentication) {
		Employee employee = entityManager.find(Employee.class, authentication.getEmployee().getId());
		authentication.setEmployee(employee);
		entityManager.persist(authentication);
		entityManager.flush();
	}

	public String validate(int idEmp, String password) {
		Authentication authentication = entityManager.createQuery(
				"select l from Authentication l where idEmp = " + idEmp + "and password = '" + password + "'",
				Authentication.class).getSingleResult();
		if (authentication.isAdmin() == Boolean.TRUE) {
			return "admin";
		} else
			return "employee";
	}

}
