package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class ComparePage {
    public WebDriver driver;


    public ComparePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Book getCompareBook(String url) {

        driver.get(url);

        Book bookToCompare = new Book();

        WebElement bookTitle = driver.findElement(By.id("productTitle"));
        bookToCompare.title = bookTitle.getText();

        //List<WebElement> authorName = driver.findElements(By.className("a-link-normal"));
        List<WebElement> authorName = driver.findElements(By.cssSelector(".a-link-normal.contributorNameID"));
        bookToCompare.author = authorName.get(0).getText();

        WebElement bookPrice = null;
        try {
            bookPrice = driver.findElement(By.id("rentPrice"));
            }
            catch (Exception e){}

        if (bookPrice == null){
            WebElement bookUsedPrice = driver.findElement(By.id("newBuyBoxPrice"));
            bookToCompare.price = bookUsedPrice.getText();
        }
        else {
            bookToCompare.price = bookPrice.getText();
        }

        WebElement isBest = null;
        try {
            isBest = driver.findElement(By.className("a-badge-text"));
            }
            catch (Exception e){}

        if (isBest == null){
            bookToCompare.isBestSeller = "Not Best Seller";
        }
        else {
            bookToCompare.isBestSeller = isBest.getText();
        }

        return bookToCompare;
    }
}
