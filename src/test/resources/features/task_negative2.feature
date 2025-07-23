Feature: Compare two item lists - price difference and one extra item
  Scenario: Validate that two lists contain the same items with name, price, and category, regardless of order
    Given I have the following items in the first list:
      | name   | price | category |
      | apple  | 1.00  | fruit    |
      | orange | 1.10  | fruit    |
      | banana | 0.75  | fruit    |
    And I have the following items in the second list:
      | name    | price | category |
      | banana  | 0.75  | fruit    |
      | apple   | 1.00  | fruit    |
      | orange  | 1.50  | fruit    |
      | apricot | 1.20  | fruit    |
    When I compare both lists
    Then the lists should contain the same items with name, price, and category, regardless of order