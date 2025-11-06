import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TestCases extends Data {


    WebDriver driver = new EdgeDriver();

    @BeforeTest
    public void MySetup() throws SQLException {

        driver.get(Url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","Password");


    }

    @Test (priority = 1,enabled = true)
    public void CreateData() throws SQLException {

        String Query = "Insert into customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) Values (999, 'Abc Company', 'ali', 'ahmad', '962797700235', '123 Main St', 'Los Angeles', 'United States', 1370, 50000.00) ";
        stmt = con.createStatement();
        int rawInserted = stmt.executeUpdate(Query);

    }

    @Test (priority = 2,enabled = true)
    public void ReadData() throws SQLException {

        String Query = "Select * from customers where customerNumber = 999";
        stmt = con.createStatement();
        rs = stmt.executeQuery(Query);

        while (rs.next()) {

            TheFirstName = rs.getString("contactFirstName").toString().trim();

            TheLastName = rs.getString("contactLastName").toString().trim();

            TheTelephone = rs.getString("phone").toString().trim();

            TheEmail = TheFirstName + TheLastName + RandomNumber + Domain ;

            TheAddress = rs.getString("addressLine1").toString().trim();

            TheCountry = rs.getString("country").toString().trim();

            Name = TheFirstName + TheLastName + RandomNumber;



        }
    }

    @Test (priority = 3,enabled = true)
    public void UpdateData() throws SQLException {

        String Query = "Update customers set contactLastName = 'Jamal' where customerNumber = 999";
        stmt = con.createStatement();
        int rawUpdated = stmt.executeUpdate(Query);

    }

    @Test (priority = 4,enabled = true)
    public void DeleteData() throws SQLException {

        String Query = "Delete from customers where customerNumber = 999";
        stmt = con.createStatement();
        int rawDeleted = stmt.executeUpdate(Query);

    }

    @Test (priority = 5,enabled = true)
    public void SignUpTest() throws InterruptedException {

        WebElement LoginOrRegister = driver.findElement(By.linkText("Login or register"));
        LoginOrRegister.click();

        WebElement ContinueButton = driver.findElement(By.cssSelector("button[title='Continue']"));
        ContinueButton.click();

        //Element

        WebElement FirstName = driver.findElement(By.id("AccountFrm_firstname"));
        WebElement LastName = driver.findElement(By.id("AccountFrm_lastname"));
        WebElement Email = driver.findElement(By.id("AccountFrm_email"));
        WebElement Telephone = driver.findElement(By.id("AccountFrm_telephone"));
        WebElement Fax = driver.findElement(By.id("AccountFrm_fax"));

        //Actions

        FirstName.sendKeys(TheFirstName);
        LastName.sendKeys(TheLastName);
        Email.sendKeys(TheEmail);
        Telephone.sendKeys(TheTelephone);
        Fax.sendKeys(TheFax);

        //Element

        WebElement Address = driver.findElement(By.id("AccountFrm_address_1"));
        WebElement City = driver.findElement(By.id("AccountFrm_city"));
        WebElement Region = driver.findElement(By.id("AccountFrm_zone_id"));
        WebElement Country = driver.findElement(By.cssSelector("#AccountFrm_country_id"));
        WebElement PostalCode = driver.findElement(By.id("AccountFrm_postcode"));

        Select TheSelectedCountry = new Select(Country);
        Select TheSelectedRegion = new Select(Region);

        TheSelectedCountry.selectByVisibleText(TheCountry);

        Thread.sleep(1500);

        TheSelectedRegion.selectByIndex(1);

        List<WebElement> AllTheRegion = Region.findElements(By.tagName("option"));
        TheCity = AllTheRegion.get(1).getText();

        //Actions

        Address.sendKeys(TheAddress);
        City.sendKeys(TheCity);
        PostalCode.sendKeys(ThePostalCode);

        //LoginDetails

        WebElement LoginName = driver.findElement(By.cssSelector("#AccountFrm_loginname"));
        WebElement Password = driver.findElement(By.id("AccountFrm_password"));
        WebElement ConfirmPassword = driver.findElement(By.id("AccountFrm_confirm"));

        //Actions

        LoginName.sendKeys(Name);
        Password.sendKeys(ThePassword);
        ConfirmPassword.sendKeys(ThePassword);

        WebElement Policy = driver.findElement(By.id("AccountFrm_agree"));
        Policy.click();

        WebElement Continue = driver.findElement(By.cssSelector("button[title='Continue']"));
        Continue.click();

        boolean ActualMessage = driver.getPageSource().contains(ExpectedMessage);

        Assert.assertEquals(ActualMessage, true);

    }

   }