package uk.co.boots.patient.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.patient.entity.PatientByRegionEntity;

public interface PatientByRegionRepository extends CassandraRepository<PatientByRegionEntity, String> {
	public List<PatientByRegionEntity> findAllByRegion(String region);
}
