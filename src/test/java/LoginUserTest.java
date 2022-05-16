import Api.User;
import Api.UserClient;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegistrationPage;
import PageObject.RestorePage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class LoginUserTest {
    private User user;
    private UserClient userClient;
    private MainPage mainPage;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandomUser();
        ValidatableResponse createResponse = userClient.createUser(user);
        accessToken = createResponse.extract().path("accessToken");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Authorization of the user by the button 'Войти в аккаунт'")
    public void userLoginMainPageWithButtonLoginAccount() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonLoginAccount();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(user.password)
                .clickButtonEnter();

        assertTrue("Button change failed, user not logged in", mainPage.buttonCreateOrderIsDisplayed());
    }

    @Test
    @DisplayName("Authorization of the user by the button 'Личный кабинет'")
    public void userLoginMainPageWithButtonPersonalAccount() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonPersonalAccountWithoutAuth();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(user.password)
                .clickButtonEnter();

        assertTrue("Button change failed, user not logged in", mainPage.buttonCreateOrderIsDisplayed());
    }

    @Test
    @DisplayName("User authorization through the button in the registration form")
    public void userLoginMainPageThroughRegistrationForm() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonPersonalAccountWithoutAuth();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickButtonRegistration();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.clickButtonEnter();

        loginPage.clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(user.password)
                .clickButtonEnter();

        assertTrue("Button change failed, user not logged in", mainPage.buttonCreateOrderIsDisplayed());
    }

    @Test
    @DisplayName("User authorization through the button in the password recovery form")
    public void userLoginMainPageThroughPasswordRecoveryForm() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonLoginAccount();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickButtonRestorePassword();

        RestorePage restorePage = page(RestorePage.class);
        restorePage.clickButtonEnter();

        loginPage.clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(user.password)
                .clickButtonEnter();

        assertTrue("Button change failed, user not logged in", mainPage.buttonCreateOrderIsDisplayed());
    }
}
