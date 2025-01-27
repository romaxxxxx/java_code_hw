package ui.managers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class WebDriverManager {
    static{
        Configuration.browser = "chrome";
        Configuration.browserVersion = "132";
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "0x0";
        Configuration.timeout = 30*1000;
        Configuration.pageLoadTimeout = 30*100;
        Configuration.savePageSource = false;
        Configuration.holdBrowserOpen = false;
    }
    @Step("Открыть браузер на странице {url}")
    public void open(String url){
        Selenide.open(url);
    }

    @Step("Закрыть браузер")
    public void close(){
        Selenide.closeWebDriver();
    }

}
