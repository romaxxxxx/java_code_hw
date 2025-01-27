package ui;

import ui.helpers.DemoblazePage;
import ui.helpers.Helper;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class DemoblazeTests extends Helper {
    @Owner("Колбенков Роман")
    @Test(description = "Оформление заказа")
    public void orderRegistration() {
        open("https://www.demoblaze.com");
        DemoblazePage demoblazePage = new DemoblazePage();
        demoblazePage.registration(userName, password)
                .authentication(userName, password);
                data = demoblazePage.addProductToCart();
        demoblazePage.orderRegistration(data.productPriceSum, data.chooseProductsName);
    }
}