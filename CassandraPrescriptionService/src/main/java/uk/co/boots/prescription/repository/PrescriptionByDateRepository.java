package uk.co.boots.prescription.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.prescription.entity.PrescriptionByDateEntity;

public interface PrescriptionByDateRepository extends CassandraRepository<PrescriptionByDateEntity, LocalDateTime> {
	public List<PrescriptionByDateEntity> findByPrescriptionDate(LocalDateTime date);
}
