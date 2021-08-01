package com.cognizantmfpe.pharmacyservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Instantiates a new medicine demand.
 *
 * @param medicineId the medicine id
 * @param medicine the medicine
 * @param medicineCount the medicine count
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDemand {

	/** The medicine id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer medicineId;
	
	/** The medicine. */
	@Column
	private String medicine;
	
	/** The medicine count. */
	@Column
	private Integer demandCount;
}
