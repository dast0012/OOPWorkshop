package org.example;
public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        converter.updateExchangeRates(); // Opdaterer valutakurser

        // Test konvertering fra DKK til EUR
        double amountInDKK = 100;
        double convertedToEUR = converter.convert(amountInDKK, "DKK", "EUR");
        System.out.printf("%.2f DKK er lig med %.2f EUR%n", amountInDKK, convertedToEUR);

        // Test konvertering fra DKK til USD
        double convertedToUSD = converter.convert(amountInDKK, "DKK", "USD");
        System.out.printf("%.2f DKK er lig med %.2f USD%n", amountInDKK, convertedToUSD);

        // Test konvertering fra DKK til JPY
        double convertedToJPY = converter.convert(amountInDKK, "DKK", "JPY");
        System.out.printf("%.2f DKK er lig med %.2f JPY%n", amountInDKK, convertedToJPY);
    }
}