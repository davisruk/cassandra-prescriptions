package uk.co.boots.prescriptions.entity;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionMappingService {

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
}
