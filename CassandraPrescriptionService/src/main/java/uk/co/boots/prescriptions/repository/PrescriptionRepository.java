package uk.co.boots.prescriptions.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import uk.co.boots.prescriptions.entity.PrescriptionByDateEntity;

@NoRepositoryBean
public interface PrescriptionRepository extends CassandraRepository<PrescriptionByDateEntity, UUID> {

}
