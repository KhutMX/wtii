package com.aserta;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.aserta.business.interfaces.IUserSignUpService;
import com.aserta.business.layer.UserSignUpService;
import com.aserta.data.interfaces.IUsersRepository;
import com.aserta.data.layer.OracleUsersRepository;
import com.aserta.operational.management.DefaultLogger;
import com.aserta.operational.management.ILogger;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@PropertySource("application.properties")
public class WhatTimeIsItApplicationConfiguration {
	
	@Value("${oracleUser}")
	private String oracleUser;
	
	@Value("${oraclePassword}")
	private String oraclePassword;
	
	@Value("${oracleURL}")
	private String oracleURL;
	
	@Bean
	public IUserSignUpService getUserSignUpService() throws SQLException{
		IUsersRepository usersRepository = getUsersRepository();
		ILogger logger = getLogger();
		
		return new UserSignUpService(usersRepository, logger);
	}
	
	private IUsersRepository getUsersRepository() throws SQLException{
		DataSource sqlServerDataSource = getSqlServerDataSource();
		return new OracleUsersRepository(sqlServerDataSource);
	}
	
	private DataSource getSqlServerDataSource() throws SQLException{
		OracleDataSource dataSource = new OracleDataSource();
		
		dataSource.setUser(oracleUser);
		dataSource.setPassword(oraclePassword);
		//dataSource.setURL("jdbc:oracle:thin:@192.168.2.128:1521:sblbcq");
		dataSource.setURL(oracleURL);

		return dataSource;
	}
	
	private ILogger getLogger() {
		return new DefaultLogger();
	}
	
}
