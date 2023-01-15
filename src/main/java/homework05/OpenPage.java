package homework05;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

public class OpenPage {
    Actions actions;
    protected WebDriver driver;
    public WebElement currencyTable;
    List<WebElement> rowsCurrency;
    Map<String, List<Float>> currencyRates;
    public OpenPage(WebDriver driver) {
        this.driver = driver;
        currencyTable = driver.findElement(By.xpath("(//div[@class = 'main-page-info__block'])[1]"));
        rowsCurrency = new ArrayList<>(driver.findElements(By.xpath("//tr[contains(@class,'row')]")));
    }

    public OpenPage moveToPurSaleTable() {
        actions = new Actions(driver);
        actions.moveToElement(currencyTable).perform();
        return this;
    }

    public OpenPage parsingPurSaleTable() {
        currencyRates = new HashMap<>();
        for (WebElement rowCurr : rowsCurrency) {
            float purchase = Float.parseFloat(rowCurr.findElement(By.xpath(".//td[2]")).getText().replace(",", "."));
            float sale = Float.parseFloat(rowCurr.findElement(By.xpath(".//td[4]")).getText().replace(",", "."));
            currencyRates.put(rowCurr.findElement(By.xpath(".//span[contains(@class,'currency-name')]")).getText(), new ArrayList<>(Arrays.asList(purchase, sale)));
        }
        return this;
    }

    public OpenPage checkCurrencyPurSale() {
        for (List<Float> r : currencyRates.values()) {
            Assertions.assertTrue(r.get(0) < r.get(1));
        }
        return this;
    }
}