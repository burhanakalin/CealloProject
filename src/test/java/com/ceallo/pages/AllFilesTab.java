package com.ceallo.pages;

import com.ceallo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AllFilesTab {

    public AllFilesTab(){PageFactory.initElements(Driver.getDriver(), this);}

    @FindBy(xpath = "(//div[@id='app-content-files']//table//tbody//tr)[1]//td[2]//a//span[@class='nametext']//span[1]")
    public WebElement allFilesFirstRowFile;

    @FindBy(xpath = "//*[@id='app-navigation']/ul/li[1]//a")
    public WebElement deletedFilesTab;

    @FindBy(xpath = "(//div[@id='app-content-files']//tbody//tr)")
    public List<WebElement> allFilesElements;



}
