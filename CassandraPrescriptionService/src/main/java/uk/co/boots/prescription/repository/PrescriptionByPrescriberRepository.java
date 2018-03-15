package uk.co.boots.prescription.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.prescription.entity.PrescriptionByPrescriberEntity;

public interface PrescriptionByPrescriberRepository extends CassandraRepository<PrescriptionByPrescriberEntity, UUID> {
	public List<PrescriptionByPrescriberEntity> findByPrescriberId (UUID id);
	public PrescriptionByPrescriberEntity findByPrescriberIdAndPrescriptionDateAndId (UUID prescriberId, LocalDateTime prescriptionDate, UUID prescriptionId);	
}
