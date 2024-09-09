package AcademyBugs.Test;
import AcademyBugs.Pages.academyBugsLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class academyBugsLoginTest {

    WebDriver driver;
    academyBugsLoginPage login;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver","resource/chromedriver.exe");
        driver.get("https://academybugs.com/account/?ec_page=register");
        driver.manage().window().maximize();
    }

    @Test
    public void login() throws IOException {
        login = new academyBugsLoginPage(driver);
        login.enterEmail();
        login.enterPassword();
        login.clickSignIn();
        Assert.assertTrue(login.getSuccess().isDisplayed(),"The element is not displayed");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}


