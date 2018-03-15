package uk.co.boots.prescriber.respository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.prescriber.entity.PrescriberByPracticeEntity;

public interface PrescriberByPracticeRepository extends CassandraRepository<PrescriberByPracticeEntity, UUID> {

	public List<PrescriberByPracticeEntity> findByPracticeId(UUID practiceId);

}
