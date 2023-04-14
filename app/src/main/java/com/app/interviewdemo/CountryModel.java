package com.app.interviewdemo;

public class CountryModel {
    private String name;
    private String capital;
    private Double population;
    private Double area;
    private String region;
    private String subregion;

    public String getCapital() {
        return capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "com.app.interviewdemo.CountryModel{" +
                "capital='" + capital + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                '}';
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

}
