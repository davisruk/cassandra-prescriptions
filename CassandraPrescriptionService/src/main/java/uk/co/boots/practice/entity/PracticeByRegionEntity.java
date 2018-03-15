package uk.co.boots.practice.entity;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

/*
 * All practices for a region
 */
@Data
@Builder
@Table("practice_by_region")
public class PracticeByRegionEntity {
	@PrimaryKeyColumn(name="practice_region", type=PrimaryKeyType.PARTITIONED)	
	private String region;
	@PrimaryKeyColumn(name="practice_name", type=PrimaryKeyType.CLUSTERED)
	private String practiceName;
	@PrimaryKeyColumn(name="practice_id", type=PrimaryKeyType.CLUSTERED)
	private UUID id;
	@Column("practice_address_line1")
	private String addressLine1;
	@Column("practice_address_line2")
	private String addressLine2;
	@Column("practice_address_line3")
	private String addressLine3;
	@Column("practice_town")
	private String town;
	@Column("practice_country")
	private String country;
	@Column("practice_postCode")
	private String postCode;
	@Column ("practice_code")
	private Map<String, String> codes;
}
