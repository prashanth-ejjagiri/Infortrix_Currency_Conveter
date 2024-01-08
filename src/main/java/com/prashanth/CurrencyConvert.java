package com.prashanth;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConvert {
    public static final String ACCESS_KEY = "25508ceaf87e687ffb4cba7788f4b380";
    public static final String BASE_URL = "http://api.exchangerate.host/";
    public static final String ENDPOINT = "live";

    private static final OkHttpClient client = new OkHttpClient();
    private static final Map<String, Double> exchangeRates = new HashMap<>();
    private static final Map<String, Double> favoriteCurrencies = new HashMap<>();

    public static void main(String[] args) {
        loadCurrencyExchangeRates();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n-----Currency Converter Menu -----");
            System.out.println("1. Convert the Currency");
            System.out.println("2. Add currency to Favorite List");
            System.out.println("3. View Favorite Currency List");
            System.out.println("4. Update Favorite Currency Rate");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> convertCurrency(scanner);
                case 2 -> addToFavoriteList(scanner);
                case 3 -> viewFavoriteList();
                case 4 -> updateFavoriteRate(scanner);
                case 5 -> {
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loadCurrencyExchangeRates() {
        try {
            Request request = new Request.Builder()
                    .url(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY)
                    .build();

            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String jsonData = response.body().string();

            System.out.println("JSON Response: " + jsonData); // Add this line

            JSONObject jsonObject = new JSONObject(jsonData);

            if (jsonObject.has("quotes")) {
                JSONObject quotes = jsonObject.getJSONObject("quotes");

                System.out.println("\nLoaded Exchange Rates:");

                // Extract base currency
                String baseCurrency = jsonObject.getString("source");

                // Add base currency to the rates
                exchangeRates.put(baseCurrency, 1.0);

                // Iterate through all currencies and their rates
                for (String quote : quotes.keySet()) {
                    String currency = quote.substring(3); // Extract currency code from quote key
                    double rate = quotes.getDouble(quote);

                    exchangeRates.put(currency, rate);

                    System.out.println(currency + ": " + rate);
                }

                System.out.println("\nExchange rates loaded successfully.");
            } else {
                System.out.println("Error loading exchange rates. Quotes not found in the response.");
                System.exit(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading exchange rates. Exiting.");
            System.exit(1);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }






    private static void convertCurrency(Scanner scanner) {
        System.out.print("Enter the amount: ");
        double amount = scanner.nextDouble();

        System.out.print("Enter the source currency code: ");
        String sourceCurrency = scanner.next().toUpperCase();

        System.out.print("Enter the target currency code: ");
        String targetCurrency = scanner.next().toUpperCase();

        if (!exchangeRates.containsKey(sourceCurrency) || !exchangeRates.containsKey(targetCurrency)) {
            System.out.println("Invalid currency code. Please check and try again.");
            return;
        }

        double sourceRate = exchangeRates.get(sourceCurrency);
        double targetRate = exchangeRates.get(targetCurrency);

        double convertedAmount = (amount / sourceRate) * targetRate;

        System.out.println(amount + " " + sourceCurrency + " = " + convertedAmount + " " + targetCurrency);
    }

    private static void addToFavoriteList(Scanner scanner) {
        System.out.print("Enter the currency code to add to favorites: ");
        String currencyCode = scanner.next().toUpperCase();

        if (exchangeRates.containsKey(currencyCode)) {
            double rate = exchangeRates.get(currencyCode);
            favoriteCurrencies.put(currencyCode, rate);
            System.out.println(currencyCode + " added to favorites with rate: " + rate);
        } else {
            System.out.println("Invalid currency code. Please check and try again.");
        }
    }

    private static void viewFavoriteList() {
        if (favoriteCurrencies.isEmpty()) {
            System.out.println("Favorite Currency List is empty.");
        } else {
            System.out.println("\n------ Favorite Currency List ------");
            for (Map.Entry<String, Double> entry : favoriteCurrencies.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    private static void updateFavoriteRate(Scanner scanner) {
        viewFavoriteList();

        System.out.print("Enter the currency code to update: ");
        String currencyCode = scanner.next().toUpperCase();

        if (favoriteCurrencies.containsKey(currencyCode)) {
            System.out.print("Do you want to (D)elete or (R)eplace the currency? ");
            char choice = scanner.next().toUpperCase().charAt(0);

            if (choice == 'D') {
                favoriteCurrencies.remove(currencyCode);
                System.out.println(currencyCode + " removed from the favorites list.");
            } else if (choice == 'R'){
                System.out.print("Enter the new currency code: ");
                String newCurrencyCode = scanner.next().toUpperCase();

                if (favoriteCurrencies.containsKey(newCurrencyCode)) {
                    System.out.println("Currency already exists in the favorites list. Please choose a different currency.");
                } else {
                    double rate = favoriteCurrencies.remove(currencyCode);
                    favoriteCurrencies.put(newCurrencyCode, rate);
                    System.out.println(currencyCode + " replaced with " + newCurrencyCode);
                }
            } else {
                System.out.println("Invalid choice. Please enter 'D' for Delete or 'R' for Replace.");
            }
        } else {
            System.out.println("Currency not found in the favorites list.");
        }
    }

}