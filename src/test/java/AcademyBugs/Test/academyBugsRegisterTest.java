package AcademyBugs.Test;

import AcademyBugs.Pages.academyBugsRegisterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class academyBugsRegisterTest {

    WebDriver driver;
    academyBugsRegisterPage register;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "resource/chromedriver.exe");
        driver.get("https://academybugs.com/account/?ec_page=register");
        driver.manage().window().maximize();
    }

    @Test
    public void register() {
        register = new academyBugsRegisterPage(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("register"), "The site is not found");
        register.enterFirtsName();
        register.enterLastName();
        register.enterEmail();
        register.retypeEmail();
        register.enterPassword();
        register.enterPasswordConfirm();
        Assert.assertFalse(register.getNewsletter(), "The newsletter checkbox is selected");
        Assert.assertEquals(register.getRequiredFields().size(), register.count().size(), "Required fields are not identified");
        register.clickRegisterButton();
        register.getSuccess();
        Assert.assertTrue(register.getSuccess().isDisplayed(), "The element is not present");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}


