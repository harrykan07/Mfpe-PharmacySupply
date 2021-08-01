package com.cognizantmfpe.pharmacyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




/**
 * Instantiates a new medicine stock.
 *
 * @param mid the mid
 * @param medicineName the medicine name
 * @param chemicalComposition the chemical composition
 * @param targetAilment the target ailment
 * @param pharmacyName the pharmacy name
 * @param dateOfExpiry the date of expiry
 * @param numberOfTabletsInStock the number of tablets in stock
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineStock {

	/** The mid. */
	private Integer mid;
	
	/** The medicine name. */
	private String medicineName;
	
	/** The chemical composition. */
	private String chemicalComposition;
	
	/** The target ailment. */
	private String targetAilment;
	
	/** The pharmacy name. */
	private String pharmacyName;
	
	/** The date of expiry. */
	private String dateOfExpiry;
	
	/** The number of tablets in stock. */
	private Integer numberOfTabletsInStock;
	
}
