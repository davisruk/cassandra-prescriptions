package uk.co.boots.store.entity;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("store_by_region")
public class StoreByRegionEntity {

	@PrimaryKeyColumn(name="store_region", type=PrimaryKeyType.PARTITIONED)	
	private String region;
	@PrimaryKeyColumn(name="store_name", type=PrimaryKeyType.CLUSTERED)
	private String storeName;
	@Column("store_id")
	private UUID storeId;
	@Column("store_address_line1")
	private String addressLine1;  
	@Column("store_address_line2")
	private String addressLine2;  
	@Column("store_address_line3")
	private String addressLine3;  
	@Column("store_town")
	private String town;  
	@Column("store_country")
	private String country;
	@Column("store_postCode")
	private String postCode;
	
}
