package com.cognizantmfpe.pharmacyservice.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizantmfpe.pharmacyservice.model.PharmacyMedicineSupply;


/**
 * The Interface PharmacyMedicineSupplyRepo.
 */
@Repository
public interface PharmacyMedicineSupplyRepo extends CrudRepository<PharmacyMedicineSupply, Integer> {
	
}
