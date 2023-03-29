package com.ceallo.step_definitions;

import com.ceallo.pages.AllFilesTab;
import com.ceallo.pages.DeletedFilesTab;
import com.ceallo.utilities.BrowserUtils;
import com.ceallo.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DeletedFilesTab_StepDef {

    DeletedFilesTab deletedFilesTab = new DeletedFilesTab();
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
    AllFilesTab allFilesTab = new AllFilesTab();

        //User can see the most recent deleted file in the first line of the deleted file list when deleted files are ordered by newest to oldest
    @When("deleted files are ordered by newest to oldest")
    public void deletedFilesAreOrderedByNewestToOldest() {

        //scroll to page bottom and click to sort button
        BrowserUtils.scrollToBottom();
        BrowserUtils.implicitlyWait(10);
        BrowserUtils.TwoClickWithJS(deletedFilesTab.sortIconDate);

    }


        //User can see the most recent deleted file in the first line of the deleted file list when deleted files are ordered by newest to oldest
    @Then("user should see recent deleted file")
    public void userShouldSeeRecentDeletedFile() {


        BrowserUtils.implicitlyWait(10);
        System.out.println("TrashBinElements.size() = " + deletedFilesTab.trashBinElements.size());

        //get all the "data-mtime" attributes to compare "Delete time"
        for (int i = 1; i < deletedFilesTab.trashBinElements.size()-1; i++) {

        //convert string values to Long
            long intDate1 = Long.parseLong(deletedFilesTab.trashBinElements.get(i).getAttribute("data-mtime"));
            long intDate2 = Long.parseLong(deletedFilesTab.trashBinElements.get(i+1).getAttribute("data-mtime"));

        //verify if the elements ordered correctly (recent deleted file has bigger "data-mtime" value
            Assert.assertTrue(intDate1 >= intDate2);
        }

    }


        //User can order the all deleted files by newest to oldest or visa versa
    @When("user orders the deleted files by newest to oldest")
    public void user_orders_the_deleted_files_by_newest_to_oldest() {

        //scroll to page bottom and order element newest to oldest
        BrowserUtils.scrollToBottom();
        BrowserUtils.implicitlyWait(10);
        BrowserUtils.TwoClickWithJS(deletedFilesTab.sortIconDate);

    }

    @Then("the most recent ones will be listed on the top of the list")
    public void the_most_recent_ones_will_be_listed_on_the_top_of_the_list() {

        BrowserUtils.implicitlyWait(10);

        //get all the "data-mtime" attributes to compare "Delete time"
        for (int i = 1; i < deletedFilesTab.trashBinElements.size()-1; i++) {

        //convert string values to Long
            long intDate1 = Long.parseLong(deletedFilesTab.trashBinElements.get(i).getAttribute("data-mtime"));
            long intDate2 = Long.parseLong(deletedFilesTab.trashBinElements.get(i+1).getAttribute("data-mtime"));

        //verify if the elements ordered correctly (recent deleted file has bigger "data-mtime" value
            Assert.assertTrue(intDate1 >= intDate2);
        }
    }

    @When("user orders the deleted files by oldest to newest")
    public void user_orders_the_deleted_files_by_oldest_to_newest() {

        BrowserUtils.clickWithJS(deletedFilesTab.sortIconDate);
        BrowserUtils.implicitlyWait(10);

    }

    @Then("the oldest deleted ones will be listed on the top of the list")
    public void the_oldest_deleted_ones_will_be_listed_on_the_top_of_the_list() {


        for (int i = 1; i < deletedFilesTab.trashBinElements.size()-1; i++) {

            long intDate1 = Long.parseLong(deletedFilesTab.trashBinElements.get(i).getAttribute("data-mtime"));
            long intDate2 = Long.parseLong(deletedFilesTab.trashBinElements.get(i+1).getAttribute("data-mtime"));

            Assert.assertTrue(intDate1 <= intDate2);
        }

    }


        //User can order alphabetically all the deleted files based on their names
    @When("user clicks on alphabetically sort button")
    public void user_clicks_on_alphabetically_sort_button() {

        //scroll to bottom and click on name sort bottom to sort elements by name
        BrowserUtils.scrollToBottom();
        BrowserUtils.implicitlyWait(10);

        js.executeScript("arguments[0].click()", deletedFilesTab.nameSortButton);

    }

    @Then("deleted files are ordered alphabetically by their names")
    public void deleted_files_are_ordered_alphabetically_by_their_names() {

            for (int i1 = 0; i1 < deletedFilesTab.trashBinElements.size()-1; i1++) {

                //get each row's "data-type" and "data-file" attributes
                String dataName1 = deletedFilesTab.trashBinElements.get(i1).getAttribute("data-type");
                String fileName1 = deletedFilesTab.trashBinElements.get(i1).getAttribute("data-file");

                //get the following row's "data-type" and "data-file" attributes
                String dataName2 = deletedFilesTab.trashBinElements.get((i1+1)).getAttribute("data-type");
                String fileName2 = deletedFilesTab.trashBinElements.get((i1+1)).getAttribute("data-file");

                //compare each and following names by using compareToIgnoreCase method
                /**
                 * An int value: 0 if the string is equal to the other string, ignoring case differences.
                 * < 0 if the string is lexicographically less than the other string
                 * > 0 if the string is lexicographically greater than the other string (more characters)
                 */
                boolean verifyAlp = fileName1.compareToIgnoreCase(fileName2) <= 0;

                //verify if each two compared elements has the same "data-type"
                boolean dirBol = (dataName1.equals("dir")) && (dataName2.equals("dir"));
                boolean fileBol = (dataName1.equals("file")) && (dataName2.equals("file"));

                //under the previous condition, verify if the elements are correctly ordered or not
                if(dirBol){
                    Assert.assertTrue(verifyAlp);
                    //System.out.println("DirTrue");
                }else if (fileBol){
                    Assert.assertTrue(verifyAlp);
                    //System.out.println("FileTrue");
                }

            }

        }


        //User can delete any deleted file permanently by using the three dots icon in the file’s line
    @When("user three dots icon and sees the Delete permanently button")
    public void user_three_dots_icon_and_sees_the_delete_permanently_button() {

        //order all deleted elements newest to oldest
        deletedFilesAreOrderedByNewestToOldest();

        //click on three dots on first row
        BrowserUtils.implicitlyWait(10);
        BrowserUtils.clickWithJS(deletedFilesTab.firstRowThreeDots);

        //verify if the "Delete Permanently" button is displayed
        Assert.assertTrue(deletedFilesTab.deletePermanentlyButton.isDisplayed());

    }

    @Then("user is able to delete file permanently")
    public void user_is_able_to_delete_file_permanently() {

        //order all deleted elements newest to oldest
        deletedFilesAreOrderedByNewestToOldest();

        //get the first row element's name
        String firstRowNameBeforeDeletion = deletedFilesTab.firstRowFileName.getText();

        //delete first rowed element permanently
        BrowserUtils.implicitlyWait(10);
        BrowserUtils.clickWithJS(deletedFilesTab.firstRowThreeDots);
        BrowserUtils.clickWithJS(deletedFilesTab.deletePermanentlyButton);

        //after deletion, again get the first rowed element's name
        BrowserUtils.sleep(5);
        String firstRowNameAfterDeletion = deletedFilesTab.firstRowFileName.getText();

        //compare first rowed element's name before and after deletion to verify if the element disappeared or not
        Assert.assertNotEquals(firstRowNameBeforeDeletion, firstRowNameAfterDeletion);

    }


        //User can restore any deleted file and see it again under the all Files tab
    @When("user clicks on restore button and file disappears from deleted files list")
    public void user_clicks_on_restore_button_and_file_disappears_from_deleted_files_list() {

        //order all deleted elements newest to oldest
        deletedFilesAreOrderedByNewestToOldest();

        //get the first row element's name
        String firstRowNameBeforeRestore1 = deletedFilesTab.firstRowFileName.getText();

        //restore first rowed element
        BrowserUtils.implicitlyWait(10);
        BrowserUtils.clickWithJS(deletedFilesTab.restoreButton);

        //after restore, again get the first rowed element's name
        BrowserUtils.sleep(10);
        String firstRowNameAfterRestore = deletedFilesTab.firstRowFileName.getText();

        //compare first rowed element's name before and after restore to verify if the element disappeared or not
        Assert.assertNotEquals(firstRowNameBeforeRestore1, firstRowNameAfterRestore);

    }

    @Then("user can see the restored file in all files list")
    public void user_can_see_the_restored_file_in_all_files_list() {

        //order all deleted elements newest to oldest
        deletedFilesAreOrderedByNewestToOldest();

        //get the first row element's name and restore the first element
        String firstRowNameBeforeRestore = deletedFilesTab.firstRowFileName.getText();
        BrowserUtils.clickWithJS(deletedFilesTab.restoreButton);

        //go to AllFilesTab
        BrowserUtils.implicitlyWait(10);
        BrowserUtils.clickWithJS(allFilesTab.AllFilesTab);

        BrowserUtils.sleep(5);

        //verify if the restored element came back to AllFilesTab
        List<String> elementName = new ArrayList<>();
        for (WebElement eachElement : allFilesTab.allFilesElements) {
            elementName.add(eachElement.getText());
        }
        Assert.assertTrue(elementName.contains(firstRowNameBeforeRestore));

    }
}






