package com.data;

import java.util.List;

public interface ServiceDAO {

	public List<Service> selectAll();

	public String findById(Long id);

	public void insert(Service service);

	public void update(Service service);

	public void delete(Long id);

}
