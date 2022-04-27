
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class MainTest {


    public static void main (String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","E:\\JavaProject\\Tools\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demoqa.com/");
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("(//div[@class='card mt-4 top-card'])[1]"));
        element.click();

        //Открываем Text Box
        element = driver.findElement(By.xpath("//span[contains(text(),'Text Box')]"));
        WebElement parentElement = element.findElement(By.xpath("//span[contains(text(),'Text Box')]/parent::li"));
        String classOfElement = parentElement.getAttribute("class");
        Thread.sleep(3000);
        if (!classOfElement.equals("btn btn-light active")) element.click();

        /*
        //Первый вариант заполнения
        element = driver.findElement(By.xpath("//input[@id='userName']"));
        element.clear();
        element.sendKeys("Иванов Иван Иванович");

        element = driver.findElement(By.xpath("//input[@id='userEmail']"));
        element.clear();
        element.sendKeys("ivanov@gmail.com");

        element = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        element.clear();
        element.sendKeys("453118 г.Стерлитамак, ул.Курчатова д.6 кв.56");

        element = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        element.clear();
        element.sendKeys("453118 г.Стерлитамак, ул.Сакко и Ванцетти д.3 кв.10");
*/
        //Второй вариант заполнения через создание метода
        findTxtElementAndFill(driver, "//input[@id='userName']", "Иванов Иван Иванович");
        findTxtElementAndFill(driver, "//input[@id='userEmail']", "ivanov@gmail.com");
        findTxtElementAndFill(driver, "//textarea[@id='currentAddress']", "453118 г.Стерлитамак, ул.Курчатова д.6 кв.56");
        findTxtElementAndFill(driver, "//textarea[@id='permanentAddress']", "453118 г.Стерлитамак, ул.Сакко и Ванцетти д.3 кв.10");

        Thread.sleep(5000);
        driver.quit();
    }

    public static void findTxtElementAndFill (WebDriver driver, String elementXpath, String textFill) {
        WebElement element = driver.findElement(By.xpath(elementXpath));
        element.clear();
        element.sendKeys(textFill);
    }
}
