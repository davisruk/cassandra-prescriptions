package uk.co.boots.prescriptions.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.prescriptions.entity.PrescriptionByPatientEntity;
import uk.co.boots.prescriptions.entity.PrescriptionByPrescriberEntity;

public interface PrescriptionByPrescriberRepository extends CassandraRepository<PrescriptionByPrescriberEntity, UUID> {
	public PrescriptionByPrescriberEntity findByPrescriberId (UUID id);
}
