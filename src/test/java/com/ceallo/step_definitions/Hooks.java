package com.ceallo.step_definitions;

import com.ceallo.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("\tthis is coming from BEFORE");
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();

    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }

        Driver.closeDriver();

    }


    //import from io.cucumber.java not from junit
//   @Before(order = 0)
//   public void setupScenario(){
//       System.out.println("===Setting up browser using cucumber @Before");
//   }
//
//    @Before(value = "@login", order = 1)
//    public void setupScenarioForLogins(){
//        System.out.println("===this will only to scenarios with @login tag");
//    }
//
//    @Before(value = "@db", order = -1)
//    public void setupScenarioDatabaseScenarios(){
//        System.out.println("===this will only apply to scenarios with @dbb tag");
//    }

}
