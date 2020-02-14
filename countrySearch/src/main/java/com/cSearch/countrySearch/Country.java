package com.cSearch.countrySearch;

import java.util.concurrent.atomic.AtomicLong;

public class Country {
    private static final AtomicLong counter = new AtomicLong();
    private long id;
    private String name;
    private double population;
    private float landMass;
    private int medianAge;

    public Country(String name, int pop, float land, int mAge) {
        this.id = counter.incrementAndGet();
        this.name = name;
        this.population = pop;
        this.landMass = land;
        this.medianAge = mAge;
    }

    public Country(Country toClone){
        this.id = toClone.getId();
        this.name = toClone.getName();
        this.population = toClone.getPopulation();
        this.landMass = toClone.getLandMass();
        this.medianAge = toClone.getMedianAge();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPopulation() {
        return population;
    }

    public float getLandMass() {
        return landMass;
    }

    public int getMedianAge() {
        return medianAge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public void setLandMass(float landMass) {
        this.landMass = landMass;
    }

    public void setMedianAge(int medianAge) {
        this.medianAge = medianAge;
    }

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", population=" + population + ", landMass=" + landMass
				+ ", medianAge=" + medianAge + "]";
	}
    
    

}
