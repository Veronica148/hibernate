package com.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ServiceDAOImpl implements ServiceDAO {

	private static final Logger LOGGER = Logger.getLogger(ServiceDAOImpl.class);

	private JdbcTemplate jdbcT;

	private NamedParameterJdbcTemplate namedParamJdbcT;

	private DataSource dataSource;

	public List<Service> selectAll() {

		return jdbcT.query("SELECT * FROM storage.service",
				new RowMapper<Service>() {
					public Service mapRow(ResultSet rs, int arg1)
							throws SQLException {

						Long id = rs.getLong("service_id");
						String title = rs.getString("title");
						int price = rs.getInt("price");
						Service service = new Service();

						LOGGER.info("i am in the mapRow " + arg1);

						service.setId(id);
						service.setTitle(title);
						service.setPrice(price);

						return service;
					}
				});
	}

	public String findById(Long id) {

		/*
		 * String title = jdbcT.queryForObject(
		 * "select title from storage.service where service_id=?", new Object[]
		 * { id }, String.class); return title;
		 */

		String sql = "select title from storage.service where service_id = :id";
		SqlParameterSource namedParams = new MapSqlParameterSource("id", id);

		return namedParamJdbcT.queryForObject(sql, namedParams, String.class);
	}

	public void insert(Service service) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title", service.getTitle());
		paramMap.put("price", service.getPrice());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		class InsertService extends SqlUpdate {
			private static final String SQL_INSERT = "insert into storage.service(title,price) values (:title,:price)";

			public InsertService(DataSource dataSource) {
				super(dataSource, SQL_INSERT);
				super.declareParameter(new SqlParameter("title", Types.VARCHAR));
				super.declareParameter(new SqlParameter("price", Types.VARCHAR));
				super.setGeneratedKeysColumnNames(new String[] { "service_id" });
				super.setReturnGeneratedKeys(true);
			}
		}

		InsertService insertService = new InsertService(dataSource);
		insertService.updateByNamedParam(paramMap, keyHolder);
		service.setId(keyHolder.getKey().longValue());
		LOGGER.info("New service inserted with id: " + service.getId());

	}

	public void update(Service service) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", service.getId());
		paramMap.put("title", service.getTitle());
		paramMap.put("price", service.getPrice());

		class UpdateService extends SqlUpdate {

			private static final String SQL_UPDATE = "update storage.service set title=:title, price=:price where service_id=:id";

			public UpdateService(DataSource dataSource) {
				super(dataSource, SQL_UPDATE);
				super.declareParameter(new SqlParameter("id", Types.INTEGER));
				super.declareParameter(new SqlParameter("title", Types.VARCHAR));
				super.declareParameter(new SqlParameter("price", Types.INTEGER));
			}
		}

		UpdateService updateService = new UpdateService(dataSource);
		updateService.updateByNamedParam(paramMap);
		LOGGER.info("Service updated with id: " + service.getId());
	}

	public void delete(Long id) {
		String query = "delete from storage.service where service_id=?";

		jdbcT.update(query, Long.valueOf(id));
	}

	public void setDataSorce(DataSource dataSorce) {
		this.dataSource = dataSorce;
		this.jdbcT = new JdbcTemplate(dataSource);
		this.namedParamJdbcT = new NamedParameterJdbcTemplate(dataSorce);
	}

}
