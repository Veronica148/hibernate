package com.demo;

import org.apache.log4j.Logger;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.data.Service;
import com.data.ServiceDAO;

public class StartApp {

	private static GenericXmlApplicationContext cnt;

	private static final Logger LOGGER = Logger.getLogger(StartApp.class);

	public static void main(String[] args) {

		cnt = new GenericXmlApplicationContext();
		cnt.load("classpath:AppContext.xml");
		cnt.refresh();

		ServiceDAO serviceDao = cnt.getBean("serviceDAO", ServiceDAO.class);

		for (Service service : serviceDao.selectAll()) {
			LOGGER.info(service);
		}

		Long id = (long) 7;
		LOGGER.info("Title for id " + id + " is " + serviceDao.findById(id));

		Service service = new Service();
		service.setId((long) 7);
		service.setTitle("face massage");
		service.setPrice(800);

		serviceDao.update(service);

		Service serviceInst = new Service();
		serviceInst.setTitle("melirovanie");
		serviceInst.setPrice(600);
		serviceDao.insert(serviceInst);
		LOGGER.info("New id:" + serviceInst.getId());

		serviceDao.delete((long) serviceInst.getId());

		for (Service service2 : serviceDao.selectAll()) {
			LOGGER.info(service2);
		}
	}
}
