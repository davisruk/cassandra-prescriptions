package uk.co.boots.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String town;
	private String country;
	private String postCode;
	private String region;
}
