package com.epam.hibern.barber.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "record")
public class Record implements Serializable {

	private static final long serialVersionUID = -6432421270072032048L;

	@Id
	@Column(name = "record_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long record_id;

	@Column(name = "date")
	private Timestamp date;

	@Column(name = "r_name", length = 100)
	private String r_name;

	@Column(name = "r_surname", length = 100)
	private String r_surname;

	@Column(name = "phone", length = 100)
	private String phone;

	@ManyToMany
	@JoinTable(name = "record_employee", joinColumns = { @JoinColumn(name = "record_id", referencedColumnName = "record_id") }, inverseJoinColumns = { @JoinColumn(name = "employee_id", referencedColumnName = "employee_id") })
	@Cascade({ CascadeType.SAVE_UPDATE })
	private List<Employee> employees;

	public Record() {
	}

	public Record(Timestamp date, String r_name, String r_surname, String phone) {
		super();
		this.date = date;
		this.r_name = r_name;
		this.r_surname = r_surname;
		this.phone = phone;
	}

	public Long getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Long record_id) {
		this.record_id = record_id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public String getR_surname() {
		return r_surname;
	}

	public void setR_surname(String r_surname) {
		this.r_surname = r_surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
