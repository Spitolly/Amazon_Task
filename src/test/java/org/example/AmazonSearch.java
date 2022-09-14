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

    public static ComparePage comparePage;

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
        comparePage = new ComparePage(driver);
    }

    @Test
    public void searchBookTest() {
        amazonPage.clicksearchIn();
        amazonPage.selectObject();
        amazonPage.enterSearchWord (ConfProperties.getProperty("search"));
        amazonPage.clickSearchButton();
        List<Book> bookList = searchResultPage.start();
        Book bookToCompare = comparePage.getCompareBook(ConfProperties.getProperty("compareBookPage"));
        boolean compareResult = false;
        for (int i = 0; i < bookList.size(); i++){
            if (bookList.get(i).title.equals(bookToCompare.title)
                && bookList.get(i).author.equals(bookToCompare.author)
                && bookList.get(i).price.equals(bookToCompare.price)
                && bookList.get(i).isBestSeller.equals(bookToCompare.isBestSeller)){
                    compareResult = true;
                    break;
            }
        }
        Assert.assertTrue(compareResult);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit(); }
}