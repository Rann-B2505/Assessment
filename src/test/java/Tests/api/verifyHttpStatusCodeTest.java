package Tests.api;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static api.clients.conversationRatesUsd;
import static org.apache.http.HttpStatus.SC_OK;
import static util.BasedAPIClient.baseRequestContext;

public class verifyHttpStatusCodeTest {

    private static APIRequestContext requestContext;
    private static APIResponse apiResponse;

    @Test
    public void verifyHttpStatus() {
        requestContext = baseRequestContext();
        apiResponse = requestContext.get(conversationRatesUsd);
        // Verify the HTTP status is 200
        Assert.assertEquals(apiResponse.status(),SC_OK);

    }

    @AfterMethod
    public void tearDown(){
        requestContext.dispose();
    }
}
