package br.com.wnfasolutions.comercio.config.database;

import java.net.URISyntaxException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class DataConfigMySQLRailway {

	@Bean
	public BasicDataSource dataSource() throws URISyntaxException {
		String username = System.getenv("MYSQLUSER");
		String password = System.getenv("MYSQLPASSWORD");
		String dbUrl = "jdbc:mysql://" + System.getenv("MYSQLHOST") + ":" + System.getenv("MYSQLPORT") + "/"
				+ System.getenv("MYSQLDATABASE");

		System.out.println("DB URL: " + dbUrl);
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(dbUrl);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);

		return basicDataSource;
	}
}
