	package uk.co.boots.prescription.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.prescription.entity.PrescriptionByPatientEntity;

public interface PrescriptionByPatientRepository extends CassandraRepository<PrescriptionByPatientEntity, UUID> {
	public List<PrescriptionByPatientEntity> findByPatientId (UUID id);
	public PrescriptionByPatientEntity findByPatientIdAndPrescriptionDateAndId (UUID patientId, LocalDateTime prescriptionDate, UUID prescriptionId);
}
