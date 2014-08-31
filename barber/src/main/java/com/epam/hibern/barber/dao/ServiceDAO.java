package com.epam.hibern.barber.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.TransactionException;

import com.epam.hibern.barber.entity.Service;

public class ServiceDAO extends DAO {

	private static final Logger LOGGER = Logger.getLogger(ServiceDAO.class);

	public Service createService(String title, Long price) {
		LOGGER.info("Creating service with params: " + title + " " + price);
		try {
			begin();
			Service service = new Service(title, price);
			getSession().save(service);
			commit();
			return service;
		} catch (HibernateException e) {
			rollback();
			throw new TransactionException("Error during commit had occured");
		}
	}

	public Service retrieveService(String title) {
		LOGGER.info("Retrieving service " + title);
		try {
			begin();
			Query q = getSession().createQuery(
					"from Service where title = :title");
			q.setString("title", title);
			Service service = (Service) q.uniqueResult();
			commit();
			return service;
		} catch (HibernateException e) {
			rollback();
			throw new TransactionException("Couldn't get service by title: "
					+ title);
		}
	}

	public void deleteService(Service service) {
		LOGGER.info("Deleting service " + service.getTitle());
		try {
			begin();
			getSession().delete(service);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new TransactionException("Couldn't delete service "
					+ service.getTitle());
		}
	}

}
