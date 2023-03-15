package com.commons.challenge.routes;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commons.challenge.crud.AdressCrud;
import com.fasterxml.jackson.databind.JsonNode;

import net.minidev.json.JSONObject;
@RestController
public class AdressRouter {
    
    @PostMapping(
        value = "v1/consulta-endereco",
        consumes = "application/Json",
        produces = "application/Json"
        )
    public JsonNode consultAdress(@RequestBody JSONObject cep){
        JsonNode data = AdressCrud.ConsultAdress(cep);
        return data;
    }


}
