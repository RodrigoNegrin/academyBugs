package AcademyBugs.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class academyBugsLoginPage {
    WebDriver driver;
    WebDriverWait wait;
    String csvFilePath = "src/Data/usuarios.csv";
    By emailInput = By.id("ec_account_login_email");
    By passwordInput = By.id("ec_account_login_password");
    By signinButton = By.xpath("//input[@value='SIGN IN']");
    By signOutButton = By.linkText("sign out");
    public academyBugsLoginPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
}

    public void enterEmail() throws IOException {

        wait.until(ExpectedConditions.presenceOfElementLocated(emailInput));

        String lastLine = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
        }
        if (lastLine != null) {
            String[] userDetails = lastLine.split(",");
            String email = userDetails[0];
            driver.findElement(emailInput).sendKeys(email);
        }
    }

    public void enterPassword() throws IOException {

        String lastLine = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
        }
        if (lastLine != null) {
            String[] userDetails = lastLine.split(",");
            String password = userDetails[1];
            driver.findElement(passwordInput).sendKeys(password);
        }
    }

    public void clickSignIn(){
        driver.findElement(signinButton).click();
    }

    public WebElement getSuccess(){
        wait.until(ExpectedConditions.presenceOfElementLocated(signOutButton));

        return driver.findElement(signOutButton);

    }

}
