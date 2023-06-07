package tests.order;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.containsString;
import org.hamcrest.MatcherAssert;

import pages.OrderBlock;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class OrderTests {
    private WebDriver driver;

    private final String firstName;
    private final String lastName;
    private final String adress;
    private final int metro;
    private final String phone;
    private final String date;
    private final int rent;
    private final String color;
    private final String comment;

    public OrderTests(String firstName, String lastName, String adress, int metro, String phone, String date, int rent, String color, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rent = rent;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getCities() {
        return new Object[][] {
                { "Иван", "Иванов", "Красная площадь", 13, "88005553535", "07.06.23", 3, "black", "Комментарий"}, //все поля заполнены корректно
                { "Петр", "Петров", "", 26, "12345678901", "01.05.22", 7, "", ""}, //только обязательные поля заполнены
                { "", "Сидоров", "Улица", 33, "11111111111", "31.07.24", 5, "white", "Самокат"}, //тест упадёт из-за пустого имени
        };
    }

    @Test
    public void checkHeaderOrder() { //Заказ из хедера
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        OrderBlock orderBlock = new OrderBlock(driver);

        orderBlock.clickHeaderOrder(); //Жмём Заказать в хедере
        orderBlock.enterFirstName(firstName); // Заполняем Имя
        orderBlock.enterLastName(lastName); //Заполняем Фамилию
        orderBlock.enterAdress(adress); // Заполняем Адрес
        orderBlock.enterMetro(metro); // Выбираем станцию метро
        orderBlock.enterPhone(phone); // Заполняем номер телефона
        orderBlock.clickNextButton(); // Жмём Далее

        orderBlock.enterDatePicker(date); // Выбираем дату
        orderBlock.enterRentPeriod(rent); // Выбираем срок аренды
        if(color == "black") { // Выбираем цвет
            orderBlock.enterСolorBlack();
        }
        if (color == "grey") { //
            orderBlock.enterСolorGrey();
        }
        orderBlock.enterComment(comment); // Заполняем комментарий
        orderBlock.clickFinalOrder(); // Жмём Заказать
        orderBlock.clickYesButton(); // Жмём Да

        String expected = "Заказ оформлен";
        String actual = orderBlock.getOrderConfirmationText(); // Получаем текст о подтверждении заказа
        MatcherAssert.assertThat("Заказ не получилось оформить", actual, containsString(expected)); // Сравнение текста
    }

    @Test
    public void checkMiddleOrder() { //Заказ из центра страницы
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        OrderBlock orderBlock = new OrderBlock(driver);

        orderBlock.clickMiddleOrder(); //Жмём Заказать в центре страницы

        orderBlock.enterFirstName(firstName); // Заполняем Имя
        orderBlock.enterLastName(lastName); //Заполняем Фамилию
        orderBlock.enterAdress(adress); // Заполняем Адрес
        orderBlock.enterMetro(metro); // Выбираем станцию метро
        orderBlock.enterPhone(phone); // Заполняем номер телефона
        orderBlock.clickNextButton(); // Жмём Далее

        orderBlock.enterDatePicker(date); // Выбираем дату
        orderBlock.enterRentPeriod(rent); // Выбираем срок аренды
        if(color == "black") { // Выбираем цвет
            orderBlock.enterСolorBlack();
        }
        if (color == "grey") { //
            orderBlock.enterСolorGrey();
        }
        orderBlock.enterComment(comment); // Заполняем комментарий
        orderBlock.clickFinalOrder(); // Жмём Заказать
        orderBlock.clickYesButton(); // Жмём Да

        String expected = "Заказ оформлен";
        String actual = orderBlock.getOrderConfirmationText(); // Получаем текст о подтверждении заказа
        MatcherAssert.assertThat("Заказ не получилось оформить", actual, containsString(expected)); // Сравнение текста
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
