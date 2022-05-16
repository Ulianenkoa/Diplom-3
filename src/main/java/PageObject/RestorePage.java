package PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RestorePage {
    public static final String FORGOT_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //кнопка "Войти"
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj")
    private SelenideElement buttonEnter;

    @Step("Click button 'Войти'")
    public LoginPage clickButtonEnter() {
        buttonEnter.click();
        return page(LoginPage.class);
    }
}
