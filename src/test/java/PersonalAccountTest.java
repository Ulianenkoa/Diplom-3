import Api.User;
import Api.UserClient;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.ProfilePage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTest {
    private User user;
    private UserClient userClient;
    private MainPage mainPage;
    private String accessToken;
    private LoginPage loginPage;
    private ProfilePage profilePage;


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
    @DisplayName("User transition by clicking the 'Личный кабинет' button without authorization")
    public void userTransitionPersonalAccountWithoutAuthorization() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonPersonalAccountWithoutAuth();

        loginPage = page(LoginPage.class);
        assertTrue("Personal account is not available, the user is not authorized", loginPage.titleLoginPageIsDisplayed());
    }

    @Test
    @DisplayName("User transition by clicking the 'Личный кабинет' button with authorization")
    public void userTransitionPersonalAccountWithAuthorization() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonLoginAccount();

        loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(user.password)
                .clickButtonEnter();

        mainPage.clickButtonPersonalAccountWithAuth();
        profilePage = page(ProfilePage.class);

        assertTrue("Personal account is not available, the user is not authorized", profilePage.buttonExitIsDisplayed());
    }

    @Test
    @DisplayName("User transition by clicking the 'Конструктор' button with authorization")
    public void userTransitionConstructorFromPersonalAccount() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonLoginAccount();

        loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(user.password)
                .clickButtonEnter();

        mainPage.clickButtonPersonalAccountWithAuth();
        profilePage = page(ProfilePage.class);
        profilePage.clickButtonConstructor();

        assertTrue("Transition to constructor failed", mainPage.titleMainPageIsDisplayed());
    }

    @Test
    @DisplayName("User transition by clicking the 'Stellar Burgers' logo with authorization")
    public void userTransitionLogoFromPersonalAccount() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonLoginAccount();

        loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(user.password)
                .clickButtonEnter();

        mainPage.clickButtonPersonalAccountWithAuth();
        profilePage = page(ProfilePage.class);
        profilePage.clickLogoStellarBurgersHeader();

        assertTrue("Transition to main page failed", mainPage.titleMainPageIsDisplayed());
    }

    @Test
    @DisplayName("User transition by clicking the 'Выход' button with authorization")
    public void userLogoutFromPersonalAccount() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonLoginAccount();

        loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(user.password)
                .clickButtonEnter();

        mainPage.clickButtonPersonalAccountWithAuth();

        profilePage = page(ProfilePage.class);
        profilePage.clickButtonExit();

        assertTrue("Account logout failed", loginPage.titleLoginPageIsDisplayed());
    }
}
