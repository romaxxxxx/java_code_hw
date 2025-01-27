package Helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.google.common.base.Splitter;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class DemoblazePage extends Helper {
    SelenideElement menuItem(String menuItemName) {
        return $x("//li[contains(@class, 'nav-item')]/a[contains(text(), '" + menuItemName + "') " +
                "and not(contains(@style, 'display: none'))]");
    }

    SelenideElement button(String buttonName) {
        return $x("//button[@type = 'button' and text() = '" + buttonName + "']");
    }

    SelenideElement buttonHref(String hrefName) {
        return $x("//a[(text() = '" + hrefName + "') and contains(@class, 'btn')]");
    }

    SelenideElement passwordInput(String id) {
        return $x("//input[@type = 'password' and @id = '" + id + "']");
    }

    SelenideElement textInput(String id) {
        return $x("//input[@type = 'text' and @id = '" + id + "']");
    }

    SelenideElement attributeValue(String attributeId) {
        return $x("//*[@id = '" + attributeId + "']");
    }

    ElementsCollection categories = $$x("//a[@id = 'itemc']");
    ElementsCollection pruductsList = $$x("//div[@class = 'card h-100']");
    SelenideElement productCard = $x("//div[contains(@class, 'product-deatil')]");
    ElementsCollection productsCart = $$x("//h2[text() = 'Products']/..//tr[@class = 'success']/td[2]");
    SelenideElement orderInfo = $x("//p[contains(@class, 'lead')]");


    @Step("Регистрация на сайте: логин - {login}, пароль - {password}")
    public DemoblazePage registration(String login, String password) {
        menuItem("Sign up").click();
        textInput("sign-username").shouldBe(Condition.exist).sendKeys(login);
        passwordInput("sign-password").setValue(password);
        button("Sign up").click();
        Alert alert = switchTo().alert();
        Assert.assertEquals(alert.getText(), "Sign up successful.");
        alert.accept();
        return this;
    }

    @Step("Аутентификация на сайте: логин - {login}, пароль - {password}")
    public DemoblazePage authentication(String login, String password) {
        menuItem("Log in").click();
        textInput("loginusername").setValue(login);
        passwordInput("loginpassword").setValue(password);
        button("Log in").click();
        return this;
    }

    @Step("Добавление по одному товару из всех категорий в корзину")
    public Data addProductToCart() {
        String listPriceProduct = "";
        String cardPriceProduct = "";
        int productPriceSum = 0;
        List<String> chooseProductsName = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            categories.get(i).should(Condition.exist).click();
            listPriceProduct = pruductsList.get(1).$x("./div/h5").getText();
            pruductsList.get(1).$x("./a").scrollTo().should(Condition.exist).click();
            cardPriceProduct = productCard.$x(".//h3[@class = 'price-container']").getOwnText();
            Assert.assertEquals(listPriceProduct, cardPriceProduct);
            chooseProductsName.add(productCard.$x(".//h2[@class = 'name']").getText());
            productPriceSum = productPriceSum + Integer.parseInt(cardPriceProduct.substring(1));
            buttonHref("Add to cart").click();
            Alert alert = switchTo().alert();
            Assert.assertEquals(alert.getText(), "Product added.");
            alert.accept();
            menuItem("Home ").click();
        }
        data.chooseProductsName = chooseProductsName;
        data.productPriceSum = productPriceSum;
        return data;
    }

    @Step("Формирование заказа")
    public DemoblazePage orderRegistration(int productPriceSum, List<String> chooseProductsName) {
        int totalPriceCat = 0;
        String currentSystemDate = new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Calendar.getInstance().getTime());
        menuItem("Cart").click();
        for (String product : chooseProductsName) {
            productsCart.find(Condition.text(product)).should(Condition.visible);
        }
        totalPriceCat = Integer.parseInt(attributeValue("totalp").should(Condition.visible).getOwnText());
        Assert.assertEquals(totalPriceCat, productPriceSum);
        button("Place Order").click();
        textInput("name").sendKeys(clientName);
        textInput("country").sendKeys(country);
        textInput("city").sendKeys(city);
        textInput("card").sendKeys(card);
        textInput("month").sendKeys(month);
        textInput("year").sendKeys(year);
        button("Purchase").click();
        Map<String, String> orderInfoMap = Splitter.on("\n")
                .withKeyValueSeparator(":")
                .split(orderInfo.getText());
        String orderDate = orderInfoMap.get("Date").substring(1);
        Assert.assertEquals(currentSystemDate, orderDate);
        return this;
    }
}


