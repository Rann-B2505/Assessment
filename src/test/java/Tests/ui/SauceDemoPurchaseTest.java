package Tests.ui;

import DataProvider.DataProvider_Excel;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.CartPage;
import ui.pages.Header;
import ui.pages.ProductPage;

import java.text.ParseException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ui.pages.Checkout_CompletePage.*;
import static ui.pages.Checkout_OverviewPage.*;
import static ui.pages.Checkout_PersonalInformationPage.*;
import static ui.pages.Header.Menu_Btn;
import static ui.pages.LoginPage.*;
import static ui.pages.MenuPage.Reset_App_State_link;
import static util.Decryption.decodeCredentials;
import static util.PropertiesReader.getProperty;
import static util.StringsProvider.*;

public class SauceDemoPurchaseTest {
    private static Browser browser;
    private static Playwright playwright;
    private static Page page;


    @BeforeClass
    public void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        page = browser.newPage();
    }

    @Test(dataProvider = "Saucedemo", dataProviderClass = DataProvider_Excel.class)
    public void purchaseItem(String test_name,String item_id,String inventory_item_name, String inventory_item_price, String tax, String total) throws InterruptedException, ParseException {

        System.out.println("Starting test - Purchase Item "+test_name);

        //Navigate To Page
        page.navigate(getProperty("ui.base.url"));
        //Login
        page.locator(Username_Input).type(decodeCredentials(getProperty("sauce_username")));
        page.locator(Password_Input).type(decodeCredentials(getProperty("sauce_password")));
        page.locator(Login_Btn).click();
        //Add one item to the cart
        page.locator(item_id).click();
        page.getByText(ProductPage.AddToCart_Btn).click();
        //Go to basket
        page.locator(Header.ShoppingCart_Btn).click();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(inventory_item_name))).isVisible();
        assertThat(page.getByText(inventory_item_price)).isVisible();
        //Check out
        page.locator(CartPage.Checkout_Btn).click();
        //Enter personal info and continue
        page.locator(FirstName_Input).type(getRandomFirstName());
        page.locator(LastName_Input).type(getRandomLastName());
        page.locator(PostalCode_Input).type(getRandomPostalCode());
        page.locator(Continue_Btn).click();
        //Checkout Overview
        assertThat(page.locator(ItemQuantity).getByText("1", new Locator.GetByTextOptions().setExact(true))).isVisible();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(inventory_item_name))).isVisible();
        assertThat(page.getByText(inventory_item_price, new Page.GetByTextOptions().setExact(true))).isVisible();
        assertThat(page.getByText(PaymentInformation)).isVisible();
        assertThat(page.getByText(SauceCard)).isVisible();
        assertThat(page.getByText(ShippingInformation)).isVisible();
        assertThat(page.getByText(DeliveryOption)).isVisible();
        assertThat(page.getByText(PriceTotal)).isVisible();
        assertThat(page.getByText(ItemTotal+inventory_item_price)).isVisible();
        assertThat(page.getByText(Tax+tax)).isVisible();
        assertThat(page.getByText(Total+total)).isVisible();
        //Finish
        page.locator(FinishBtn).click();
        assertThat(page.getByText(CheckoutComplete)).isVisible();
        assertThat(page.getByText(ThankYou)).isVisible();
        page.locator(BackHomeBtn).click();

    }

    @AfterMethod
    public void teardown(){

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(Menu_Btn)).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Reset_App_State_link)).click();
    }



}

