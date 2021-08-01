package com.cognizantmfpe.pharmacyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	private String userId;


	private String userName;


	private boolean isValid;
}
