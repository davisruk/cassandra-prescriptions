package uk.co.boots.prescriptions.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import uk.co.boots.prescriptions.entity.PrescriptionByDateEntity;
import uk.co.boots.prescriptions.entity.PrescriptionMappingService;

public class PrescriptionRepositoryImpl extends SimpleCassandraRepository<PrescriptionByDateEntity, UUID> implements PrescriptionRepository{

	@Autowired
	PrescriptionByPatientRepository byPatientRepo;
	@Autowired
	PrescriptionByPrescriberRepository byPrescriberRepo;
	@Autowired
	PrescriptionByStoreRepository byStoreRepo;
	@Autowired
	CassandraTemplate cassandraTemplate;
	@Autowired
	PrescriptionMappingService mapper;
	
	public PrescriptionRepositoryImpl(CassandraEntityInformation<PrescriptionByDateEntity, UUID> metadata,
			CassandraOperations operations) {
		super(metadata, operations);
	}

	@Override
	public <S extends PrescriptionByDateEntity> S insert(S entity) {
		CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
		insertByStore(entity, batchOps);
		insertByPatient(entity, batchOps);
		insertByPrescriber(entity, batchOps);
		batchOps.insert(entity);
		batchOps.execute();
		return entity;
	}
	
	@Override
	public <S extends PrescriptionByDateEntity> List<S> insert(Iterable<S> entities) {
		List<S> result = new ArrayList<>();
		for (S prescription : entities) {
			result.add(insert(prescription));
		}
		return result;
	}

	@Override
	public <S extends PrescriptionByDateEntity> S save(S entity) {
		return insert(entity);
	}

	@Override
	public <S extends PrescriptionByDateEntity> List<S> saveAll(Iterable<S> entities) {
		return super.saveAll(entities);
	}

	@Override
	public void delete (PrescriptionByDateEntity p) {
		CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
		deleteByStore(p, batchOps);
		deleteByPatient(p, batchOps);
		deleteByPrescriber(p, batchOps);
		batchOps.delete(p);
		batchOps.execute();
	}

	@Override
	public void deleteById(UUID id) {
		findById(id).ifPresent(this::delete);
	}
	
	@Override
	public void deleteAll(Iterable<? extends PrescriptionByDateEntity> prescriptions) {
		prescriptions.forEach(this::delete);
	}
	
	@Override
	public void deleteAll() {
		deleteAll(findAll());
	}
	
	
	private void deleteByStore(PrescriptionByDateEntity p, CassandraBatchOperations batchOps) {
		batchOps.delete(byStoreRepo.findByStoreIdAndId(p.getStoreId(), p.getId()));
				
	}

	private void deleteByPatient(PrescriptionByDateEntity p, CassandraBatchOperations batchOps) {
		batchOps.delete(byPatientRepo.findById(p.getPatientId()));
	}

	private void deleteByPrescriber(PrescriptionByDateEntity p, CassandraBatchOperations batchOps) {
		batchOps.delete(byPrescriberRepo.findById(p.getPrescriberId()));
	}

	private void insertByStore(PrescriptionByDateEntity p, CassandraBatchOperations batchOps) {
		batchOps.insert(mapper.toByStore(p));
	}

	private void insertByPatient(PrescriptionByDateEntity p, CassandraBatchOperations batchOps) {
		batchOps.insert(mapper.toByPatient(p));
	}

	private void insertByPrescriber(PrescriptionByDateEntity p, CassandraBatchOperations batchOps) {
		batchOps.insert(mapper.toByPrescriber(p));
	}

}
