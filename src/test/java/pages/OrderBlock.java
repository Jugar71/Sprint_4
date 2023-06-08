package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class OrderBlock {
    private WebDriver driver;

    //Кнопка Заказать в хедере
    private By headerOrder = By.cssSelector("button.Button_Button__ra12g");
    //Кнопка Заказать на странице
    private By middleOrder = By.xpath("//div[5][@class=\"Home_FinishButton__1_cWm\"]/button[contains(text(), 'Заказать')]");
    //Имя
    private By firstName = By.xpath("//input[@placeholder='* Имя']");
    //Фамилия
    private By lastName = By.xpath("//input[@placeholder='* Фамилия']");
    //Адрес
    private By adress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Метро
    private By metro = By.xpath("//input[@placeholder='* Станция метро']");
    //Телефон
    private By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Далее
    private By nextButton = By.xpath("//button[contains(text(), 'Далее')]");
    //Датапикер
    private By datePicker = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Список по срокам аренды
    private By rentPeriod = By.xpath("//div[1][@class=\"Dropdown-control\"]");
    //Цвет чёрный
    private By colorBlack = By.xpath("//input[@id=\"black\"]");
    //Цвет серый
    private By colorGrey = By.xpath("//input[@id=\"grey\"]");
    //Комментарий
    private By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //Кнопка Заказать в конце
    private By finalOrder = By.xpath("//button[2][contains(text(), 'Заказать')]");
    //Кнопка Да
    private By yesButton = By.xpath("//button[2][contains(text(), 'Да')]");
    //Заказ оформлен
    private By orderConfirmation = By.xpath("//div[1][contains(text(), 'Заказ оформлен')]");

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
        driver.findElement(By.xpath("//ul[@class=\"select-search__options\"]/li[" + station + "]")).click();
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
        driver.findElement(By.xpath("//div[2][@class=\"Order_Content__bmtHS\"]")).click();//Кликнуть рядом, чтобы датапикер закрылся
    }

    public void enterRentPeriod(int period) {
        driver.findElement(rentPeriod).click();
        //Выбрать конкретный срок
        driver.findElement(By.xpath("//div[2][@class=\"Dropdown-menu\"]/div[" + period + "]")).click();
    }

    public void enterСolor(String color) {
        if(color.equals("black")) { // Выбираем цвет
            driver.findElement(colorBlack).click();
        }
        if (color.equals("grey")) { //
            driver.findElement(colorGrey).click();
        }
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