
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class MainTest {

//а где использование аннотаций для тестирования?)
    public static void main (String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","E:\\JavaProject\\Tools\\chromedriver.exe");// Этот путь будет работать только локально на компе. 
        WebDriver driver = new ChromeDriver();                                                  // Лучше храни драйвер в проекте и пользуйся относительным путем, а не абсолютным
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));// должно лежать в отдельном классе, а вызываться отдельным методом.


        driver.get("https://demoqa.com/");
        Thread.sleep(3000);//ай-йа-яй! Единственно, о чем стоит помнить при использовании этого метода - НИКОГДА не использовать этот метод!!)))
        WebElement element = driver.findElement(By.xpath("(//div[@class='card mt-4 top-card'])[1]"));// не самый стабильный локатор. Классы часто меняются, да и структура вложенности [1] тоже. Может перестать работать со временем
        element.click();

        //Открываем Text Box - комментарий тут лишний. Информации полезной не несет.
        element = driver.findElement(By.xpath("//span[contains(text(),'Text Box')]")); // называй элементы как то более явно. Чтобы было понятно с чем работаешь. "Элемент" и "ПарентЭлемент" не несут вообще никакой информации =)
        WebElement parentElement = element.findElement(By.xpath("//span[contains(text(),'Text Box')]/parent::li")); //оси - это хорошо =) 
        String classOfElement = parentElement.getAttribute("class");
        Thread.sleep(3000);//!!! аналогично верхнему комментарию)
        if (!classOfElement.equals("btn btn-light active")) element.click(); //старайся всегда использовать { }. Да, без них работает, ровно до тех пор, пока ты по случайности не добавишь еще строку) А это забывается довольно быстро

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
        //Второй вариант заполнения через создание метода - смотрится акккуратнее) Но зачем постоянно передавать driver? Он ж всегда один. 
        findTxtElementAndFill(driver, "//input[@id='userName']", "Иванов Иван Иванович"); // и локаторы, типа "//input[@id='userName']" повторяются. Вынеси их вверх в виде констант.
        findTxtElementAndFill(driver, "//input[@id='userEmail']", "ivanov@gmail.com");
        findTxtElementAndFill(driver, "//textarea[@id='currentAddress']", "453118 г.Стерлитамак, ул.Курчатова д.6 кв.56");
        findTxtElementAndFill(driver, "//textarea[@id='permanentAddress']", "453118 г.Стерлитамак, ул.Сакко и Ванцетти д.3 кв.10");

        Thread.sleep(5000);
        driver.quit();//это тоже в отдельный метод оборачивается
    }

    public static void findTxtElementAndFill (WebDriver driver, String elementXpath, String textFill) {
        WebElement element = driver.findElement(By.xpath(elementXpath));
        element.clear();
        element.sendKeys(textFill);
    }
}
