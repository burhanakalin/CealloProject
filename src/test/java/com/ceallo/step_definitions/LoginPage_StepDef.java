package com.ceallo.step_definitions;

import com.ceallo.pages.DeletedFilesTab;
import com.ceallo.pages.LoginPage;
import com.ceallo.utilities.BrowserUtils;
import com.ceallo.utilities.ConfigurationReader;
import com.ceallo.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage_StepDef {

    LoginPage loginPage = new LoginPage();

    @Given("User is logged in and on Deleted Files tab")
    public void userIsLoggedInAndOnDeletedFilesTab() {

        DeletedFilesTab deletedFilesTab = new DeletedFilesTab();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        Driver.getDriver().get("https://qa.ceallo.com/index.php/login");

        loginPage.usernameInput.sendKeys(ConfigurationReader.getProperty("user"));
        loginPage.passwordInput.sendKeys(ConfigurationReader.getProperty("password"));

        loginPage.submitButton.click();

        BrowserUtils.implicitlyWait(10);

        js.executeScript("arguments[0].click()", deletedFilesTab.deletedFilesButton);




    }


}
