package com.epam.hibern.barber.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DAO {
	
	private static final Logger LOGGER = Logger.getLogger(DAO.class);
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	protected DAO(){
	}
	
	public static Session getSession(){
		Session session = (Session) DAO.session.get();
		if (session == null) {
			session = sessionFactory.openSession();
			DAO.session.set(session);
		}
		return session;
	}
	
	protected void begin(){
		getSession().beginTransaction();
	}
	
	protected void commit(){
		getSession().getTransaction().commit();
	}
	
	protected void rollback(){
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException ex) {
			LOGGER.error("Can not rollback");
		}
		try {
			getSession().close();
		} catch (HibernateException e) {
			LOGGER.error("Can not close");
		}
	}
	
	/*
	 * close session
	 */
	public static void close(){
		getSession().close();
		DAO.session.set(null);
	}

}
