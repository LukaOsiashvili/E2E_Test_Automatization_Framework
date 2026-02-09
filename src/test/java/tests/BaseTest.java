package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

public class BaseTest {

    @BeforeMethod
    public void setup(){
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();
    }
}
