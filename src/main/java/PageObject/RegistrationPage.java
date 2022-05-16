package PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {
    public static final String REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/register";

    // поле "Имя"
    @FindBy(how = How.XPATH, using = ".//fieldset[1]//input")
    private SelenideElement nameField;

    // поле "Email"
    @FindBy(how = How.XPATH, using = ".//fieldset[2]//input")
    private SelenideElement emailField;

    // поле "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@type='password']")
    private SelenideElement passwordField;

    // кнопка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Зарегистрироваться')]")
    private SelenideElement buttonRegistration;

    // кнопка "Войти"
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj")
    private SelenideElement buttonEnter;

    // кнопка "Конструктор"
    @FindBy(how = How.XPATH, using = ".//*[contains (text(), 'Конструктор')]")
    private SelenideElement buttonConstructor;

    // текст об ошибке "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//*[contains (text(), 'Некорректный пароль')]")
    private SelenideElement textErrorPassword;

    //методы
    @Step("Click on the input field Name")
    public RegistrationPage clickNameField() {
        nameField.click();
        return this;
    }

    @Step("Fill in the name field")
    public RegistrationPage setNameField(String name) {
        nameField.setValue(name);
        return this;
    }

    @Step("Click on the input field Email")
    public RegistrationPage clickEmailField() {
        emailField.click();
        return this;
    }

    @Step("Fill in the email field")
    public RegistrationPage setEmailField(String email) {
        emailField.setValue(email);
        return this;
    }

    @Step("Click on the input field Password")
    public RegistrationPage clickPasswordField() {
        passwordField.click();
        return this;
    }

    @Step("Fill in the password field")
    public RegistrationPage setPasswordField(String password) {
        passwordField.setValue(password);
        return this;
    }

    @Step("Click button 'Зарегистрироваться'")
    public LoginPage clickButtonRegistration() {
        this.buttonRegistration.click();
        return page(LoginPage.class);
    }

    @Step("Click button 'Войти'")
    public LoginPage clickButtonEnter() {
        buttonEnter.click();
        return page(LoginPage.class);
    }

    @Step("Click button 'Конструктор'")
    public MainPage clickButtonConstructor() {
        buttonConstructor.click();
        return page(MainPage.class);
    }

    @Step("Checking the text of the error output when entering the wrong password")
    public boolean textErrorPasswordFieldIsDisplayed() {
        return textErrorPassword.isDisplayed();
    }
}
