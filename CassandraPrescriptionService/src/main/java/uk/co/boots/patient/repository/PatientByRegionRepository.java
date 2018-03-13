package uk.co.boots.patient.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.patient.entity.PatientByRegionEntity;

public interface PatientByRegionRepository extends CassandraRepository<PatientByRegionEntity, String> {

}
