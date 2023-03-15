package com.commons.challenge.crud;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

public class AdressCrud {

    public String ValidateCep(JSONObject cep){
        String cepString = cep.getAsString("cep");
        cepString = cepString.replaceAll("-", "");
        cepString = cepString.replaceAll(" ", "");
        if (cepString.matches("[0-9]+") && cepString.length() == 8)
            return cepString;
        else{
            return "invalid input";
        }
        
    }

    public JSONObject ConsumeAPI(String cep){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json";
        try {
            JSONObject apiData = restTemplate.getForObject(url, JSONObject.class);
            return apiData;
        } catch (Exception e) {
            return null;
        }
        
      }
    
    public double CalculateFee(String cep){
        double fee = 0;
        int cepInt = Integer.parseInt(cep);
        if(01000000 <= cepInt && cepInt <= 39999999)
            fee = 7.85;
        else if(40000000 <= cepInt && cepInt <= 65999999)
            fee = 15.98;
        else if(66000000 <= cepInt && cepInt <= 69999999)
            fee = 20.83;
        else if(70000000 <= cepInt && cepInt <= 76799999)
            fee = 12.50;
        else if(76800000 <= cepInt && cepInt <= 77999999)
            fee = 20.83;
        else if(78000000 <= cepInt && cepInt <= 79999999)
            fee = 12.50;
        else if(80000000 <= cepInt)
            fee = 17.30;
        return fee;
        
    }

    public JsonNode ConsultAdress(JSONObject cep){
        ObjectMapper objectMapper = new ObjectMapper();
        
        String cepString = ValidateCep(cep);
        if (cepString == "invalid input"){
            JsonNode jsonNode = objectMapper.createObjectNode()
                .put("erro", "input invalido");
            return jsonNode;
        }
        else{
            JSONObject apiData = ConsumeAPI(cepString);
            if(apiData == null){
                JsonNode jsonNode = objectMapper.createObjectNode()
                    .put("erro", "nao foi possivel acessar a api");
                return jsonNode;
            } 
            if(apiData.get("erro") != null){
                JsonNode jsonNode = objectMapper.createObjectNode()
                .put("erro", "cpf nao foi encontrado");
                return jsonNode;
            }            
            double fee = CalculateFee(cepString);
            JsonNode jsonNode = objectMapper.createObjectNode()
                .put("cep", apiData.get("cep").toString())
                .put("rua", apiData.get("logradouro").toString())
                .put("complemento", apiData.get("complemento").toString())
                .put("bairro", apiData.get("bairro").toString())
                .put("cidade", apiData.get("localidade").toString())
                .put("estado", apiData.get("uf").toString())
                .put("frete", fee);
            return jsonNode;
        }
        
    }
}
