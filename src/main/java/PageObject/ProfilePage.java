package PageObject;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {
    public static final String REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //Кнопка конструктор
    @FindBy(how = How.XPATH, using = ".//*[contains (text(), 'Конструктор')]")//
    private SelenideElement buttonConstructorHeader;
    //Логотип stellar burgers
    @FindBy(how = How.CSS, using = ".AppHeader_header__logo__2D0X2")
    private SelenideElement logoStellarBurgersHeader;
    // кнопка "Выйти"
    @FindBy(how = How.XPATH, using = ".//button[contains (text(), 'Выход')]")
    private SelenideElement buttonExit;

    //методы
    @Step("Click button 'Конструктор'")
    public MainPage clickButtonConstructor() {
        buttonConstructorHeader.click();
        return page(MainPage.class);
    }

    @Step("Click button 'Логотип stellar burgers'")
    public MainPage clickLogoStellarBurgersHeader() {
        logoStellarBurgersHeader.click();
        return page(MainPage.class);
    }

    @Step("Click button 'Выход'")
    public LoginPage clickButtonExit() {
        buttonExit.click();
        return page(LoginPage.class);
    }

    @Step("Checking the logout button")
    public boolean buttonExitIsDisplayed() {
        return buttonExit.shouldBe(visible).isDisplayed();
    }

}
