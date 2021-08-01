package com.cognizantmfpe.pharmacyservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizantmfpe.pharmacyservice.model.MedicineStock;

/**
 * The Interface MedicineStockFeignClient.
 */
@FeignClient(name = "medicine-stock", url = "http://65.0.251.158:8080/medicine")
public interface MedicineStockFeignClient {

	/**
	 * Gets the stock by medicine.
	 *
	 * @param medicine: the medicine
	 * @return the stock by medicine
	 */
	@GetMapping("/getByMedicine/{medicine}")
	public MedicineStock getStockByMedicine(@RequestHeader(name = "Authorization") String token, @PathVariable String medicine);

	@GetMapping("/updateCount/{medicineName}/{count}")
	public Boolean updateNumberOfTabletsInStockByName(@RequestHeader(name = "Authorization") String token,@PathVariable String medicineName,@PathVariable int count);
}
