package com.epam.hibern.barber.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 7756507093508999961L;

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employee_id;

	@Column(name = "e_name", length = 100)
	private String e_name;

	@Column(name = "e_surname", length = 100)
	private String e_surname;

	@Column(name = "category", length = 100)
	private String category;

	@ManyToMany(mappedBy = "employees")
	private List<Service> services;

	@ManyToMany(mappedBy = "employees")
	private List<Record> records;

	public Employee() {
	}

	public Employee(String e_name, String e_surname, String category) {
		super();
		this.e_name = e_name;
		this.e_surname = e_surname;
		this.category = category;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public String getE_name() {
		return e_name;
	}

	public void setE_name(String e_name) {
		this.e_name = e_name;
	}

	public String getE_surname() {
		return e_surname;
	}

	public void setE_surname(String e_surname) {
		this.e_surname = e_surname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

}
