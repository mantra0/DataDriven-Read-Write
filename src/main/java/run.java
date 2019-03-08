import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class run {

    WebDriver driver;
    int rowNum = 0;
    int colNum = 0;

    @BeforeTest
    void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\JARS\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @Test
    void run(){
        String data = "No results found for";
        try{
            for(int i=0;i<dataProvider.getRowCount();i++) {
                if (!dataProvider.getCellData(rowNum, colNum).equals("")) {
                    String firstData = dataProvider.getCellData(rowNum, colNum);
                    String secondData = dataProvider.getCellData(rowNum, colNum + 1);
                    driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("\"" + firstData + "\" + " + "\"" + secondData + "\"");
                    driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.ENTER);
                    Thread.sleep(1000L);
                    String searchResult = driver.findElement(By.cssSelector(".med #topstuff")).getText();
                    if (searchResult.contains(data)) {
                        System.out.println(searchResult);
                        dataProvider.setCellData(data, rowNum, colNum + 2);
                    } else {
                        System.out.println(searchResult);
                    }
                    driver.findElement(By.xpath("//input[@title='Search']")).clear();
                } else {
                    break;
                }
                rowNum++;
            }
        }
        catch (Exception e){}
    }
}
