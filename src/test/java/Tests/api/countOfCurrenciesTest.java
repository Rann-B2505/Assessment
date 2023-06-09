package Tests.api;

import DataProvider.DataProvider_Excel;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.json.JSONObject;

import static api.clients.conversationRatesUsd;
import static org.apache.http.HttpStatus.SC_OK;
import static util.BasedAPIClient.baseRequestContext;

public class countOfCurrenciesTest {

    private static APIRequestContext requestContext;
    private static APIResponse apiResponse;

    @Test
    public void countOccurrencesOfCurrenciesTest() {
        requestContext = baseRequestContext();
        apiResponse = requestContext.get(conversationRatesUsd);
        Assert.assertEquals(apiResponse.status(),SC_OK);

        // Parse the response as a JSON object
        JSONObject jsonResponse = new JSONObject(apiResponse.text());

        // Get the "conversion_rates" object
        JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");

        // Count the number of items in the "conversion_rates" object
        int itemCount = conversionRates.length();

        System.out.println("Number of currencies in conversion_rates: " + itemCount);
    }

    @AfterMethod
    public void tearDown(){
        requestContext.dispose();
    }
}
