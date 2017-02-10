package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Employee;
import model.FlatPaidEmployee;
import model.HourlyPaidEmployee;

@Stateless
public class EmployeeDAO {
	@PersistenceContext
	EntityManager entityManager;

	public List<Employee> findAllEmployee() {
		List<Employee> employeeList = entityManager.createQuery("select e from Employee e", Employee.class)
				.getResultList();
		return employeeList;
	}

	public List<HourlyPaidEmployee> findAllHourlyPaidEmployee() {
		List<HourlyPaidEmployee> employeeList = entityManager
				.createQuery("select e from HourlyPaidEmployee e", HourlyPaidEmployee.class).getResultList();
		return employeeList;
	}

	public List<FlatPaidEmployee> findAllFlatPaidEmployee() {
		List<FlatPaidEmployee> employeeList = entityManager
				.createQuery("select e from FlatPaidEmployee e", FlatPaidEmployee.class).getResultList();
		return employeeList;
	}

	public Employee findEmployeeById(int id) {
		Employee employee = entityManager.createQuery("select e from Employee e where id = " + id, Employee.class)
				.getSingleResult();
		return employee;
	}

	public HourlyPaidEmployee findHourlyPaidEmployeeById(int id) {
		HourlyPaidEmployee employee = entityManager
				.createQuery("select e from Employee e where id = " + id, HourlyPaidEmployee.class).getSingleResult();
		return employee;
	}

	public FlatPaidEmployee findFlatPaidEmployeeById(int id) {
		FlatPaidEmployee employee = entityManager
				.createQuery("select e from Employee e where id = " + id, FlatPaidEmployee.class).getSingleResult();
		return employee;
	}

	public void add(Employee employee) {
		entityManager.persist(employee);
		entityManager.flush();
	}

	public Employee modifyByEmployee(int idEmp, String paymentMethod, String postalAddress, String bankAccount) {
		entityManager.createQuery("update Employee set paymentMethod = '" + paymentMethod + "', postalAddress = '"
				+ postalAddress + "', bankAccount = '" + bankAccount + "' where id = " + idEmp).executeUpdate();
		return findEmployeeById(idEmp);
	}

	public void modifyByAdmin(Employee employee) {
		entityManager.merge(employee);
		entityManager.flush();
	}

	public Employee modifyByServiceCharge(int idEmp, float totalDues) {
		entityManager.createQuery("update Employee set totalDues = " + totalDues + " where id = " + idEmp).executeUpdate();
		return findEmployeeById(idEmp);
	}

	public void remove(Employee employee) {
		Employee requiredEmployee = entityManager.contains(employee) ? employee : entityManager.merge(employee);
		entityManager.createQuery("delete from Employee where id = '" + requiredEmployee.getId() + "'").executeUpdate();
	}

	public String getEmployeeType(int idEmp) {
		Employee employee = findEmployeeById(idEmp);
		return employee.getContractType();
	}

}
