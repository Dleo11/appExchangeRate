package com.mdleo.appexchangerate.models;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SearchExchangeRate {

    public ExchangeRate searchExchangeRate(String baseCode) {
        String apiKey = "5741f1aada1b0086006d3f36";
        URI direction = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCode);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direction)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                // Si el código de estado no es 200, significa que algo falló en la llamada a la API
                throw new RuntimeException("Error al obtener las tasas de cambio. Código de estado: " + response.statusCode());
            }
            return new Gson().fromJson(response.body(), ExchangeRate.class);
        } catch (IOException e) {
            throw new RuntimeException("Error al comunicarse con el servicio de tasas de cambio: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException("La solicitud fue interrumpida: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}