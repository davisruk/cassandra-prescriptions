package uk.co.boots.patient.entity;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("patient_by_region")
public class PatientByRegionEntity {
	@PrimaryKeyColumn(name="patient_region", type=PrimaryKeyType.PARTITIONED)
	private String region;
	@PrimaryKeyColumn(name="patient_id", type=PrimaryKeyType.CLUSTERED)
	private UUID id;
	@PrimaryKeyColumn(name="last_interaction", type=PrimaryKeyType.CLUSTERED)
	private LocalDate lastInteraction;
	@PrimaryKeyColumn(name="patient_second_name", type=PrimaryKeyType.CLUSTERED)
	private String secondName;
	@Column("patient_first_name")
	private String firstName;
	@Column("patient_address_line1")
	private String addressLine1;
	@Column("patient_address_line2")
	private String addressLine2;
	@Column("patient_address_line3")
	private String addressLine3;
	@Column("patient_town")
	private String town;
	@Column("patient_country")
	private String country;
	@Column("patient_postCode")
	private String postCode;
	@Column ("patient_code")
	private Map<String, String> codes;
}
