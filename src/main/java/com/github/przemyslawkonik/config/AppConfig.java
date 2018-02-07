package com.github.przemyslawkonik.config;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.github.przemyslawkonik.converter.BudgetConverter;
import com.github.przemyslawkonik.converter.CategoryConverter;
import com.github.przemyslawkonik.converter.PaymentMethodConverter;
import com.github.przemyslawkonik.converter.PlanConverter;
import com.github.przemyslawkonik.converter.UserConverter;

import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.github.przemyslawkonik" })
@EnableJpaRepositories(basePackages = { "com.github.przemyslawkonik.repository" })
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/budgetDB");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("coderslab");

		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		if(dbUrl!=null) {
			driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
			driverManagerDataSource.setUrl(dbUrl);
			driverManagerDataSource.setUsername(System.getenv("JDBC_DATABASE_USERNAME"));
			driverManagerDataSource.setPassword(System.getenv("JDBC_DATABASE_PASSWORD"));
		}
		return driverManagerDataSource;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("budgetDB");
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		if(dbUrl!=null) {
			Properties jpaProperties = new Properties();
			jpaProperties.put("javax.persistence.jdbc.url", System.getenv("JDBC_DATABASE_URL"));
			jpaProperties.put("javax.persistence.jdbc.user", System.getenv("JDBC_DATABASE_USERNAME"));
			jpaProperties.put("javax.persistence.jdbc.password", System.getenv("JDBC_DATABASE_PASSWORD"));
			jpaProperties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
			jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			emfb.setJpaProperties(jpaProperties);
			emfb.afterPropertiesSet();
		}
		return emfb;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tm = new JpaTransactionManager(emf);
		return tm;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public UserConverter userConverter() {
		return new UserConverter();
	}

	@Bean
	public PaymentMethodConverter paymentMethodConverter() {
		return new PaymentMethodConverter();
	}

	@Bean
	public CategoryConverter categoryConverter() {
		return new CategoryConverter();
	}

	@Bean
	public PlanConverter planConverter() {
		return new PlanConverter();
	}

	@Bean
	public BudgetConverter budgetConverter() {
		return new BudgetConverter();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(userConverter());
		registry.addConverter(paymentMethodConverter());
		registry.addConverter(categoryConverter());
		registry.addConverter(planConverter());
		registry.addConverter(budgetConverter());
	}

}
