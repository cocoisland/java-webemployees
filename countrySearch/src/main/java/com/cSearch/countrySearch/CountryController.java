package com.cSearch.countrySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CountryController {

	// return the names of all the countries alphabetically
	@GetMapping(value = "/names/all", produces = { "application/json" })
	public ResponseEntity<?> getAllCountries() {
		CountrySearchApplication.ourCountryList.countryList
				.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
		return new ResponseEntity<>(CountrySearchApplication.ourCountryList.countryList, HttpStatus.OK);
	}

	// localhost:8080/data/names/id
	@GetMapping(value = "/names/{id}", produces = { "application/json" })
	public ResponseEntity<?> getcountryId(@PathVariable long id) {
		Country rtnCty = CountrySearchApplication.ourCountryList.findCountry(e -> (e.getId() == id));
		return new ResponseEntity<>(rtnCty, HttpStatus.OK);
	}

	// return the countries alphabetically that begin with the given letter
	@GetMapping(value = "/names/start/{letter}", produces = { "application/json" })
	public ResponseEntity<?> getCountryByLetter(@PathVariable char letter) {
		ArrayList<Country> rtnCtyList = CountrySearchApplication.ourCountryList
				.findCountries(e -> (e.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter)));
		rtnCtyList.sort((e1, e2) -> (e1.getName().compareTo(e2.getName())));
		return new ResponseEntity<>(rtnCtyList, HttpStatus.OK);
	}

	// return the countries alphabetically that have a name equal to or longer than
	// the given length
	@GetMapping(value = "/names/size/{number}", produces = { "application/json" })
	public ResponseEntity<?> getNameByLength(@PathVariable int number) {
		ArrayList<Country> rtnCtyList = CountrySearchApplication.ourCountryList
				.findCountries(e -> (e.getName().length() >= number));
		rtnCtyList.sort((e1, e2) -> (e1.getName().compareTo(e2.getName())));
		return new ResponseEntity<>(rtnCtyList, HttpStatus.OK);
	}

	// return the countries that have a population equal to or greater than the
	// given population
	@GetMapping(value = "/population/size/{people}", produces = { "application/json" })
	public ResponseEntity<?> getNameByPopulation(@PathVariable int people) {
		ArrayList<Country> rtnCtyList = CountrySearchApplication.ourCountryList
				.findCountries(e -> (((int) e.getPopulation()) >= people));
		rtnCtyList.sort((e1, e2) -> ((int) (e1.getPopulation() - e2.getPopulation())));
		return new ResponseEntity<>(rtnCtyList, HttpStatus.OK);
	}

	// return the country with the smallest population
	@GetMapping(value = "/population/min", produces = { "application/json" })
	public ResponseEntity<?> getPopMin() {
		CountrySearchApplication.ourCountryList.countryList
				.sort((e1, e2) -> ((int) (e1.getPopulation() - e2.getPopulation())));
		Country c = CountrySearchApplication.ourCountryList.getFirst();
		return new ResponseEntity<>(c, HttpStatus.OK);
	}

	// return the country with the largest population
	@GetMapping(value = "/population/max", produces = { "application/json" })
	public ResponseEntity<?> getPopMax() {
		ArrayList<Country> myList = CountrySearchApplication.ourCountryList.countryList;
		myList.sort(Comparator.comparing(Country::getPopulation)); // Java 8 Comparator
		int last = myList.size() - 1;
		Country c = myList.get(last);
		// CountrySearchApplication.ourCountryList.countryList.
		// sort((e1,e2) -> ((int)(e1.getPopulation() - e2.getPopulation())));
		// Country c = CountrySearchApplication.ourCountryList.getLast();
		return new ResponseEntity<>(c, HttpStatus.OK);
	}

	// return the country with the median population
	@GetMapping(value = "/population/median", produces = { "application/json" })
	public ResponseEntity<?> getPopMed() {
		// Country c = CountrySearchApplication.ourCountryList.getPopMedian();

		ArrayList<Country> myList = CountrySearchApplication.ourCountryList.countryList;
		myList.sort(Comparator.comparing(Country::getPopulation));
		int mid = myList.size() / 2;
		Country c;
		if ((mid % 2) == 0) {
			c = myList.get(mid + 1);
		} else {
			c = myList.get(mid);
		}
		return new ResponseEntity<>(c, HttpStatus.OK);
	}

	// return the countries that have a median age equal to or greater than the
	// given age
	@GetMapping(value = "/age/{age}", produces = { "application/json" })
	public ResponseEntity<?> getAge(@PathVariable int age) {
		ArrayList<Country> rtnList = CountrySearchApplication.ourCountryList
				.findCountries(e -> (e.getMedianAge() >= age));
		return new ResponseEntity<>(rtnList, HttpStatus.OK);
	}

	// return the country with the smallest median age
	@GetMapping(value = "/age/min", produces = { "application/json" })
	public ResponseEntity<?> getAgeMin() {
		ArrayList<Country> myList = CountrySearchApplication.ourCountryList.countryList;
		myList.sort(Comparator.comparing(Country::getMedianAge));
		return new ResponseEntity<>(myList.get(0), HttpStatus.OK);
	}

	// return the country the the greatest median age
	@GetMapping(value = "/age/max", produces = { "application/json" })
	public ResponseEntity<?> getAgeMax() {
		ArrayList<Country> myList = CountrySearchApplication.ourCountryList.countryList;
		myList.sort(Comparator.comparing(Country::getMedianAge)); // Java 8 Comparator
		int last = myList.size() - 1;
		Country c = myList.get(last);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}

	// return the country with the median median age
	@GetMapping(value = "/age/median", produces = { "application/json" })
	public ResponseEntity<?> getCountryAgeMedian() {
		ArrayList<Country> myList = CountrySearchApplication.ourCountryList.countryList;
		myList.sort(Comparator.comparing(Country::getMedianAge));
		int mid = myList.size() / 2;
		Country c;
		if ((mid % 2) == 0) {
			c = myList.get(mid + 1);
		} else {
			c = myList.get(mid);
		}
		return new ResponseEntity<>(c, HttpStatus.OK);
	}

}
