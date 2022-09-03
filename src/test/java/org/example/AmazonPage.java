package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AmazonPage {

    public WebDriver driver;
    public AmazonPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"searchDropdownBox\"]")
    private WebElement searchIn;

    WebElement selectElement;
    Select selectObject;

    @FindBy(xpath = "//*[@id=\"twotabsearchtextbox\"]")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id=\"nav-search-submit-button\"]")
    private WebElement searchButton;

    public void clicksearchIn() {
        searchIn.click();
    }

    public void selectObject() {
        selectElement = driver.findElement(By.id("searchDropdownBox"));
        selectObject = new Select(selectElement);
        selectObject.selectByVisibleText ("Books");
    }
    public void enterSearchWord (String search) {
        searchField.sendKeys(search);
    }

    public void clickSearchButton (){
        searchButton.click();
    }

}
