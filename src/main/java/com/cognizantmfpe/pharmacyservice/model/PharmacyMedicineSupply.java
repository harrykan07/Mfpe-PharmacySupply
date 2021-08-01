package com.cognizantmfpe.pharmacyservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Instantiates a new pharmacy medicine supply.
 *
 * @param pharmacyId the pharmacy id
 * @param pharmacyName the pharmacy name
 * @param medicine the medicine
 * @param supplyCount the supply count
 */

@Data
@Table(name="medicine_supply")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyMedicineSupply {
	
	/** The pharmacy id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pharmacyId;
	
	/** The pharmacy name. */
	@Column
	private String pharmacyName;
	
	/** The medicine. */
	@Column
	private String medicineName;
	
	/** The supply count. */
	@Column
	private Integer supplyCount;

}
