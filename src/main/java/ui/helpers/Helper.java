package ui.helpers;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;

public class Helper {
    public Faker faker = new Faker();
    public Data data = new Data();
    public String clientName = faker.name().name();
    public String country = faker.country().name();
    public String city = faker.address().cityName();
    public String card = faker.finance().creditCard();
    public String month = LocalDateTime.now().getMonth().toString();
    public String year = String.valueOf(LocalDateTime.now().getYear());
    public String userName = faker.name().name() + (int) (Math.random() * 20);
    public String password = faker.code().toString();
}
