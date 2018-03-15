package uk.co.boots.prescription.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.prescription.entity.PrescriptionByStoreEntity;

public interface PrescriptionByStoreRepository extends CassandraRepository <PrescriptionByStoreEntity, UUID>{
	public PrescriptionByStoreEntity findByStoreIdAndPrescriptionDateAndId(UUID storeId, LocalDateTime prescriptionDate, UUID prescriptionId);
	public List<PrescriptionByStoreEntity> findByStoreId(UUID storeId);
	public List<PrescriptionByStoreEntity> findByStoreIdAndPrescriptionDateGreaterThan(UUID storeId, LocalDateTime date); 
	public List<PrescriptionByStoreEntity> findByStoreIdAndPrescriptionDateLessThan(UUID storeId, LocalDateTime date);
}
