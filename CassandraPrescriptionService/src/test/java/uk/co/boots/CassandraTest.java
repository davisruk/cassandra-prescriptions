package uk.co.boots;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import uk.co.boots.patient.dto.PatientDTO;
import uk.co.boots.patient.service.PatientRepositoryService;
import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.practice.service.PracticeRepositoryService;
import uk.co.boots.prescriber.dto.PrescriberDTO;
import uk.co.boots.prescriber.service.PrescriberRepositoryService;
import uk.co.boots.prescription.dto.PrescriptionDTO;
import uk.co.boots.prescription.entity.PrescriptionByDateEntity;
import uk.co.boots.prescription.repository.PrescriptionByPatientRepository;
import uk.co.boots.prescription.repository.PrescriptionByPrescriberRepository;
import uk.co.boots.prescription.repository.PrescriptionByStoreRepository;
import uk.co.boots.prescription.repository.PrescriptionRepository;
import uk.co.boots.prescription.service.PrescriptionRepositoryService;
import uk.co.boots.store.dto.StoreDTO;
import uk.co.boots.store.repository.StoreByRegionRepository;
import uk.co.boots.store.service.StoreRepositoryService;

@SpringBootTest(classes= {CassandraTestConfig.class, RepoBeanConfig.class})
public class CassandraTest extends AbstractEmbeddedCassandraTest {
	@Autowired
	PracticeRepositoryService practiceRepoService;
	@Autowired
	PrescriberRepositoryService prescriberRepoService;
	@Autowired
	PatientRepositoryService patientService;
	@Autowired
	PrescriptionRepositoryService prescriptionRepoService;
	@Autowired
	StoreRepositoryService storeRepoService;
	@Autowired
	PracticeDTO practice1;
	@Autowired
	PracticeDTO practice2;
	@Autowired
	PrescriberDTO prescriber1;
	@Autowired
	PrescriberDTO prescriber2;
	@Autowired
	StoreDTO store1;
	@Autowired
	StoreDTO store2;
	@Autowired
	PatientDTO patient1;
	@Autowired
	PatientDTO patient2;
	@Autowired
	PrescriptionDTO prescriptionDTO1;
	@Autowired
	PrescriptionDTO prescriptionDTO2;
	@Autowired
	PrescriptionDTO prescriptionDTO3;
	@Autowired
	PrescriptionDTO prescriptionDTO4;
	
	@Test
	public void practiceTests() {
		insertPractices();
		PracticeDTO practiceRetVal = practiceRepoService.getPracticeForRegionAndCode(practice1.getAddress().getRegion(), "GMC", practice1.getCodes().get("GMC"));
		assertThat(practiceRetVal, is(notNullValue()));
		assertThat(practiceRetVal.getId(), equalTo(practice1.getId()));

		practiceRetVal = practiceRepoService.getPracticeForRegionAndCode(practice2.getAddress().getRegion(), "GMC", practice2.getCodes().get("GMC"));
		assertThat(practiceRetVal, is(notNullValue()));
		assertThat(practiceRetVal.getId(), equalTo(practice2.getId()));
	}
	
	@Test
	public void prescriberAndPracticeTests() {
		insertPractices();
		insertPrescribers();
			
		// check both prescribers are in first practice and only those prescribers
		List<PrescriberDTO> prescribers = practiceRepoService.getPractice(practice1.getId()).getPrescribers(); 
		assertThat(prescribers.size(), is(2));
		assertThat(prescribers, hasItems(hasProperty("id", is(prescriber1.getId())),
										hasProperty("id", is(prescriber2.getId()))));

		// check prescriber1 is in second practice and only prescriber 1
		prescribers = practiceRepoService.getPractice(practice2.getId()).getPrescribers();
		assertThat(prescribers.size(), is(1));
		assertThat(prescribers, hasItem(hasProperty("id", is(prescriber1.getId()))));

		// check that first prescriber has both practices and only those practices

		List<PracticeDTO> practices = prescriberRepoService.getPrescriber(prescriber1.getId()).getPractice(); 
		assertThat(practices.size(), is(2));
		assertThat(practices, hasItems(hasProperty("id", is(practice1.getId())),
				hasProperty("id", is(practice2.getId()))));
		
		// check that second prescriber has practice1 and only that practice		
		practices = prescriberRepoService.getPrescriber(prescriber2.getId()).getPractice(); 
		assertThat(practices.size(), is(1));
		assertThat(practices, hasItem(hasProperty("id", is(practice1.getId()))));		
	}
	
