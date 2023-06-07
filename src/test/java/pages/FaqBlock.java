package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public class FaqBlock {
    private WebDriver driver;

    // Вопросы c 0 по 7
    private String questionLocator;
    private By question;

    private String answerLocator;
    private By answer;

    public FaqBlock(WebDriver driver){
        this.driver = driver;
    }


    //Метод клика по вопросам
    public void clickQuestion(int number) {
        //driver.findElement(acceptCookies).click();//Убираем куки

        questionLocator = "accordion__heading-" + number;//Формируем айди
        question = By.id(questionLocator);//Инициализируем локатор

        WebElement el = driver.findElement(question);//Доскролливаем
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", el);

        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(5))//Ждём
                .until(ExpectedConditions.elementToBeClickable(question));

        driver.findElement(question).click();//Кликаем на вопрос
    }

    //Метод, который достаёт текст из ответов
    public String getAnswer(int number) {
        answerLocator = "#accordion__panel-" + number + " > p";//Формируем селектор
        answer = By.cssSelector(answerLocator);//Инициализируем локатор

        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(5))//Ждём
                .until(ExpectedConditions.visibilityOfElementLocated(answer));

        return driver.findElement(answer).getText();//Возвращаем текст ответа
    }
}
