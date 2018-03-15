package uk.co.boots.prescription.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import uk.co.boots.prescription.entity.PrescriptionByDateEntity;

@NoRepositoryBean
public interface PrescriptionRepository extends CassandraRepository<PrescriptionByDateEntity, UUID> {

}
