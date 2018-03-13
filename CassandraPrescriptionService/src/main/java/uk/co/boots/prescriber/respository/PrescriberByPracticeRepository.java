package uk.co.boots.prescriber.respository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.prescriber.entity.PrescriberByPracticeEntity;

public interface PrescriberByPracticeRepository extends CassandraRepository<PrescriberByPracticeEntity, UUID> {

}
