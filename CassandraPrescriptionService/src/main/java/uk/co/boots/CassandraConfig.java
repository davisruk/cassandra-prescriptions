package uk.co.boots;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;

import lombok.Getter;
import uk.co.boots.prescriber.entity.PrescriberByPracticeEntity;
import uk.co.boots.prescriber.repository.PrescriberPracticeBatchRepository;
import uk.co.boots.prescriber.repository.PrescriberPracticeBatchRepositoryImpl;
import uk.co.boots.prescription.entity.PrescriptionByDateEntity;
import uk.co.boots.prescription.repository.PrescriptionRepository;
import uk.co.boots.prescription.repository.PrescriptionRepositoryImpl;

@Getter
@Configuration
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

	@Bean
	public PrescriptionRepository pr(CassandraTemplate cassandraTemplate) {
		CassandraPersistentEntity<?> entity = cassandraTemplate
												.getConverter()
												.getMappingContext()
												.getRequiredPersistentEntity(PrescriptionByDateEntity.class);
		CassandraEntityInformation<PrescriptionByDateEntity, UUID> metadata = new MappingCassandraEntityInformation<>(
																	(CassandraPersistentEntity<PrescriptionByDateEntity>) entity,
																	cassandraTemplate.getConverter()
																	);
		return new PrescriptionRepositoryImpl(metadata, cassandraTemplate);
	}
	
	@Bean
	public PrescriberPracticeBatchRepository practiceByPrescriberRepo(CassandraTemplate cassandraTemplate) {
		CassandraPersistentEntity<?> entity = cassandraTemplate
												.getConverter()
												.getMappingContext()
												.getRequiredPersistentEntity(PrescriberByPracticeEntity.class);
		CassandraEntityInformation<PrescriberByPracticeEntity, UUID> metadata = new MappingCassandraEntityInformation<>(
																	(CassandraPersistentEntity<PrescriberByPracticeEntity>) entity,
																	cassandraTemplate.getConverter()
																	);
		return new PrescriberPracticeBatchRepositoryImpl(metadata, cassandraTemplate);
	}

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

/*
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
*/
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
