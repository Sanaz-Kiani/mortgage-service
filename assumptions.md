# Assumptions

To ensure accurate calculations and proper understanding, the following assumptions have been made:

1. Monthly Cost Calculation Formula: The formula used to calculate the monthly cost is as follows: `(loan amount * interest rate/100) / period`.

2. Minimum Values: The following minimum values have been assumed for the calculations:
  - Minimum income: $2000
  - Minimum loan value: $30000
  - Minimum home value: $30000
  - Minimum period: 10 month

3. Returning Monthly Cost: The API is designed to return the monthly cost, even if the loan is not feasible based on the provided data. This ensures that users receive a response with the calculated cost, allowing them to make informed decisions.

4. Default Rates: The application incorporates pre-configured default rates based on different maturity periods. These rates are as follows:

  - Maturity Period: 5 months
    - Interest Rate: 3.5%
    - Last Update: [current date and time]

  - Maturity Period: 10 months
    - Interest Rate: 4.2%
    - Last Update: [current date and time]

  - Maturity Period: 15 months
    - Interest Rate: 4.8%
    - Last Update: [current date and time]
