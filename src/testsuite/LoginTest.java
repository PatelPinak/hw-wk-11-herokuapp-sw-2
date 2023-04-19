package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //Find the Username Field Element
        WebElement usernameField = driver.findElement(By.name("username"));
        // Type the Username to username field element
        usernameField.sendKeys("tomsmith");
        //Find the Password Field Element and send password on password field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //Find the Login btn Element and click
        WebElement loginBtn = driver.findElement(By.xpath("//i[text()=' Login']"));
        loginBtn.click();

        String expectedMessage = "Secure Area";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[text()=' Secure Area']")); //xpath for locating dashboard
        String actualMessage = actualTextElement.getText();

        //Assert.assertEquals(expectedMessage, actualMessage);
        Assert.assertEquals("Not redirected to dashboard", expectedMessage, actualMessage);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //Find the Username Field Element
        WebElement usernameField = driver.findElement(By.name("username"));
        // Type the Username to username field element
        usernameField.sendKeys("tomsmith1");
        //Find the Password Field Element and send password on password field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //Find the Login btn Element and click
        WebElement loginBtn = driver.findElement(By.xpath("//i[text()=' Login']"));
        loginBtn.click();

        String expectedMessage = "Your username is invalid!\n" +
                "×";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessage = actualTextElement.getText();

        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //Find the Username Field Element
        WebElement usernameField = driver.findElement(By.name("username"));
        // Type the Username to username field element
        usernameField.sendKeys("tomsmith");
        //Find the Password Field Element and send password on password field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        //Find the Login btn Element and click
        WebElement loginBtn = driver.findElement(By.xpath("//i[text()=' Login']"));
        loginBtn.click();

        String expectedMessage = "Your password is invalid!\n" +
                "×";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessage = actualTextElement.getText();

        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
