import homework06.WBPage;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class WBTests extends BaseTest{
    @Owner("Колбенков Роман")
    @Test(description = "Проверка поиска и фильтрации на WB")
    public void iphoneSearch(){
    open("https://www.wildberries.ru/");
    WBPage wbPage = new WBPage();
    wbPage.search("iPhone")
            .сheckSearchResaltNamesContains("iPhone")
            .modelSideFilter("iPhone 12")
            .сheckSearchResaltNamesContains("iPhone 12")
            .colorQuickFilter("синий")
            .CheckIPhoneColor("синий");



    }
}
