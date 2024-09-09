package AcademyBugs.Pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class academyBugsRegisterPage {
    WebDriver driver;
    WebDriverWait wait;
    Faker faker;
    String email;
    String pass;
    private String csvFilePath = "src/Data/usuarios.csv";
    By firstNameInput = By.id("ec_account_register_first_name");
    By lastNameInput = By.id("ec_account_register_last_name");
    By emailInput = By.id("ec_account_register_email");
    By retypeEmailInput = By.id("ec_account_register_retype_email");
    By passwordInput = By.id("ec_account_register_password");
    By retypePasswordInput = By.id("ec_account_register_password_retype");
    By registerButton = By.xpath("//input[@value='REGISTER']");
    By header = By.xpath("//div[normalize-space()='Your Primary Email']");
    By checkbox = By.xpath("//input[@id='ec_account_register_is_subscriber']");
    List<By> requiredFields = Arrays.asList(
            By.xpath("//label[@for='ec_account_register_first_name']"),
            By.xpath("//label[@for='ec_account_register_last_name']"),
            By.xpath("//label[@for='ec_account_register_email']"),
            By.xpath("//label[@for='ec_account_register_email_retype']"),
            By.xpath("//label[@for='ec_account_register_password']"),
            By.xpath("//label[@for='ec_account_register_password_retype']"));

    public academyBugsRegisterPage(WebDriver driver) {
        this.driver = driver;
        this.faker = new Faker();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.pass = faker.internet().password();
        this.email = faker.internet().emailAddress();
    }

    private void saveUserDataToCSV(String email, String password) {
        try (FileWriter writer = new FileWriter(csvFilePath, true)) {
            writer.append(email).append(",").append(password).append("\n");
            System.out.println("User data saved to CSV successfully.");
        } catch (IOException e) {
            System.out.println("Error saving user data to CSV: " + e.getMessage());
        }
    }

    public void enterFirtsName() {
        wait.until(ExpectedConditions.presenceOfElementLocated(firstNameInput));
        String firstName = faker.name().firstName();
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName() {
        String lastName = faker.name().lastName();
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterEmail() {
        driver.findElement(emailInput).sendKeys(email);
        saveUserDataToCSV(email, pass);
    }

    public void retypeEmail() {
        driver.findElement(retypeEmailInput).sendKeys(email);
    }

    public void enterPassword() {
        driver.findElement(passwordInput).sendKeys(pass);
    }

    public void enterPasswordConfirm() {
        driver.findElement(retypePasswordInput).sendKeys(pass);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public WebElement getSuccess() {

        wait.until(ExpectedConditions.presenceOfElementLocated(header));
        return driver.findElement(header);
    }

    public List<By> getRequiredFields() {

        return requiredFields;
    }

    public boolean getNewsletter() {

        WebElement castnewsletter = driver.findElement(checkbox);

        boolean isselected = castnewsletter.isSelected();

        return isselected;
    }

    public List<String> count() {
        List<String> count = new ArrayList<String>();
        for (By field : requiredFields) {
            String fieldText = driver.findElement(field).getText();
            if (fieldText.contains("*")) {
                count.add("*");
            }
        }
        return count;
    }
}
