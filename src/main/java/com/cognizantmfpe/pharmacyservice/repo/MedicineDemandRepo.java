package com.cognizantmfpe.pharmacyservice.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizantmfpe.pharmacyservice.model.MedicineDemand;

/**
 * The Interface MedicineDemandRepo.
 */
@Repository
public interface MedicineDemandRepo extends CrudRepository<MedicineDemand, Integer> {

}
