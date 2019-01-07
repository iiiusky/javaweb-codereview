package org.javaweb.codereview.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * Druid 数据源配置,JDBC 中的连接数据库的密码需要用此类中的passwordGenerator生成后才能使用
 *
 * @author yz
 */
@Configuration
public class DataSourceConfig {

	@Autowired
	private Environment env;

	/**
	 * JDBC 密码前缀
	 */
	private static final String PREFIX = "6ab9/dc";

	/**
	 * JDBC 密码后缀
	 */
	private static final String SUFFIX = "f$8d3b5c6";

	@Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
	public DruidDataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		String          prefix     = "spring.datasource.";

		// JDBC 连接密码反解
		String password = env.getProperty(prefix + "password");
		if (password != null) {
			password = new StringBuffer(password).reverse().toString();
			password = password.replaceAll("^" + Pattern.quote(PREFIX), "").replaceAll(Pattern.quote(SUFFIX) + "$", "");
		}

		datasource.setUrl(env.getProperty(prefix + "url"));
		datasource.setUsername(env.getProperty(prefix + "username"));
		datasource.setPassword(password);
		datasource.setDriverClassName(env.getProperty(prefix + "driverClassName"));
		datasource.setInitialSize(Integer.parseInt(env.getProperty(prefix + "initialSize")));
		datasource.setMinIdle(Integer.parseInt(env.getProperty(prefix + "minIdle")));
		datasource.setMaxActive(Integer.parseInt(env.getProperty(prefix + "maxActive")));
		datasource.setMaxWait(Long.parseLong(env.getProperty(prefix + "maxWait")));
		datasource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty(prefix + "timeBetweenEvictionRunsMillis")));
		datasource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty(prefix + "minEvictableIdleTimeMillis")));
		datasource.setValidationQuery(env.getProperty(prefix + "validationQuery"));
		datasource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty(prefix + "testWhileIdle")));
		datasource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty(prefix + "testOnBorrow")));
		datasource.setTestOnReturn(Boolean.parseBoolean(env.getProperty(prefix + "testOnReturn")));
		datasource.setPoolPreparedStatements(Boolean.parseBoolean(env.getProperty(prefix + "poolPreparedStatements")));

		try {
			datasource.setFilters(env.getProperty(prefix + "filters"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return datasource;
	}

	/**
	 * 只是为了消除IDEA无法识别Spring自动创建的jdbcTemplate的警告
	 *
	 * @param dataSource
	 * @return
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * 生成虚假的数据库连接密码用户Spring数据源配置
	 *
	 * @param password
	 * @return
	 */
	public static String passwordGenerator(String password) {
		password = password == null ? "" : password;
		return new StringBuffer(PREFIX + password + SUFFIX).reverse().toString();
	}

	public static void main(String[] args) {
		System.out.println(passwordGenerator("root"));
	}

}