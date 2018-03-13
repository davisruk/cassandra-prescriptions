package uk.co.boots.prescriptions.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.prescriptions.entity.PrescriptionByPrescriberEntity;

public interface PrescriptionByPrescriberRepository extends CassandraRepository<PrescriptionByPrescriberEntity, UUID> {
	public List<PrescriptionByPrescriberEntity> findByPrescriberId (UUID id);
	public PrescriptionByPrescriberEntity findByPrescriberIdAndPrescriptionDateAndId (UUID prescriberId, LocalDateTime prescriptionDate, UUID prescriptionId);	
}
