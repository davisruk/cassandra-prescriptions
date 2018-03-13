	package uk.co.boots.prescriptions.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.prescriptions.entity.PrescriptionByPatientEntity;

public interface PrescriptionByPatientRepository extends CassandraRepository<PrescriptionByPatientEntity, UUID> {
	public PrescriptionByPatientEntity findByPatientId (UUID id);
}
