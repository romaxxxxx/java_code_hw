package homework06;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class WBPage {

    ElementsCollection QuickFilterButton = $$x("//button[contains(@class,'filter-btn')]");
    SelenideElement applySideFilterButton = $x("//button[contains(@class,'__btn-main btn-main')]");
    ElementsCollection sideFilterItems = $$x("//div[contains(@class,'filter-container')]/div/h3");
    ElementsCollection quickFilterItems = $$x("//div[contains(@class,'dropdown-filter') and not(contains(@class, '__conten'))]/button");
    SelenideElement loader = $x("//div[contains(@class, 'general-preloader')]");
    ElementsCollection itemSearched = $$x("//div[@class = 'product-card j-card-item']");
    SelenideElement searchInput = $x("//input[@id = 'searchInput']");
    SelenideElement searchButton = $x("//button[@id = 'applySearchBtn']");

    SelenideElement sideMenu = $x("//div[@class = 'filters-desktop__content']");

    @Step("Ищем {text}")
    public WBPage search(String text) {
        loader.shouldBe(Condition.disappear);
        searchInput.shouldBe(Condition.visible).sendKeys(text);
        searchButton.click();
        return this;
    }

    @Step("Проверить результат поиска на содержание в названии {text}")
    public WBPage сheckSearchResaltNamesContains(String text) {
        for (SelenideElement itemSearched : itemSearched) {
            itemSearched.$x(".//span[@class='goods-name']").shouldBe(Condition.visible, Condition.text(text));
        }
        return this;
    }

    @Step("Проверить найденный результат на цвет {color}")
    public WBPage CheckIPhoneColor(String color) {
        for (SelenideElement itemSearched : itemSearched) {
            itemSearched.$x(".//*[contains(text(),'Быстрый просмотр')]").click();
            $x("//div[contains(@class, 'j-product-popup shown')]").shouldBe(Condition.text(color));
            $x("//a[contains(@class, 'popup__close close')]").click();
        }
        return this;
    }

    @Step("Отфильтровать на боковом фильтре по модели {model}")
    public WBPage modelSideFilter(String model) {
        quickFilterItems.find(Condition.text("Все фильтры")).click();
        sideFilterItems.find(Condition.text("Модель"))
                .$x("./../following-sibling::div//following-sibling::ul/li/div/span[contains(text(), '" + model + "')]").click();
        applySideFilterButton.click();
        itemSearched.find(Condition.text(model)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Отфильтровать на быстром фильтре по цвету {color}")
    public WBPage colorQuickFilter(String color) {
        quickFilterItems.find(Condition.text("Цвет")).click();
        quickFilterItems.find(Condition.text("Цвет")).$x("./..//span[contains(@title,'" + color + "')]").click();
        QuickFilterButton.find(Condition.text("Готово")).click();
        return this;
    }
}