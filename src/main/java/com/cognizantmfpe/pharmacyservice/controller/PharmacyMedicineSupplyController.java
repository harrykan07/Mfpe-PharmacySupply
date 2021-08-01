package com.cognizantmfpe.pharmacyservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizantmfpe.pharmacyservice.exception.MedicineNotFoundException;
import com.cognizantmfpe.pharmacyservice.exception.TokenValidationFailedException;
import com.cognizantmfpe.pharmacyservice.model.MedicineDemand;
import com.cognizantmfpe.pharmacyservice.model.PharmacyMedicineSupply;
import com.cognizantmfpe.pharmacyservice.service.IPharmacyMedicineSupplyService;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class PharmacyMedicineSupplyController.
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping(value="supply")
public class PharmacyMedicineSupplyController {

	/** The pharmacy service. */
	@Autowired
	private IPharmacyMedicineSupplyService pharmacyService;

	/*
	 * input:
	 * 	[
	 * 		{
	 * 			"medicine":"Gaviscon",
	 * 			"medicineCount":"150"
	 * 		},
	 * 		{
	 * 			"medicine":"Dolo-650",
	 * 			"medicineCount":"305"
	 * 		},
	 * 		{
	 * 			"medicine":"Cyclopam",
	 * 			"medicineCount":"15"
	 * 		},	  
	 * 		{
	 * 			"medicine":"Hilact",
	 * 			"medicineCount":"50"
	 * 		},	  		
	 *
	 * 	]
	 * http://localhost:8082/PharmacySupply
	 */
	/**
	 * Gets the pharmacy supply.
	 * @param medicineDemands the medicine demands
	 * @return the pharmacy supply
	 * @throws MedicineNotFoundException 
	 * @throws TokenValidationFailedException 
	 * @throws NullPointerException 
	 */
	@RequestMapping(value = "/PharmacySupply", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PharmacyMedicineSupply>> getPharmacySupply(@RequestHeader(name = "Authorization") String token,@RequestBody List<MedicineDemand> medicineDemands) throws MedicineNotFoundException, TokenValidationFailedException 
	{
		log.info("Start");

		log.debug("MedicineDemandList {}:", medicineDemands);
		List<PharmacyMedicineSupply> pharmacySupply = null;
		if(pharmacyService.validateToken(token))
		{
			pharmacySupply=pharmacyService.getPharmacySupply(token, medicineDemands);
		
			if (pharmacySupply == null)  {
				return ResponseEntity.notFound().build();
			}
			
			log.info("End");
			return ResponseEntity.ok(pharmacySupply);

		}
		log.info("End");
		throw new TokenValidationFailedException("Token is no longer valid");
	}
}
