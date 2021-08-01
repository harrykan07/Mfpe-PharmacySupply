package com.cognizantmfpe.pharmacyservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizantmfpe.pharmacyservice.exception.MedicineNotFoundException;
import com.cognizantmfpe.pharmacyservice.exception.TokenValidationFailedException;
import com.cognizantmfpe.pharmacyservice.feign.AuthenticationFeignClient;
import com.cognizantmfpe.pharmacyservice.feign.MedicineStockFeignClient;
import com.cognizantmfpe.pharmacyservice.model.JwtResponse;
import com.cognizantmfpe.pharmacyservice.model.MedicineDemand;
import com.cognizantmfpe.pharmacyservice.model.MedicineStock;
import com.cognizantmfpe.pharmacyservice.model.PharmacyMedicineSupply;
import com.cognizantmfpe.pharmacyservice.repo.MedicineDemandRepo;
import com.cognizantmfpe.pharmacyservice.repo.PharmacyMedicineSupplyRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class PharmacyMedicineSupplyServiceImpl.
 */
@Service
@Slf4j
public class PharmacyMedicineSupplyServiceImpl implements IPharmacyMedicineSupplyService {

	/** The prepo. */
	@Autowired
	private PharmacyMedicineSupplyRepo prepo;

	/** The mrepo. */
	@Autowired
	private MedicineDemandRepo mrepo;
	
	@Autowired
	private AuthenticationFeignClient authFeign;

	/** The medicine feign client. */
	@Autowired
	private MedicineStockFeignClient medicineFeignClient;

	/**
	 * Gets the pharmacy supply.
	 *
	 * @param medicineDemands the medicine demands
	 * @return the pharmacy supply
	 * @throws NullPointerException the null pointer exception
	 * @throws MedicineNotFoundException 
	 */
	@Override
	public List<PharmacyMedicineSupply> getPharmacySupply(String token, List<MedicineDemand> medicineDemands) throws MedicineNotFoundException {
		
		log.info("Start");
		log.info("Medicine Demand List {} ", medicineDemands);
		List<PharmacyMedicineSupply> pharmacyMedicineSupplies = new ArrayList<>();
		
		for (MedicineDemand medicineDemand : medicineDemands) {
			PharmacyMedicineSupply pharmacyMedicineSupply = new PharmacyMedicineSupply();
			MedicineStock medicineStock =null;
			medicineStock = medicineFeignClient.getStockByMedicine(token, medicineDemand.getMedicine());

			if (medicineStock == null) {
				throw new MedicineNotFoundException("Medicine not found");
			}
			log.info("{}", medicineStock);
			log.info("Demanded Medicine {} , InStockTablets Of That Medicine {}", medicineDemand.getMedicine(), medicineStock.getNumberOfTabletsInStock());
			
//			if (medicineStock.getNumberOfTabletsInStock() < medicineDemand.getDemandCount()) {
//				return null;
//			}
			
			setPharmacySupply(token, pharmacyMedicineSupply, medicineDemand, medicineStock);
			pharmacyMedicineSupplies.add(pharmacyMedicineSupply);
		}
		prepo.saveAll(pharmacyMedicineSupplies);
		mrepo.saveAll(medicineDemands);
		log.info("End");
		return pharmacyMedicineSupplies;
	}

	public void setPharmacySupply(String token, PharmacyMedicineSupply medicineSupply, MedicineDemand medicineDemand,
			MedicineStock medicineStock) throws MedicineNotFoundException {
		log.info("Start");
		int updatedCount = 0;
		log.info("Count of tablets in stock {}", medicineStock.getNumberOfTabletsInStock());
		if (medicineStock.getNumberOfTabletsInStock() < medicineDemand.getDemandCount()) {
			medicineSupply.setSupplyCount(medicineStock.getNumberOfTabletsInStock());

		} else {
			medicineSupply.setSupplyCount(medicineDemand.getDemandCount());
			updatedCount = medicineStock.getNumberOfTabletsInStock() - medicineDemand.getDemandCount();
		}
		log.info("UpdatedCount After Medicine Allotment To The Pharmacies= {}", updatedCount);
		if(!medicineFeignClient.updateNumberOfTabletsInStockByName(token, medicineDemand.getMedicine(), updatedCount))
			throw new MedicineNotFoundException("Medicine not found");
		medicineSupply.setMedicineName(medicineDemand.getMedicine());
		log.info("medicineDemand {} medicineSupply {}", medicineDemand, medicineSupply);
		medicineSupply.setPharmacyName(medicineStock.getPharmacyName());
		log.info("medicineSupply{}:", medicineSupply);
		log.info("End");
	}
	
	/**
	 * Validate the token received inside the Authorization part of the header
	 * Method Name --> validateToken 
	 * @return     --> List of medicine supply
	 * @throws TokenValidationFailedException 
	 */
	@Override
	public Boolean validateToken(String token) throws TokenValidationFailedException {
		log.info("Start");

		JwtResponse jwtResponse = authFeign.verifyToken(token);
		log.info("End");

		if (jwtResponse.isValid())
			return true;
		throw new TokenValidationFailedException("Token is no longer valid");

	}


}
