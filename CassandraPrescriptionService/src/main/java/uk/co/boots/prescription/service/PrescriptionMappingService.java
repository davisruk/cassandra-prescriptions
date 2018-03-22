package uk.co.boots.prescription.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.patient.dto.PatientDTO;
import uk.co.boots.patient.service.PatientEntityMappingService;
import uk.co.boots.prescriber.service.PrescriberEntityMappingService;
import uk.co.boots.prescription.dto.PrescriptionDTO;
import uk.co.boots.prescription.entity.PrescriptionByDateEntity;
import uk.co.boots.prescription.entity.PrescriptionByPatientEntity;
import uk.co.boots.prescription.entity.PrescriptionByPrescriberEntity;
import uk.co.boots.prescription.entity.PrescriptionByStoreEntity;
import uk.co.boots.store.dto.StoreDTO;
import uk.co.boots.store.service.StoreEntityMappingService;

@Service
public class PrescriptionMappingService {

	@Autowired
	PrescriberEntityMappingService prescriberMapper;
	@Autowired
	PatientEntityMappingService patientMapper;
	@Autowired
	StoreEntityMappingService storeMapper;

	
	public PrescriptionByStoreEntity toByStore (PrescriptionByDateEntity p) {
		return PrescriptionByStoreEntity.builder()
			.id(p.getId())
			.patientFirstName(p.getPatientFirstName())
			.patientSecondName(p.getPatientSecondName())
			.patientId(p.getPatientId())
			.prescriberId(p.getPrescriberId())
			.prescriberPracticeId(p.getPrescriberPracticeId())
			.prescriberPracticeName(p.getPrescriberPracticeName())
			.prescriberFirstName(p.getPrescriberFirstName())
			.prescriberSecondName(p.getPatientSecondName())
			.prescriptionDate(p.getPrescriptionDate())
			.storeId(p.getStoreId())
			.storeName(p.getStoreName())
			.build();
	}
	
	public PrescriptionByPatientEntity toByPatient (PrescriptionByDateEntity p) {
		return PrescriptionByPatientEntity.builder()
			.id(p.getId())
			.patientFirstName(p.getPatientFirstName())
			.patientSecondName(p.getPatientSecondName())
			.patientId(p.getPatientId())
			.prescriberId(p.getPrescriberId())
			.prescriberPracticeId(p.getPrescriberPracticeId())
			.prescriberPracticeName(p.getPrescriberPracticeName())
			.prescriberFirstName(p.getPrescriberFirstName())
			.prescriberSecondName(p.getPatientSecondName())
			.prescriptionDate(p.getPrescriptionDate())
			.storeId(p.getStoreId())
			.storeName(p.getStoreName())
			.build();
	}

	public PrescriptionByPrescriberEntity toByPrescriber (PrescriptionByDateEntity p) {
		return PrescriptionByPrescriberEntity.builder()
			.id(p.getId())
			.patientFirstName(p.getPatientFirstName())
			.patientSecondName(p.getPatientSecondName())
			.patientId(p.getPatientId())
			.prescriberId(p.getPrescriberId())
			.prescriberPracticeId(p.getPrescriberPracticeId())
			.prescriberPracticeName(p.getPrescriberPracticeName())
			.prescriberFirstName(p.getPrescriberFirstName())
			.prescriberSecondName(p.getPatientSecondName())
			.prescriptionDate(p.getPrescriptionDate())
			.storeId(p.getStoreId())
			.storeName(p.getStoreName())
			.build();
	}
	
	public PrescriptionByDateEntity fromByPrescriber (PrescriptionByPrescriberEntity p) {
		return PrescriptionByDateEntity.builder()
			.id(p.getId())
			.patientFirstName(p.getPatientFirstName())
			.patientSecondName(p.getPatientSecondName())
			.patientId(p.getPatientId())
			.prescriberId(p.getPrescriberId())
			.prescriberPracticeId(p.getPrescriberPracticeId())
			.prescriberPracticeName(p.getPrescriberPracticeName())
			.prescriberFirstName(p.getPrescriberFirstName())
			.prescriberSecondName(p.getPatientSecondName())
			.prescriptionDate(p.getPrescriptionDate())
			.storeId(p.getStoreId())
			.storeName(p.getStoreName())
			.build();
	}

	public PrescriptionByDateEntity fromByPatient (PrescriptionByPatientEntity p) {
		return PrescriptionByDateEntity.builder()
			.id(p.getId())
			.patientFirstName(p.getPatientFirstName())
			.patientSecondName(p.getPatientSecondName())
			.patientId(p.getPatientId())
			.prescriberId(p.getPrescriberId())
			.prescriberPracticeId(p.getPrescriberPracticeId())
			.prescriberPracticeName(p.getPrescriberPracticeName())
			.prescriberFirstName(p.getPrescriberFirstName())
			.prescriberSecondName(p.getPatientSecondName())
			.prescriptionDate(p.getPrescriptionDate())
			.storeId(p.getStoreId())
			.storeName(p.getStoreName())
			.build();
	}

	public PrescriptionByDateEntity fromByStore (PrescriptionByStoreEntity p) {
		return PrescriptionByDateEntity.builder()
			.id(p.getId())
			.patientFirstName(p.getPatientFirstName())
			.patientSecondName(p.getPatientSecondName())
			.patientId(p.getPatientId())
			.prescriberId(p.getPrescriberId())
			.prescriberPracticeId(p.getPrescriberPracticeId())
			.prescriberPracticeName(p.getPrescriberPracticeName())
			.prescriberFirstName(p.getPrescriberFirstName())
			.prescriberSecondName(p.getPatientSecondName())
			.prescriptionDate(p.getPrescriptionDate())
			.storeId(p.getStoreId())
			.storeName(p.getStoreName())
			.build();
	}
	
	public List<PrescriptionDTO> toDTOFromByStore(List<PrescriptionByStoreEntity> pl) {
		return pl.stream().map(x -> toDTOFromByStore(x)).collect(Collectors.toList());		
	}

	public PrescriptionDTO toDTOFromByStore(PrescriptionByStoreEntity p) {
		return toDTOFromByDate(fromByStore(p));
	}
	
	public PrescriptionDTO toDTOFromByDate(PrescriptionByDateEntity p) {
		PatientDTO patientDTO = patientMapper.toPatientDTO(p);
		StoreDTO storeDTO = storeMapper.toStoreDTO(p);
		PrescriptionDTO pDTO = PrescriptionDTO.builder()
									.id(p.getId())
									.prescriber(prescriberMapper.toPrescriberDTO(p, 2))
									.patient(patientDTO)
									.store(storeDTO)
									.issueDate(p.getPrescriptionDate())
									.build();
		return pDTO;
	}	
}
