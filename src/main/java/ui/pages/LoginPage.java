package ui.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;
    public LoginPage(Page page) {
        this.page = page;
    }
    public static String Username_Input = "#user-name";
    public static String Password_Input = "#password";
    public static String Login_Btn = "#login-button";
}
