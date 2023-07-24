package tv.okko;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tv.okko.pages.MainPage;

public class okkoMainPageTests extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    @Tag("all")
    @DisplayName("Проверка отображения хедера c названиями разделов на главной странице")
    void checkHeader() {
        mainPage.openMainPage().checkHeaderMainPage();
    }

    @Test
    @Tag("all")
    @DisplayName("При вводе текста в графу Промокод кнопка Активировать становится активна для нажатия")
    void checkPromoCodeButtonEnabled() {
        mainPage.openMainPage()
                .checkButtonPromoCode()
                .checkPromoInputEnabled("test");
    }

    @Test
    @Tag("all")
    @DisplayName("Кнопка Активировать не активна для нажатия без ввода промокода")
    void checkPromoCodeButtonDisabled() {
        mainPage.openMainPage()
                .checkButtonPromoCode()
                .checkPromoInputDisabled();
    }

    @Test
    @Tag("all")
    @DisplayName("Кнопка поиск активна, при ее нажатии отображается поле для поиска с плейсхолдером")
    void checkSearch() {
        mainPage.openMainPage()
                .checkSearchButton()
                .checkInputTypeSearchPlaceholder();
    }

    @Test
    @Tag("all")
    @DisplayName("Проверка загрузки img с логотипом")
    void checkSearchOnSite() {
        mainPage.openMainPage()
                .checkImgLogo();
    }

}
