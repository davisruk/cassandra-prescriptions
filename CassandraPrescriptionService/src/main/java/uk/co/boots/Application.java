package uk.co.boots;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.co.boots.prescriptions.entity.PrescriptionByDateEntity;
import uk.co.boots.prescriptions.entity.PrescriptionMappingService;
import uk.co.boots.prescriptions.repository.PrescriptionByPatientRepository;
import uk.co.boots.prescriptions.repository.PrescriptionByPrescriberRepository;
import uk.co.boots.prescriptions.repository.PrescriptionByStoreRepository;
import uk.co.boots.prescriptions.repository.PrescriptionRepository;
import uk.co.boots.store.entity.StoreByRegionEntity;
import uk.co.boots.store.repository.StoreByRegionRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	PrescriptionRepository pr;
	@Autowired
	PrescriptionByStoreRepository byStoreRepo;
	@Autowired
	PrescriptionByPatientRepository byPatientRepo;
	@Autowired
	PrescriptionByPrescriberRepository byPrescriberRepo;
	@Autowired
	PrescriptionMappingService mapper;
	@Autowired
	StoreByRegionRepository storeRepo;
	
	public static void main (final String args[]) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		UUID patientId_a = UUID.randomUUID();
		UUID prescriberId_a = UUID.randomUUID();
		UUID prescriberPracticeId = UUID.randomUUID();
		UUID patientId_b = UUID.randomUUID();
		UUID prescriberId_b = UUID.randomUUID();
		UUID prescriberPracticeId_b = UUID.randomUUID();
		UUID prescriberId_c = UUID.randomUUID();
		
		StoreByRegionEntity store1 = StoreByRegionEntity.builder()
				.storeId(UUID.randomUUID())
				.region("Leicestershire")
				.storeName("High Cross")
				.addressLine1("38-39 Shires Lane")
				.town("Leicester")
				.postCode("LE1 4FQ")
				.country("England")
				.build();
		storeRepo.save(store1);
		
		StoreByRegionEntity store2  = StoreByRegionEntity.builder()
				.storeId(UUID.randomUUID())
				.region("Nottinghamshire")
				.storeName("Riverside")
				.addressLine1("Riverside Retail Park")
				.town("Nottingham")
				.postCode("NG2 1RU")
				.country("England")
				.build();

		storeRepo.save(store2);
		
		PrescriptionByDateEntity e1 = PrescriptionByDateEntity.builder()
				.id(UUID.randomUUID())
				.patientId(patientId_a)
				.patientFirstName("Richard")
				.patientSecondName("Davis")
				.storeId(store1.getStoreId())
				.storeName(store1.getStoreName())
				.prescriberId(prescriberId_a)
				.prescriberFirstName("Joe")
				.prescriberSecondName("Bloggs")
				.prescriberPracticeId(prescriberPracticeId)
				.prescriberPracticeName("Charnwood Surgery")
				.prescriptionDate(LocalDateTime.now())
				.build();
		
		PrescriptionByDateEntity e2 = PrescriptionByDateEntity.builder()
				.id(UUID.randomUUID())
				.patientId(patientId_a)
				.patientFirstName("Richard")
				.patientSecondName("Davis")
				.storeId(store2.getStoreId())
				.storeName(store2.getStoreName())
				.prescriberId(prescriberId_a)
				.prescriberFirstName("Joe")
				.prescriberSecondName("Bloggs")
				.prescriberPracticeId(prescriberPracticeId)
				.prescriberPracticeName("Charnwood Surgery")
				.prescriptionDate(LocalDateTime.of(2013, 8, 30, 15, 30))
				.build();

		PrescriptionByDateEntity e3 = PrescriptionByDateEntity.builder()
				.id(UUID.randomUUID())
				.patientId(patientId_b)
				.patientFirstName("Merle")
				.patientSecondName("Davis")
				.storeId(store2.getStoreId())
				.storeName(store2.getStoreName())
				.prescriberId(prescriberId_b)
				.prescriberFirstName("A.N")
				.prescriberSecondName("Other")
				.prescriberPracticeId(prescriberPracticeId)
				.prescriberPracticeName("Charnwood Surgery")
				.prescriptionDate(LocalDateTime.now())
				.build();
		
		PrescriptionByDateEntity e4 = PrescriptionByDateEntity.builder()
				.id(UUID.randomUUID())
				.patientId(patientId_b)
				.patientFirstName("Merle")
				.patientSecondName("Davis")
				.storeId(store2.getStoreId())
				.storeName(store2.getStoreName())
				.prescriberId(prescriberId_c)
				.prescriberFirstName("Doctor")
				.prescriberSecondName("Foster")
				.prescriberPracticeId(prescriberPracticeId_b)
				.prescriberPracticeName("Loughborough Walkin")
				.prescriptionDate(LocalDateTime.now())
				.build();
		
		pr.insert(e1);
		pr.insert(e2);
		pr.insert(e3);
		pr.insert(e4);

		System.out.println("All Prescriptions For" + store2.getStoreName());
		byStoreRepo.findByStoreId(store2.getStoreId()).forEach(System.out::println);

		System.out.println("All Prescriptions For" + store1.getStoreName());
		byStoreRepo.findByStoreId(store1.getStoreId()).forEach(System.out::println);

		System.out.println("All Prescriptions For " +  store2.getStoreName() + " Older than today");
		byStoreRepo.findByStoreIdAndPrescriptionDateLessThan(store2.getStoreId(), LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.now()))
							.forEach(System.out::println);
		pr.delete(e1);
		pr.delete(e2);
		pr.delete(e3);
		pr.delete(e4);
		storeRepo.delete(store2);
		storeRepo.delete(store1);
	}
}