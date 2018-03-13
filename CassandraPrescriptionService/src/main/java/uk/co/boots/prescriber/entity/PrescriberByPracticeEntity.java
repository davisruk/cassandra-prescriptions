package uk.co.boots.prescriber.entity;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table ("prescriber_by_practice")
public class PrescriberByPracticeEntity {
	@PrimaryKeyColumn(name="practice_id", type=PrimaryKeyType.PARTITIONED)	
	private UUID practiceId;
	@PrimaryKeyColumn(name="prescriber_second_name", type=PrimaryKeyType.CLUSTERED)
	private String secondName;
	@Column("prescriber_id")
	private UUID id;
	@Column("prescriber_first_name")
	private String firstName;
	@Column("practice_name")
	private String practiceName;
	@Column("practice_address_line1")
	private String addressLine1;
	@Column("practice_address_line2")
	private String addressLine2;
	@Column("practice_address_line3")
	private String addressLine3;
	@Column("practice_town")
	private String town;
	@Column("practice_region")
	private String region;
	@Column("practice_country")
	private String country;
	@Column("practice_postCode")
	private String postCode;
	
}
