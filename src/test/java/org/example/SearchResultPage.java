package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class SearchResultPage {
    public WebDriver driver;
    public SearchResultPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<Book> start()
    {
        List<Book> bookList = new ArrayList<>();
        List<WebElement> allBooks = driver.findElements(By.cssSelector(".s-card-container.s-overflow-hidden.aok-relative.puis-include-content-margin.s-latency-cf-section.s-card-border"));
        for (int i=0; i<allBooks.size(); i++){
            List<WebElement> bookTitles = allBooks.get(i).findElements(By.cssSelector(".a-size-medium.a-color-base.a-text-normal"));
            List<WebElement> authorName = allBooks.get(i).findElements(By.className ("a-size-base"));
            List<WebElement> bookPrice = allBooks.get(i).findElements(By.className("a-price"));
            List<WebElement> isBest = allBooks.get(i).findElements(By.className("a-badge-text"));

            Book book = new Book();
            book.title = bookTitles.get(0).getText();
            for (int j=0; j<authorName.size(); j++){
                if (authorName.get(j).getText().equals("by")) {
                    book.author = authorName.get(j+1).getText();
                    break;
                }
            }

            if (bookPrice.size() == 0){
                book.price = "0";
            }
            else {
                //String bookPriceForChange;
                //bookPriceForChange = bookPrice.get(0).getText();
                book.price = bookPrice.get(0).getText().replace("\n", ".");
            }

            if (isBest.size() == 0){
               book.isBestSeller = "Not Best Seller";
            }
            else {
                book.isBestSeller = isBest.get(0).getText();
            }
            bookList.add(book);
        }
        return bookList;
    }
}
