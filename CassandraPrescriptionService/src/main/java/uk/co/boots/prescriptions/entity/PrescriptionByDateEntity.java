package uk.co.boots.prescriptions.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("prescription_by_date")
public class PrescriptionByDateEntity {
	@PrimaryKeyColumn(name="prescription_date", type=PrimaryKeyType.PARTITIONED)
	private LocalDateTime prescriptionDate;

	@PrimaryKeyColumn(name="store_id", type=PrimaryKeyType.CLUSTERED)
	private UUID storeId;

	@Column("id")
	private UUID id;

	@Column("store_name")
	private String storeName;
	
	@PrimaryKeyColumn("prescriber_id")
	private UUID prescriberId;
	
	@Column("prescriber_first_name")
	private String prescriberFirstName;
	
	@Column("prescriber_second_name")
	private String prescriberSecondName;
	
	@Column("prescriber_practice_id")
	private UUID prescriberPracticeId;
	
	@Column("prescriber_practice_name")
	private String prescriberPracticeName;
	
	@Column("patient_id")  
	private UUID patientId;

	@Column("patient_first_name")
	private String patientFirstName;

	@Column("patient_second_name")
	private String patientSecondName;
}
