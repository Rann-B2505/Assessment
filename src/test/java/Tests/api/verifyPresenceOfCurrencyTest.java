package Tests.api;

import DataProvider.DataProvider_Excel;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static api.clients.conversationRatesUsd;
import static org.apache.http.HttpStatus.SC_OK;
import static util.BasedAPIClient.baseRequestContext;

public class verifyPresenceOfCurrencyTest {

    private static APIRequestContext requestContext;
    private static APIResponse apiResponse;

    @Test(dataProvider = "ExchangeRateApi", dataProviderClass = DataProvider_Excel.class)
    public void verifyPresenceOfCurrency(String CurrencyToSearch) {
        requestContext = baseRequestContext();
        apiResponse = requestContext.get(conversationRatesUsd);
        Assert.assertEquals(apiResponse.status(),SC_OK);

        // Parse the response as a JSON object
        JSONObject jsonResponse = new JSONObject(apiResponse.text());

        // Get the "conversion_rates" object
        JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");

        // Verify if selected currency is present within the "conversion_rates" object
        Assert.assertTrue(conversionRates.has(CurrencyToSearch));

    }

    @AfterMethod
    public void tearDown(){
        requestContext.dispose();
    }
}
