package util;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import static util.PropertiesReader.getProperty;

public class BasedAPIClient {
    protected static final String baseUrl = getProperty("api.base.url");
    public static final APIRequestContext baseRequestContext() {
        Playwright playwright = Playwright.create();
        APIRequest request = playwright.request();
        APIRequestContext requestContext = request.newContext(new
                APIRequest.NewContextOptions()
                .setBaseURL(baseUrl));
        return requestContext;
    }



}

