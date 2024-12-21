package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class CurrencyConverter {
    // Map til at gemme valutakurser, hvor nøglen er valutaens navn og værdien er kursen
    Map<String, Double> exchangeRates = new HashMap<>();

    // Metode til at opdatere valutakurser fra en ekstern API
    public void updateExchangeRates() {
        try {
            // URL til API'en, der henter de aktuelle valutakurser
            String apiUrl = "https://api.currencyapi.com/v3/latest?apikey=cur_live_0SYSHccRFodNZkrxqh9RCnP5pmY4Szbk4JqrnA2m&currencies=EUR,USD,JPY&base_currency=DKK";
            // Opretter en forbindelse til API'en
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET"); // Angiver, at vi vil lave en GET-anmodning

            // Læser svaret fra API'en
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            // Læser linje for linje, indtil der ikke er flere linjer
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine); // Tilføjer hver linje til svaret
            }
            in.close(); // Lukker BufferedReader

            // Parser JSON-svaret fra API'en
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject data = jsonResponse.getJSONObject("data"); // Henter dataobjektet fra svaret

            // Opdaterer valutakurserne i exchangeRates kortet
            for (String currency : data.keySet()) {
                exchangeRates.put(currency, data.getJSONObject(currency).getDouble("value")); // Gemmer kursen for hver valuta
            }
        } catch (Exception e) {
            e.printStackTrace(); // Udskriver fejl, hvis der opstår en undtagelse
        }
    }

    // Metode til at konvertere et beløb fra en valuta til en anden
    public double convert(double amount, String fromCurrency, String toCurrency) {
        // Tjekker om den ønskede valuta findes i exchangeRates
        if (!exchangeRates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Ugyldig valuta"); // Kaster en undtagelse, hvis valutaen er ugyldig
        }
        // Returnerer det konverterede beløb
        return amount * exchangeRates.get(toCurrency);
    }
} 