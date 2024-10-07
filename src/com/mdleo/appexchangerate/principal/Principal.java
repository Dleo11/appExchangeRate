package com.mdleo.appexchangerate.principal;

import com.mdleo.appexchangerate.models.CreateJSONfile;
import com.mdleo.appexchangerate.models.ExchangeRate;
import com.mdleo.appexchangerate.models.Rate;
import com.mdleo.appexchangerate.models.SearchExchangeRate;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        System.out.println(" -- Sistema de Conversión de Monedas --");

        Scanner lectura = new Scanner(System.in);
        List<Rate> rates = new ArrayList<>();
        SearchExchangeRate consulta = new SearchExchangeRate();
        CreateJSONfile generator = new CreateJSONfile();

        while (true) {
            System.out.println("Ingresa la moneda base (o escribe 'salir' para terminar): ");

            try {
                var baseCurrency = String.valueOf(lectura.nextLine().trim().toUpperCase());
                if (baseCurrency.equalsIgnoreCase("salir")) {
                    break;
                }

                if (baseCurrency.length() != 3) {
                    System.out.println("El código de moneda debe tener 3 letras. Intenta nuevamente.");
                    continue;
                }
                ExchangeRate exchangeRate = consulta.searchExchangeRate(baseCurrency);

                System.out.println("Ingresa la moneda destino: ");
                var targetCurrency = lectura.nextLine().trim().toUpperCase();
                if (targetCurrency.length() != 3) {
                    System.out.println("El código de la moneda destino debe tener 3 letras. Intenta nuevamente.");
                    continue;
                }
                System.out.println("Ingresa la cantidad a convertir: ");
                double  amount;

                try {
                    amount = lectura.nextDouble();
                    lectura.nextLine();  // Consumir el salto de línea
                } catch (InputMismatchException ime) {
                    System.out.println("Por favor, ingresa un número válido.");
                    lectura.nextLine();  // Consumir el valor incorrecto
                    continue;
                }

                // Obtener el tipo de cambio para la moneda destino
                Double conversionRate = exchangeRate.conversion_rates().get(targetCurrency.toUpperCase());

                double convertedAmount = 0;
                if (conversionRate != null) {
                    convertedAmount = amount * conversionRate;
                    System.out.println("Monto convertido: " + convertedAmount + " " + targetCurrency.toUpperCase());

                    Rate rate = new Rate(
                            baseCurrency.toUpperCase(),
                            targetCurrency.toUpperCase(),
                            amount,
                            convertedAmount,
                            exchangeRate.time_last_update_utc().substring(0, 26),
                            exchangeRate.time_next_update_utc().substring(0, 26)
                    );
                    rates.add(rate);

                } else {
                    System.out.println("Moneda destino no válida o no disponible.");
                }

            } catch (NullPointerException e) {
                System.out.println("No se pudo obtener información de la API. Verifica la moneda base.");
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }

        if (!rates.isEmpty()) {
            generator.createFile((ArrayList<Rate>) rates);
            System.out.println("Conversiones almacenadas en archivo JSON.");
        } else {
            System.out.println("No se realizaron conversiones.");
        }
    }
}
