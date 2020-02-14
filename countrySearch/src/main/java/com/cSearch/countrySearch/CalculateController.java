package com.cSearch.countrySearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calc")

public class CalculateController {
	@RequestMapping(value = "/population/{id}/{raise}", produces={"application/json"})
	public ResponseEntity<?> estimateRaise(@PathVariable long id, @PathVariable double raise){
		Country tmpCty = new Country(CountrySearchApplication.ourCountryList.findCountry(e -> (e.getId() == id)));
		tmpCty.setPopulation(tmpCty.getPopulation() * (1.0 + raise));
		return new ResponseEntity<>(tmpCty, HttpStatus.OK);
	}

}
