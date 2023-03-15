package com.commons.challenge.classes;

import com.commons.challenge.interfaces.AdressInterface;

public class Adress implements AdressInterface {

    private String cep;

    private String road;

    private String compliment;

    private String neighborhood;

    private String city;

    private String state;

    private float shippingFee;

    @Override
    public String getCep() {
        return cep;
    }

    @Override
    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String getRoad() {
        return road;
    }

    @Override
    public void setRoad(String road) {
        this.road = road;
    }

    @Override
    public String getCompliment() {
        return compliment;
    }

    @Override
    public void setCompliment(String compliment) {
        this.compliment = compliment;
    }

    @Override
    public String getNeighborhood() {
        return neighborhood;
    }

    @Override
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public float getShippingFee() {
        return shippingFee;
    }

    @Override
    public void setShippingFee(float shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Adress(String cep, String road, String compliment, String neighborhood, String city, String state,
            float shippingFee) {
        this.cep = cep;
        this.road = road;
        this.compliment = compliment;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.shippingFee = shippingFee;
    }
}