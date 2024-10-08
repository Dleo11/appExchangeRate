package com.mdleo.appexchangerate.principal;

import com.mdleo.appexchangerate.models.CreateJSONfile;
import com.mdleo.appexchangerate.models.ExchangeRate;
import com.mdleo.appexchangerate.models.Rate;
import com.mdleo.appexchangerate.models.SearchExchangeRate;

import java.util.*;

public class Principal {
    public static void main(String[] args) {

        System.out.println("\n -- Sistema de Conversión de Monedas --");
        System.out.println("\nMonedas más comunes: \n");
        String div = """
                USD, Dólar estadounidense       BRL, Real brasileño
                EUR, Euro                       INR, Rupia india
                JPY, Yen japonés                CHF, Franco suizo
                GBP, Libra esterlina            AUD, Dólar australiano
                CNY, Yuan chino                 CAD, Dólar canadiense
                ARS, Peso argentino             COP, Peso colombiano
                PEN, Nuevo sol peruano          BOB, Boliviano
                """;
        System.out.println(div);

        Scanner lectura = new Scanner(System.in);
        List<Rate> rates = new ArrayList<>();
        SearchExchangeRate consulta = new SearchExchangeRate();
        CreateJSONfile generator = new CreateJSONfile();

        Set<String> validCurrencies = Set.of("USD", "EUR", "JPY", "GBP", "CNY", "CAD", "ARS", "PEN", "BRL", "INR", "CHF", "AUD", "COP", "BOB");

        while (true) {
            System.out.println("Ingresa la moneda base (o escribe 'salir' para terminar): ");
            try {
                var baseCurrency = lectura.nextLine().trim().toUpperCase();
                if (baseCurrency.equalsIgnoreCase("salir")) {
                    break;
                }

                if (!validCurrencies.contains(baseCurrency)) {
                    System.out.println("Moneda base no válida. Intenta nuevamente.");
                    continue;
                }

                ExchangeRate exchangeRate = consulta.searchExchangeRate(baseCurrency);

                System.out.println("Ingresa la moneda destino: ");
                var targetCurrency = lectura.nextLine().trim().toUpperCase();

                if (!validCurrencies.contains(targetCurrency)) {
                    System.out.println("Moneda destino no válida. Intenta nuevamente.");
                    continue;
                }

                System.out.println("Ingresa la cantidad a convertir: ");
                double amount;
                try {
                    amount = lectura.nextDouble();
                    lectura.nextLine();  // Consumir el salto de línea
                } catch (InputMismatchException ime) {
                    System.out.println("Por favor, ingresa un número válido.");
                    lectura.nextLine();  // Consumir el valor incorrecto
                    continue;
                }

                Double conversionRate = exchangeRate.conversion_rates().get(targetCurrency);

                if (conversionRate != null) {
                    double convertedAmount = amount * conversionRate;
                    System.out.println("Monto convertido: " + Math.round(convertedAmount * 100.0) / 100.0 + " " + targetCurrency);

                    Rate rate = new Rate(
                            baseCurrency,
                            targetCurrency,
                            conversionRate,
                            amount,
                            convertedAmount,
                            exchangeRate.time_last_update_utc(),
                            exchangeRate.time_next_update_utc()
                    );
                    rates.add(rate);

                } else {
                    System.out.println("No se pudo obtener la tasa de conversión para " + targetCurrency);
                }

            } catch (NullPointerException e) {
                System.out.println("Error al obtener información. Verifica las monedas ingresadas o inténtalo más tarde.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }

        // Generar archivo JSON
        if (!rates.isEmpty()) {
            generator.createFile((ArrayList<Rate>) rates);
        }

        System.out.println("Gracias por usar el conversor de monedas.");
    }
}