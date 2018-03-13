package uk.co.boots.prescriptions.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.prescriptions.entity.PrescriptionByStoreEntity;

public interface PrescriptionByStoreRepository extends CassandraRepository <PrescriptionByStoreEntity, UUID>{
	public PrescriptionByStoreEntity findByStoreIdAndId(UUID storeId, UUID prescriptionId);
	public List<PrescriptionByStoreEntity> findByStoreId(UUID storeId);
	public List<PrescriptionByStoreEntity> findByStoreIdAndPrescriptionDateGreaterThan(UUID storeId, LocalDateTime date); 
	public List<PrescriptionByStoreEntity> findByStoreIdAndPrescriptionDateLessThan(UUID storeId, LocalDateTime date);
}
