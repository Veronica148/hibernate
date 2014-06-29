package com.epam.hibern.barber.dao;

import java.sql.Timestamp;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.TransactionException;

import com.epam.hibern.barber.entity.Employee;
import com.epam.hibern.barber.entity.Record;

public class RecordDAO extends DAO {

	private static final Logger LOGGER = Logger.getLogger(RecordDAO.class);

	public Record createRecord(Timestamp date, String r_name, String r_surname,
			String phone, String e_name, String e_surname) {
		LOGGER.info("Creating record with params: " + date + " " + r_name + " "
				+ r_surname + " " + phone + " for master: " + e_name + " "
				+ e_surname);
		try {
			Record record = new Record(date, r_name, r_surname, phone);

			EmployeeDAO emplDAO = new EmployeeDAO();
			Employee empl = emplDAO.retrieveEmployee(e_name, e_surname);
			record.setEmployees(Arrays.asList(empl));
			begin();

			getSession().saveOrUpdate(record);
			getSession().saveOrUpdate(empl);
			commit();

			return record;
		} catch (HibernateException e) {
			rollback();
			throw new TransactionException("Error during commit had occured");
		}
	}

	public Record retrieveRecord(Timestamp date, String r_surname) {
		LOGGER.info("Retrieving record by surname " + r_surname + " for "
				+ date);
		try {
			begin();
			Query q = getSession()
					.createQuery(
							"from Record where date = :date and r_surname = :r_surname");
			q.setString("date", date.toString());
			q.setString("r_surname", r_surname);
			Record record = (Record) q.uniqueResult();
			commit();
			return record;
		} catch (HibernateException e) {
			rollback();
			throw new TransactionException("Couldn't get record for "
					+ r_surname);
		}
	}

	public void deleteRecord(Record record) {
		LOGGER.info("Deleting record for " + record.getR_surname());
		try {
			begin();
			getSession().delete(record);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new TransactionException("Couldn't delete record "
					+ record.getR_surname());
		}
	}

}