	@Test
	public void patientTests() {
		insertPatients();

		List<PatientDTO> patients = patientService.getAllPatientsForRegion("Leicestershire");
		assertThat(patients, hasItems(hasProperty("id", is(patient1.getId())),
				hasProperty("id", is(patient2.getId()))));

		PatientDTO patient = patientService.getPatientForRegionAndCode(patient1.getAddress().getRegion(), "NI", patient1.getCodes().get("NI"));
		assertThat(patient.getId(), equalTo(patient1.getId()));
	}	
	
	@Test
	public void prescriptionTests() {
		insertStores();
		insertPatients();
		insertPractices();
		insertPrescribers();
		insertPrescriptions();
		
		// check all prescriptions for store2
		List<PrescriptionDTO> pl = prescriptionRepoService.getByStoreId(store2.getId());
		assertThat(pl.size(), is(3));
		assertThat(pl, hasItems(
				hasProperty("id", is(prescriptionDTO2.getId())),
				hasProperty("id", is(prescriptionDTO3.getId())),
				hasProperty("id", is(prescriptionDTO4.getId()))
			)
		);


		pl = prescriptionRepoService.getByStoreId(store1.getId());
		assertThat(pl.size(), is(1));
		assertThat(pl, hasItems(
				hasProperty("id", is(prescriptionDTO1.getId()))
			)
		);
		
		// check prescriptions older than today;
		pl = prescriptionRepoService.getByStoreIdAndPrescriptionDateLessThan(store2.getId(), LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.now()));
		assertThat(pl.size(), is(1));
		assertThat(pl, hasItems(
				hasProperty("id", is(prescriptionDTO2.getId()))
			)
		);
	}
	
	@Test
	public void testDeletes() {
		insertStores();
		insertPatients();
		insertPractices();
		insertPrescribers();
		insertPrescriptions();

		prescriptionRepoService.delete(prescriptionDTO1);
		prescriptionRepoService.delete(prescriptionDTO2);
		prescriptionRepoService.delete(prescriptionDTO3);
		prescriptionRepoService.delete(prescriptionDTO4);
		patientService.delete(patient1);
		patientService.delete(patient2);
		practiceRepoService.delete(practice1);
		practiceRepoService.delete(practice2);
		storeRepoService.delete(store2);
		storeRepoService.delete(store1);
		
		assertThat (prescriptionRepoService.getByStoreId(store1.getId()).size(),is(0));
		assertThat (prescriptionRepoService.getByStoreId(store2.getId()).size(),is(0));
		assertThat (prescriptionRepoService.getByPatientId(patient1.getId()).size(),is(0));
		assertThat (prescriptionRepoService.getByPatientId(patient2.getId()).size(),is(0));
		assertThat (prescriptionRepoService.getByPrescriberId(prescriber1.getId()).size(),is(0));
		assertThat (prescriptionRepoService.getByPrescriberId(prescriber2.getId()).size(),is(0));
		assertThat (prescriptionRepoService.getByPrescriptionDate(prescriptionDTO1.getIssueDate()).size(),is(0));
		assertThat (prescriptionRepoService.getByPrescriptionDate(prescriptionDTO2.getIssueDate()).size(),is(0));
		assertThat (prescriptionRepoService.getByPrescriptionDate(prescriptionDTO3.getIssueDate()).size(),is(0));
		assertThat (prescriptionRepoService.getByPrescriptionDate(prescriptionDTO4.getIssueDate()).size(),is(0));
		assertThat (storeRepoService.getByRegion(store1.getAddress().getRegion()).size(), is(0));
		assertThat (practiceRepoService.getAllPracticesForRegion(practice1.getAddress().getRegion()).size(), is(0));
		assertThat (patientService.getAllPatientsForRegion(patient1.getAddress().getRegion()).size(), is(0));
	}
	
	private void insertPractices() {
		practiceRepoService.save(practice1);
		practiceRepoService.save(practice2);
	}
	
	private void insertPrescribers() {
		prescriberRepoService.addPractice(prescriber1, practice1);
		prescriberRepoService.addPractice(prescriber1, practice2);
		prescriberRepoService.addPractice(prescriber2, practice1);
	}
	
	private void insertStores() {
		storeRepoService.save(store1);
		storeRepoService.save(store2);
	}
	
	private void insertPatients() {
		patientService.save(patient1);
		patientService.save(patient2);
	}

	private void insertPrescriptions() {
		prescriptionRepoService.save(prescriptionDTO1);
		prescriptionRepoService.save(prescriptionDTO2);
		prescriptionRepoService.save(prescriptionDTO3);
		prescriptionRepoService.save(prescriptionDTO4);
	}
	
}
