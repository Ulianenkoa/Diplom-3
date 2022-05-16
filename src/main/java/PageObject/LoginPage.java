package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";

    // поле "Email"
    @FindBy(how = How.XPATH, using = ".//input[@name ='name']")
    private SelenideElement emailField;

    // поле "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@name ='Пароль']")
    private SelenideElement passwordField;

    // кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Войти')]")
    private SelenideElement buttonEnter;

    // кнопка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Зарегистрироваться')]")
    private SelenideElement buttonRegistration;

    // кнопка "Восстановить пароль"
    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Восстановить пароль')]")
    private SelenideElement restorePassword;

    // Заголовок страницы входа в аккаунт
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Вход')]")
    private SelenideElement titleLoginPage;

    //методы

    @Step("Click on the input field Email")
    public LoginPage clickEmailField() {
        emailField.click();
        return this;
    }

    @Step("Fill in the email field")
    public LoginPage setEmailField(String email) {
        emailField.setValue(email);
        return this;
    }

    @Step("Click on the input field Password")
    public LoginPage clickPasswordField() {
        passwordField.click();
        return this;
    }

    @Step("Fill in the password field")
    public LoginPage setPasswordField(String password) {
        passwordField.setValue(password);
        return this;
    }

    @Step("Click button 'Войти'")
    public MainPage clickButtonEnter() {
        buttonEnter.click();
        return page(MainPage.class);
    }

    @Step("Click button 'Зарегистрироваться'")
    public RegistrationPage clickButtonRegistration() {
        buttonRegistration.click();
        return page(RegistrationPage.class);
    }

    @Step("Click button 'Восстановить пароль'")
    public RestorePage clickButtonRestorePassword() {
        restorePassword.click();
        return page(RestorePage.class);
    }

    @Step("Checking the button change during user authorization")
    public boolean titleLoginPageIsDisplayed() {
        return titleLoginPage.shouldBe(visible).isDisplayed();
    }
}
