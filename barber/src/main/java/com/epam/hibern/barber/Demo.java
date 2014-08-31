package com.epam.hibern.barber;

import java.sql.Timestamp;

import com.epam.hibern.barber.dao.EmployeeDAO;
import com.epam.hibern.barber.dao.RecordDAO;
import com.epam.hibern.barber.dao.ServiceDAO;
import com.epam.hibern.barber.entity.Service;

public class Demo {
	public static void main(String[] args) {

		// Work with services
		ServiceDAO serviceDAO = new ServiceDAO();
		serviceDAO.createService("massage", (long) 300);
		serviceDAO.createService("face massage", (long) 500);
		serviceDAO.createService("pedicur2", (long) 200);

		Service service = serviceDAO.retrieveService("pedicur2");
		serviceDAO.deleteService(service);

		// work with employees
		EmployeeDAO emplDAO = new EmployeeDAO();
		emplDAO.createEmployee("Vernica", "Lapunka", "middle", "massage");

		// work with records
		RecordDAO recordDAO = new RecordDAO();
		recordDAO.createRecord(Timestamp.valueOf("2007-09-27 14:30:10"),
				"Katja", "Sidorova", "+375257700844", "Vernica4", "Lapunka");

	}
}
