package com.mdleo.appexchangerate.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Rate {
    private String baseCurrency;
    private String targetCurrency;
    private double conversionRate;
    private double amount;
    private double convertedAmount;
    private String time_last_update_utc;
    private String time_next_update_utc;
    private String dateConsulta;

    // Constructor
    public Rate(String baseCurrency, String targetCurrency, double conversionRate,
                double amount, double convertedAmount,
                String time_last_update_utc, String time_next_update_utc) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.conversionRate = conversionRate;
        this.amount = amount;
        // Redondear el monto convertido a 2 decimales
        this.convertedAmount = Math.round(convertedAmount * 100.0) / 100.0;
        this.time_last_update_utc = time_last_update_utc;
        this.time_next_update_utc = time_next_update_utc;
        this.dateConsulta = getCurrentDate();
    }

    // Método para obtener la fecha actual
    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd h:mm");
        return formatter.format(new Date());
    }

    @Override
    public String toString() {
        return "Base: " + baseCurrency + ", Destino: " + targetCurrency +
                ", Cantidad: " + amount + ", Monto convertido: " + convertedAmount +
                ", Última actualización: " + time_last_update_utc +
                ", Siguiente actualización: " + time_next_update_utc +
                ", Consulta realizada: " + dateConsulta;
    }
}
