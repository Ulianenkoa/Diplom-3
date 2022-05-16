import Api.User;
import Api.UserClient;
import Api.UserCredentials;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class RegistrationUserTest {

    private User user;
    private UserClient userClient;
    private MainPage mainPage;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandomUser();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Successful registration")
    public void successfulRegistrationTest() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonPersonalAccountWithoutAuth();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickButtonRegistration();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.clickNameField()
                .setNameField(user.name)
                .clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(user.password)
                .clickButtonRegistration();
        ValidatableResponse loginResponse = userClient.loginUser(UserCredentials.builder().email(user.email).password(user.password).build());
        accessToken = loginResponse.extract().path("accessToken");

        assertThat("AccessToken - missing, user not logged in", accessToken, notNullValue());
    }

    @Test
    @DisplayName("Unsuccessful registration")
    public void unsuccessfulRegistrationTest() {
        MainPage mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonPersonalAccountWithoutAuth();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickButtonRegistration();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.clickNameField()
                .setNameField(user.name)
                .clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(RandomStringUtils.randomAlphabetic(5))
                .clickButtonRegistration();

        assertTrue("Missing error text when entering a password less than 6 characters", registrationPage.textErrorPasswordFieldIsDisplayed());
    }
}
