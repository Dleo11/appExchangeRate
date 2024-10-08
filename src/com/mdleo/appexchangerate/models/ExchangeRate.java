package com.mdleo.appexchangerate.models;

import java.util.Map;

// Record que me permite obtener los datos de la API
public record ExchangeRate(
        String time_last_update_utc,
        String time_next_update_utc,
        String base_code,
        Map<String, Double> conversion_rates
        ){
}