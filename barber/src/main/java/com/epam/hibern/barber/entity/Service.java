package com.epam.hibern.barber.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "service")
public class Service implements Serializable {

	private static final long serialVersionUID = -993665159090869508L;

	@Id
	@Column(name = "service_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long service_id;

	@Column(name = "title", length = 100, unique = true)
	private String title;

	@Column(name = "price", length = 100)
	private Long price;

	@ManyToMany
	@JoinTable(name = "service_employee", joinColumns = { @JoinColumn(name = "service_id", referencedColumnName = "service_id") }, inverseJoinColumns = { @JoinColumn(name = "employee_id", referencedColumnName = "employee_id") })
	@Cascade({ CascadeType.SAVE_UPDATE })
	private List<Employee> employees;

	public Service() {
	}

	public Service(String title, Long price) {
		super();
		this.title = title;
		this.price = price;
	}

	public Long getService_id() {
		return service_id;
	}

	public void setService_id(Long service_id) {
		this.service_id = service_id;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee empl) {
		getEmployees().add(empl);
	}
}
