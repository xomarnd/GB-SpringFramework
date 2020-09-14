package ru.geekbrains;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:db.properties")
public class PersistConfig {
  @Value("${jdbc.driver}")
  private String driverClassName;

  @Value("${jdbc.url}")
  private String databaseUrl;

  @Value("${jdbc.username}")
  private String username;

  @Value("${jdbc.password}")
  private String password;

//  @Bean
//  public ProductRepository productRepository(DataSource dataSource) throws SQLException {
//    return new ProductRepository(dataSource);
//  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(driverClassName);
    ds.setUsername(username);
    ds.setPassword(password);
    ds.setUrl(databaseUrl);
    return ds;
  }
}
