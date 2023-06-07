package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class OrderBlock {
    private WebDriver driver;


    //Кнопка Заказать в хедере
    private By headerOrder = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]");
    //Кнопка Заказать на странице
    private By middleOrder = By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button");
    //Имя
    private By firstName = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input");
    //Фамилия
    private By lastName = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/input");
    //Адрес
    private By adress = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/input");
    //Метро
    private By metro = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div/input");
    //Телефон
    private By phone = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/input");
    //Далее
    private By nextButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button");
    //Датапикер
    private By datePicker = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input");
    //Список по срокам аренды
    private By rentPeriod = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div/div[1]");
    //Цвет чёрный
    private By colorBlack = By.xpath("//*[@id=\"black\"]");
    //Цвет серый
    private By colorGrey = By.xpath("//*[@id=\"grey\"]");
    //Комментарий
    private By comment = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/input");
    //Кнопка Заказать в конце
    private By finalOrder = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[2]");
    //Кнопка Да
    private By yesButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[2]");
    //Заказ оформлен
    private By orderConfirmation = By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[1]");

    public OrderBlock(WebDriver driver){
        this.driver = driver;
    }

    public void clickHeaderOrder() {
        driver.findElement(headerOrder).click();
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(5))//Ждём
                .until(ExpectedConditions.elementToBeClickable(headerOrder));
    }

    public void clickMiddleOrder() {
        WebElement el = driver.findElement(middleOrder);//Доскролливаем
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", el);

        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(5))//Ждём
                .until(ExpectedConditions.elementToBeClickable(middleOrder));

        driver.findElement(middleOrder).click();
    }

    public void enterFirstName(String fName) {
        driver.findElement(firstName).sendKeys(fName);
    }

    public void enterLastName(String lName) {
        driver.findElement(lastName).sendKeys(lName);
    }

    public void enterAdress(String adr) {
        driver.findElement(adress).sendKeys(adr);
    }

    public void enterMetro(int station) {
        driver.findElement(metro).click();
        //Ввести конкретную станцию
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div[2]/ul/li[" + station + "]")).click();
    }

    public void enterPhone(String ph) {
        driver.findElement(phone).sendKeys(ph);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void enterDatePicker(String date) {
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(datePicker));
        driver.findElement(datePicker).sendKeys(date);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]")).click();//Кликнуть рядом, чтобы датапикер закрылся
    }

    public void enterRentPeriod(int period) {
        driver.findElement(rentPeriod).click();
        //Выбрать конкретный срок
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[" + period + "]")).click();
    }

    public void enterСolorBlack() {
        driver.findElement(colorBlack).click();
    }

    public void enterСolorGrey() {
        driver.findElement(colorGrey).click();
    }

    public void enterComment(String comm) {
        driver.findElement(comment).sendKeys(comm);
    }

    public void clickFinalOrder() {
        driver.findElement(finalOrder).click();
    }

    public void clickYesButton() {
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(yesButton));
        driver.findElement(yesButton).click();
    }

    public String getOrderConfirmationText() {
        return driver.findElement(orderConfirmation).getText();
    }
}