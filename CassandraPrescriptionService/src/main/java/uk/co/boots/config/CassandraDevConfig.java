package uk.co.boots.config;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import lombok.Getter;

@Getter
@Configuration
@Profile("dev")
@EnableCassandraRepositories
@PropertySource(value={"classpath:application-dev.properties"}, ignoreResourceNotFound = true)
public class CassandraDevConfig extends AbstractCassandraConfiguration {

	@Value("${cassandra.port}")
	private int port;
	
	@Value("${cassandra.keySpace}")
	private String keySpace;
	
	@Value("${cassandra.basePackages}")
	private String basePackages;

	@Value("${cassandra.contactPoints}")
	private String contactPoints;


	private static final Logger log = Logger.getLogger(CassandraConfig.class.getName());

	
	@Override
	protected String getKeyspaceName() {
		// TODO Auto-generated method stub
		return getKeySpace();
	}

	@Override
	public String[] getEntityBasePackages() {
		// TODO Auto-generated method stub
		return new String[] {getBasePackages()};
	}

	@Override
	public SchemaAction getSchemaAction() {
		// TODO Auto-generated method stub
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

}
