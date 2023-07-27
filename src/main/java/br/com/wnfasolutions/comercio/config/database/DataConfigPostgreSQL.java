package br.com.wnfasolutions.comercio.config.database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class DataConfigPostgreSQL {
	@Bean
    public BasicDataSource dataSource(){

		String dbUrl = "jdbc:postgresql://" + System.getenv("PGHOST") + ':' + System.getenv("PGPORT") +"/"+ System.getenv("PGDATABASE");
        String username = System.getenv("PGUSER");
        String password = System.getenv("PGPASSWORD");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
}
