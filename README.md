PROGRAMMING TEST

Write a command-line Java program that fetches data from the following public API:
https://www.coindesk.com/coindesk-api

Once executed, the program should request the user to input a currency code (USD, EUR, GBP, etc.)

Once the user provides the currency code, the application should display the following information:
-	The current Bitcoin rate, in the requested currency
-	The lowest Bitcoin rate, in requested date range (in USD)
-	The highest Bitcoin rate, in requested date range (in USD)

If the currency code provided is not supported by the API, a message should inform the user.

Additional requirements:
-	Application should have unit test coverage.
-	The code should follow good practices and have comments.
-	The final result of this exercise should be an executable JAR file that can be run in any platform 
in a standard Java VM (Java 8) along with a ZIP file with the source code.
