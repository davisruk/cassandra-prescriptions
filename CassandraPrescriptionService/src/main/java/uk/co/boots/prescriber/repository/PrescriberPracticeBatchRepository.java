package uk.co.boots.prescriber.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import uk.co.boots.prescriber.entity.PrescriberByPracticeEntity;

@NoRepositoryBean
public interface PrescriberPracticeBatchRepository extends CassandraRepository<PrescriberByPracticeEntity, UUID> {

}
