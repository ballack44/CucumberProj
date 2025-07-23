package com.tj.cucumber.steps;

import com.tj.cucumber.Item;
import com.tj.cucumber.ItemComparator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import java.util.*;
import static org.junit.Assert.assertTrue;


public class ComparisonSteps {
    private List<Item> list1 = new ArrayList<>();
    private List<Item> list2 = new ArrayList<>();
    private String comparisonLog;
    private boolean comparisonResult;
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("I have the following items in the first list:")
    public void first_list(DataTable table) {
        list1 = parseItems(table);
    }

    @Given("I have the following items in the second list:")
    public void second_list(DataTable table) {
        list2 = parseItems(table);
    }

    @When("I compare both lists")
    public void compare_lists() {
        comparisonLog = ItemComparator.compareLists(list1, list2);
        comparisonResult = comparisonLog.isEmpty(); // if log is empty, they matched
        scenario.log(comparisonLog); //adds it to cucumber log
    }

    @Then("the lists should contain the same items with name, price, and category, regardless of order")
    public void verify_lists() {
        if (!comparisonResult) {
            scenario.log("Comparison failed:\n" + comparisonLog);
        }
        assertTrue("The lists did not match. See logs for details.", comparisonResult);
    }

    private List<Item> parseItems(DataTable table) {
        List<Map<String, String>> rows = table.asMaps();
        List<Item> items = new ArrayList<>();
        for (Map<String, String> row : rows) {
            items.add(new Item(
                    row.get("name"),
                    Double.parseDouble(row.get("price")),
                    row.get("category")
            ));
        }
        return items;
    }
}