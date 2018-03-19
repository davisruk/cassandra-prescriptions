package uk.co.boots.practice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.practice.entity.PracticeByPrescriberEntity;

public interface PracticeByPrescriberRepository extends CassandraRepository<PracticeByPrescriberEntity, UUID>{
	public List<PracticeByPrescriberEntity> findByPrescriberId(UUID id);
	public PracticeByPrescriberEntity findByPrescriberIdAndPracticeNameAndPracticeId(UUID prescriberId, String practiceName, UUID practiceId);
}
