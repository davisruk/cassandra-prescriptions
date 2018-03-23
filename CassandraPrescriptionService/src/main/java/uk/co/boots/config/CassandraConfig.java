package uk.co.boots.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import lombok.Getter;

@Getter
@Configuration
@Profile("prod")
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${cassandra.peerService}")
	private String cassandraPeerService;
	
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


	@Override
	public String getContactPoints() {
		StringJoiner sj = new StringJoiner(",");
		System.out.println("Cassandra Peer Service: " + cassandraPeerService);
		System.out.println("IP Resolution:");
		try {
			Arrays.stream(InetAddress.getAllByName(cassandraPeerService)).forEach(x -> {
				String ip = CassandraConfig.getIpAddress(x.getAddress());
				System.out.println(ip);
				sj.add(ip);
			});
		}catch (UnknownHostException uhe) {
			System.out.println("Unknown Host Exception");
			log.severe(uhe.getMessage());
		}
		System.out.println("All IP Addresses: " + sj.toString());
		return sj.toString();
	}

	public static String getIpAddress(byte[] rawBytes) {
		int i = 4;
		String ipAddress = "";
		for (byte raw : rawBytes) {
			ipAddress += (raw & 0xFF);
			if (--i > 0) {
				ipAddress += ".";
			}
		}
		return ipAddress;
	}
}
