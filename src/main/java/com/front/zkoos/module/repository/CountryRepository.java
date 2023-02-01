package com.front.zkoos.module.repository;


import com.front.zkoos.module.entities.Country;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Yann39
 * @since nov 2018
 */
public interface CountryRepository extends CrudRepository<Country, Long> {

}