package com.ceallo.pages;

import com.ceallo.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DeletedFilesTab {

    public DeletedFilesTab() {
        PageFactory.initElements(Driver.getDriver(), this);
    };

    @FindBy(xpath = "(//div[@id='app-content-trashbin']//table//tbody//tr)[1]//td[@class='filename']//a//span[@class='fileactions']//a[@class='action action-menu permanent']")
    public WebElement firstRowThreeDots;

    @FindBy(xpath = "(//div[@id='app-content-trashbin']//table//tbody//tr)[1]//td[@class='filename']//a//span[@class='fileactions']//a[@class='action action-restore permanent']")
    public WebElement restoreButton;

    @FindBy(xpath = "(//span[normalize-space()='Delete permanently'])[2]")
    public WebElement deletePermanentlyButton;

    @FindBy(xpath = "(//div[@id='app-content-trashbin']//table//tbody//tr)[1]//td[@class='filename']//a//span[@class='nametext extra-data']//span[@class='innernametext']")
    public WebElement firstRowFileName;

    @FindBy(xpath = "((//div[@id='app-content-trashbin']//table//thead//tr//th)[3]//a//span)[2]")
    public WebElement sortIconDate;

    @FindBy(xpath = "//*[@id='app-navigation']/ul/li[6]/a")
    public WebElement deletedFilesButton;

    @FindBy(xpath = "(//a[@data-sort='name'])[11]")
    public WebElement nameSortButton;

    @FindBy(xpath = "(//div[@id='app-content-trashbin']//tbody//tr)")
    public List<WebElement> trashBinElements;


    
    

    
    
    





  

    

    
    
    
    

    





   













    
}
