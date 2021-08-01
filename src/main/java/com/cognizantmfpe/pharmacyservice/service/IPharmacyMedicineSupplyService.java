package com.cognizantmfpe.pharmacyservice.service;

import java.util.List;

import com.cognizantmfpe.pharmacyservice.exception.MedicineNotFoundException;
import com.cognizantmfpe.pharmacyservice.exception.TokenValidationFailedException;
import com.cognizantmfpe.pharmacyservice.model.MedicineDemand;
import com.cognizantmfpe.pharmacyservice.model.PharmacyMedicineSupply;

/**
 * The Interface IPharmacyMedicineSupplyService.
 */
public interface IPharmacyMedicineSupplyService {

	/**
	 * Gets the pharmacy supply.
	 *
	 * @param medicineDemands the medicine demands
	 * @return the pharmacy supply
	 * @throws NullPointerException the null pointer exception
	 * @throws MedicineNotFoundException 
	 */
	public List<PharmacyMedicineSupply> getPharmacySupply(String token, List<MedicineDemand> medicineDemands) throws MedicineNotFoundException;

	/**
	 * Validate the token received inside the Authorization part of the header
	 * Method Name --> validateToken 
	 * @return     --> List of medicine supply
	 * @throws TokenValidationFailedException 
	 */
	public Boolean validateToken(String token) throws TokenValidationFailedException;

}
