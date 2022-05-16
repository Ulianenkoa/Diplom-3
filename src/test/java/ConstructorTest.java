import PageObject.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class ConstructorTest {
    private MainPage mainPage;

    @Before
    public void setUp() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
    }

    @Test
    @DisplayName("Checking for the display of ingredients (buns)")
    public void choseSectionBunFromConstructor() {
        mainPage.clickSauceTub();
        mainPage.clickBunTub();

        assertTrue(mainPage.titleCategoryBunIsDisplayed());
    }

    @Test
    @DisplayName("The selected section is underlined (buns)")
    public void checkUnderlineSectionBun() {
        mainPage.clickSauceTub();
        mainPage.clickBunTub()
                .getActiveSectionText();

        assertThat("Wrong category selected", mainPage.getActiveSectionText(), equalTo("Булки"));
    }

    @Test
    @DisplayName("Checking for the display of ingredients (Sauce)")
    public void choseSectionSauceFromConstructor() {
        mainPage.clickSauceTub();

        assertTrue(mainPage.titleCategorySauceIsDisplayed());
    }

    @Test
    @DisplayName("The selected section is underlined (Sauce)")
    public void checkUnderlineSectionSauce() {
        mainPage.clickSauceTub()
                .getActiveSectionText();

        assertThat("Wrong category selected", mainPage.getActiveSectionText(), equalTo("Соусы"));
    }

    @Test
    @DisplayName("Checking for the display of ingredients (Filling)")
    public void choseSectionFillingFromConstructor() {
        mainPage.clickFillingTub();

        assertTrue(mainPage.titleCategoryFillingIsDisplayed());
    }

    @Test
    @DisplayName("The selected section is underlined (Filling)")
    public void checkUnderlineSectionFilling() {
        mainPage.clickFillingTub()
                .getActiveSectionText();

        assertThat("Wrong category selected", mainPage.getActiveSectionText(), equalTo("Начинки"));
    }
}
