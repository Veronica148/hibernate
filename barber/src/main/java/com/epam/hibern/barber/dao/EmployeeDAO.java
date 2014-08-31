package com.epam.hibern.barber.dao;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.TransactionException;

import com.epam.hibern.barber.entity.Employee;
import com.epam.hibern.barber.entity.Service;

import exceptions.TranzactionException;

public class EmployeeDAO extends DAO {

	private static final Logger LOGGER = Logger.getLogger(EmployeeDAO.class);

	public Employee createEmployee(String e_name, String e_surname,
			String category, String serviceTitle) {
		try {
			Employee empl = new Employee(e_name, e_surname, category);

			ServiceDAO serviceDAO = new ServiceDAO();
			Service service = serviceDAO.retrieveService(serviceTitle);
			service.setEmployees(Arrays.asList(empl));
			begin();
			getSession().saveOrUpdate(empl);
			getSession().saveOrUpdate(service);

			commit();
			return empl;

		} catch (HibernateException e) {
			throw new TranzactionException("Couldn't create employee " + e_name
					+ " " + e_surname + " exception " + e);
		}
	}

	public Employee retrieveEmployee(String e_name, String e_surname) {

		LOGGER.info("Retrieving employee by name: " + e_name + " and surname: "
				+ e_surname);
		try {
			begin();
			Query q = getSession()
					.createQuery(
							"from Employee where e_name = :e_name and e_surname = :e_surname");
			q.setString("e_name", e_name);
			q.setString("e_surname", e_surname);
			Employee employee = (Employee) q.uniqueResult();
			commit();
			return employee;
		} catch (HibernateException e) {
			rollback();
			throw new TransactionException("Couldn't get employer " + e_name
					+ " " + e_surname);
		}
	}
}
