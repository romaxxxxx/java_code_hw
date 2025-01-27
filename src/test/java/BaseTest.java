import managers.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class BaseTest {
    WebDriverManager manager = new WebDriverManager();
    WebDriver driver;
    public void open(String url) {
        manager.open(url);
    }
    @AfterTest
    public void closeTest() {
        manager.close();
    }
}