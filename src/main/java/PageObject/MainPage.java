package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Objects;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static io.netty.util.internal.SystemPropertyUtil.contains;

public class MainPage {
    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";

    //Кнопка Личный кабинет
    @FindBy(how = How.XPATH, using = ".//p[contains(text(),'Личный Кабинет')]")
    private SelenideElement buttonPersonalAccount;
    //Кнопка Войти в аккаунт
    @FindBy(how = How.XPATH, using = ".//*[contains (text(), 'Войти в аккаунт')]")//
    private SelenideElement buttonLoginAccount;
    //Кнопка Оформить заказ
    @FindBy(how = How.XPATH, using = ".//*[contains (text(), 'Оформить заказ')]")//
    private SelenideElement buttonCreateOrder;
    //Раздел Булки
    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Булки')]")
    private SelenideElement bunTub;
    //Раздел Соусы
    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Соусы')]")
    private SelenideElement sauceTub;
    //Раздел Начинки
    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Начинки')]")
    private SelenideElement fillingTub;
    //заголовок раздела Булок
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Булки')]")
    private SelenideElement titleBunSection;
    //Заголовок раздела Соусов
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Соусы')]")
    private SelenideElement titleSauceSection;
    //заголовок раздела Начинок
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Начинки')]")
    private SelenideElement titleFillingSection;

    //заголовок Главной страницы
    @FindBy(how = How.XPATH, using = ".//h1[contains(text(),'Соберите бургер')]")
    private SelenideElement titleMainPage;
    //выбранный раздел
    @FindBy(how = How.CSS, using = ".tab_tab_type_current__2BEPc")
    public SelenideElement activeSection;

    //методы

    @Step("Click button 'Личный кабинет'.Unauthorized user")
    public LoginPage clickButtonPersonalAccountWithoutAuth() {
        buttonPersonalAccount.click();
        return page(LoginPage.class);
    }

    @Step("Click button 'Личный кабинет'.Authorized user")
    public ProfilePage clickButtonPersonalAccountWithAuth() {
        buttonPersonalAccount.click();
        return page(ProfilePage.class);
    }

    @Step("Click button 'Войти в аккаунт'")
    public LoginPage clickButtonLoginAccount() {
        buttonLoginAccount.click();
        return page(LoginPage.class);
    }

    @Step("Click on the section with 'Bun'")
    public MainPage clickBunTub() {
        bunTub.click();
        return this;
    }

    @Step("Checking the title for the selected category 'Bun'")
    public boolean titleCategoryBunIsDisplayed() {
        return titleBunSection.shouldBe(visible).isDisplayed();
    }

    @Step("Click on the section with 'Sauce'")
    public MainPage clickSauceTub() {
        sauceTub.click();
        return this;
    }

    @Step("Checking the title for the selected category 'Sauce'")
    public boolean titleCategorySauceIsDisplayed() {
        return titleSauceSection.shouldBe(visible).isDisplayed();
    }

    @Step("Click on the section with 'Filling'")
    public MainPage clickFillingTub() {
        fillingTub.click();
        return this;
    }

    @Step("Checking the title for the selected category 'Filling'")
    public boolean titleCategoryFillingIsDisplayed() {
        return titleFillingSection.shouldBe(visible).isDisplayed();
    }

    @Step("Checking the button change during user authorization")
    public boolean buttonCreateOrderIsDisplayed() {
        return buttonCreateOrder.shouldBe(visible).isDisplayed();
    }

    @Step("Checking the title MainPage")
    public boolean titleMainPageIsDisplayed() {
        return titleMainPage.shouldBe(visible).isDisplayed();
    }

    @Step("Получение текста активного раздела")
    public String getActiveSectionText() {
        return activeSection.getText();
    }

    @Step("Checking the button change during user unauthorization")
    public boolean buttonLoginAccountIsDisplayed() {
        return buttonLoginAccount.isDisplayed();
    }
}
