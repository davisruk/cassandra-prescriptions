package uk.co.boots;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;

import uk.co.boots.prescriber.entity.PrescriberByPracticeEntity;
import uk.co.boots.prescriber.repository.PrescriberPracticeBatchRepository;
import uk.co.boots.prescriber.repository.PrescriberPracticeBatchRepositoryImpl;
import uk.co.boots.prescription.entity.PrescriptionByDateEntity;
import uk.co.boots.prescription.repository.PrescriptionRepository;
import uk.co.boots.prescription.repository.PrescriptionRepositoryImpl;

@Configuration
@Profile({"test", "prod"})
public class RepoBeanConfig {

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

}
