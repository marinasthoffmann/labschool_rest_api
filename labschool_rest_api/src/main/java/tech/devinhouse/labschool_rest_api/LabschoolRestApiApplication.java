package tech.devinhouse.labschool_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class LabschoolRestApiApplication {

	public static void main(String[] args) {
		String envVar = System.getenv().get("MARIADBPASSWORD");
		SpringApplication.run(LabschoolRestApiApplication.class, args);
	}

}
