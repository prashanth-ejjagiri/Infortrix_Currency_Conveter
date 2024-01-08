# Currency Converter Documentation

## Introduction

The Currency Converter is a Java program that enables users to convert currencies using real-time exchange rates. It leverages the ExchangeRateHost API to fetch the latest exchange rates between various currencies.

## Features

1. **Convert Currency:** Convert an amount from one currency to another based on the latest exchange rates.

2. **Add to Favorite Currency List:** Add a currency to your favorite list for quick access.

3. **View Favorite Currency List:** Display the list of currencies added to the favorites.

4. **Update Favorite Currency Rate:** Update, replace, or remove a currency from the favorites list.

5. **Quit:** Exit the Currency Converter program.

## Usage

1. **Access Key:** The program requires an access key from ExchangeRateHost to fetch the latest exchange rates. The default access key in the provided code is `25508ceaf87e687ffb4cba7788f4b380`. Replace it with your key for production use.

2. **Main Menu:**
   - Choose from options 1 to 5 to perform different actions.

3. **Convert Currency:**
   - Enter the amount to convert.
   - Input the source currency code.
   - Specify the target currency code.
   - The program will display the converted amount.

4. **Add to Favorite Currency List:**
   - Enter the currency code to add to the favorites.
   - The program will add the currency to the favorites with its current exchange rate.

5. **View Favorite Currency List:**
   - Displays the list of currencies in the favorites along with their exchange rates.

6. **Update Favorite Currency Rate:**
   - View the current favorites list.
   - Choose to delete or replace a currency.
   - If replacing, enter the new currency code.
   - The program will update the favorites accordingly.

7. **Quit:**
   - Choose option 5 to exit the program.

## Dependencies

The program utilizes the following libraries:

- [OkHttp](https://square.github.io/okhttp/): Used for making HTTP requests to the ExchangeRateHost API.
- [JSON-java](https://github.com/stleary/JSON-java): Used for parsing JSON responses.

## Installation

1. Clone the repository: `git clone https://github.com/yourusername/yourrepository.git`
2. Open the project in your preferred Java development environment (IDE).
3. Replace the `ACCESS_KEY` in the `CurrencyConverter` class with your ExchangeRateHost API key.
4. Run the program.

## Troubleshooting

- If you encounter issues with the ExchangeRateHost API key or network connectivity, check your internet connection and ensure the API key is valid.

## Disclaimer

This Currency Converter is provided as-is without any warranty. Use it responsibly and ensure compliance with ExchangeRateHost's terms of use.

Feel free to modify and enhance the documentation based on your specific project requirements and additional features.
