package com.commons.challenge.routes;

import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest
@CucumberContextConfiguration
public class AddressStepDefinitions {

    
    
    private JsonNode addressData;

    @Given("The client has an endpoint to consult an address")
    public void the_client_has_an_endpoint_to_consult_an_address() {
    }

    @When("The client send a valid CEP")
    public void the_client_send_a_valid_cep() {
        RestTemplate restTemplate = new RestTemplate();
        JSONObject cep = new JSONObject();
        cep.put("cep", "01001000");
        addressData = restTemplate.postForObject("localhost:5000/v1/consulta-endereco", cep, null);
    }

    @Then("The client should receive a valid address data")
    public void the_client_should_receive_a_valid_address_data() {
        Assert.assertNotNull(addressData);
        JSONObject response = new JSONObject();
        response.put("cep", "01001-000");
        response.put("logradouro", "Praça da Sé");
        response.put("complemento", "lado ímpar");
        response.put("bairro", "Sé");
        response.put("localidade", "São Paulo");
        response.put("uf", "SP");
        response.put("ibge", "3550308");
        response.put("gia", "1004");
        response.put("ddd", "11");
        response.put("siafi", "7107");
        Assert.assertEquals(response, addressData);
    }
}