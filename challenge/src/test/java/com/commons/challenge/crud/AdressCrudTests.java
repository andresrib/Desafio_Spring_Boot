package com.commons.challenge.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.minidev.json.JSONObject;

public class AdressCrudTests {
    AdressCrud adressCrud = new AdressCrud();
    @Test
    void CepWithLetter(){
        JSONObject cep = new JSONObject();
        cep.put("cep", "1234a-678");
        assertEquals("invalid input", adressCrud.ValidateCep(cep));
    }

    @Test
    void CepWithSpecialCharacter(){
        JSONObject cep = new JSONObject();
        cep.put("cep", "1234#-678");
        assertEquals("invalid input", adressCrud.ValidateCep(cep));
    }

    @Test
    void CepTooShort(){
        JSONObject cep = new JSONObject();
        cep.put("cep", "1234-678");
        assertEquals("invalid input", adressCrud.ValidateCep(cep));
    }

    @Test
    void CepTooLong(){
        JSONObject cep = new JSONObject();
        cep.put("cep", "123444678");
        assertEquals("invalid input", adressCrud.ValidateCep(cep));
    }

    @Test
    void CepWithSpace(){
        JSONObject cep = new JSONObject();
        cep.put("cep", "12345 678");
        assertEquals("12345678", adressCrud.ValidateCep(cep));
    }

    @Test
    void CepOkWithMascara(){
        JSONObject cep = new JSONObject();
        cep.put("cep", "12345-678");
        assertEquals("12345678", adressCrud.ValidateCep(cep));
    }

    @Test
    void CepOkWithoutMascara(){
        JSONObject cep = new JSONObject();
        cep.put("cep", "12345678");
        assertEquals("12345678", adressCrud.ValidateCep(cep));
    }

    @Test
    void ConsumeWithInvalidCep(){
        assertEquals(null, adressCrud.ConsumeAPI("123453678"));
    }

    @Test
    void ConsumeWithInexistantCep(){
        JSONObject response = new JSONObject();
        response.put("erro", true);
        assertEquals(response, adressCrud.ConsumeAPI("99999999"));
    }

    @Test
    void ConsumeWithValidCep(){
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
        
        assertEquals(response, adressCrud.ConsumeAPI("01001000"));
    }

    @Test
    void ConsumeWithValidCepWithMascara(){
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
        
        assertEquals(response, adressCrud.ConsumeAPI("01001-000"));
    }

    @Test
    void GetFeeSudeste(){
        assertEquals(7.85, adressCrud.CalculateFee("12343678"));
    }

    @Test
    void GetFeeSul(){
        assertEquals(17.30, adressCrud.CalculateFee("92343678"));
    }

    @Test
    void GetFeeNorte(){
        assertEquals(20.83, adressCrud.CalculateFee("67343678"));
    }

    @Test
    void GetFeeCentro(){
        assertEquals(12.50, adressCrud.CalculateFee("71343678"));
    }

    @Test
    void GetFeeNordeste(){
        assertEquals(15.98, adressCrud.CalculateFee("46343678"));
    }
}
