
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public class MainTest {
    public static void main (String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","E:\\JavaProject\\Tools\\chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        browser.get("https://demoqa.com/");
        Thread.sleep(3000);
        WebElement elementCard = browser.findElement(By.xpath("(//div[@class='card mt-4 top-card'])[1]"));
        elementCard.click();

        WebElement textBox = browser.findElement(By.xpath("//span[contains(text(),'Text Box')]"));
        WebElement parentTextBox = textBox.findElement(By.xpath("//span[contains(text(),'Text Box')]/parent::li"));
        String classOfElement = parentTextBox.getAttribute("class").toLowerCase();
        Thread.sleep(3000);
        if (!classOfElement.contains("active")) textBox.click();

        findTxtBoxAndFill(browser, "//input[@id='userName']", "Иванов Иван Иванович");
        findTxtBoxAndFill(browser, "//input[@id='userEmail']", "ivanov@gmail.com");
        findTxtBoxAndFill(browser, "//textarea[@id='currentAddress']", "453118 г.Стерлитамак, ул.Курчатова д.6 кв.56");
        findTxtBoxAndFill(browser, "//textarea[@id='permanentAddress']", "453118 г.Стерлитамак, ул.Сакко и Ванцетти д.3 кв.10");
        WebElement submit = browser.findElement(By.id("submit"));
        submit.click();
        Thread.sleep(5000);
        browser.quit();
    }

    public static void findTxtBoxAndFill(WebDriver browser, String elementXpath, String textFill) {
        WebElement textBox = browser.findElement(By.xpath(elementXpath));
        textBox.clear();
        textBox.sendKeys(textFill);
    }
}
