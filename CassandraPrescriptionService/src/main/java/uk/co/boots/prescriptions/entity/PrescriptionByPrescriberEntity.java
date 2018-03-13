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
@Table("prescription_by_prescriber")
public class PrescriptionByPrescriberEntity{

	@PrimaryKeyColumn(name="prescriber_id", type=PrimaryKeyType.PARTITIONED)
	private UUID prescriberId;

	@PrimaryKeyColumn(name="prescription_date", type=PrimaryKeyType.CLUSTERED)
	private LocalDateTime prescriptionDate;
	
	@Column("id")
	private UUID id;
	
	@Column("store_id")
	private UUID storeId;

	@Column("store_name")
	private String storeName;
	
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
