package org.example;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonSearch {

    public static AmazonPage amazonPage;
    public static SearchResultPage searchResultPage;

    public static WebDriver driver;

    @BeforeClass
    public static void setup() {

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver = new ChromeDriver();
        amazonPage = new AmazonPage(driver);
        searchResultPage = new SearchResultPage (driver);
        driver.manage().window().maximize();
           driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("site"));
    }

    @Test
    public void searchBookTest() {
        amazonPage.clicksearchIn ();
        amazonPage.selectObject ();
        amazonPage.enterSearchWord (ConfProperties.getProperty("search"));
        amazonPage.clickSearchButton();
        List<Book> bookList = searchResultPage.start();
        String bookToCompareWith = "Head First Java, 2nd Edition";
        boolean isExist = false;
        for (int i = 0; i < bookList.size(); i++){
            if (bookList.get(i).title.equals(bookToCompareWith)) {
                isExist = true;
                break;
            }
        }
        Assert.assertTrue(isExist);
    }


    @AfterClass
    public static void tearDown() {
        driver.quit(); }
}