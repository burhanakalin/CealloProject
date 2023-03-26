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

public class DeletedFilesTab_StepDef {

    DeletedFilesTab deletedFilesTab = new DeletedFilesTab();
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
    AllFilesTab allFilesTab = new AllFilesTab();

        //User can see the most recent deleted file in the first line of the deleted file list when deleted files are ordered by newest to oldest
    @When("deleted files are ordered by newest to oldest")
    public void deletedFilesAreOrderedByNewestToOldest() {

        BrowserUtils.scrollToBottom();
        BrowserUtils.implicitlyWait(10);
        BrowserUtils.TwoClickWithJS(deletedFilesTab.sortIconDate);

    }


        //User can see the most recent deleted file in the first line of the deleted file list when deleted files are ordered by newest to oldest
    @Then("user should see recent deleted file")
    public void userShouldSeeRecentDeletedFile() {

        BrowserUtils.implicitlyWait(10);
        System.out.println("TrashBinElements.size() = " + deletedFilesTab.trashBinElements.size());

        for (int i = 1; i < deletedFilesTab.trashBinElements.size()-1; i++) {

            long intDate1 = Long.parseLong(deletedFilesTab.trashBinElements.get(i).getAttribute("data-mtime"));
            long intDate2 = Long.parseLong(deletedFilesTab.trashBinElements.get(i+1).getAttribute("data-mtime"));

            Assert.assertTrue(intDate1 >= intDate2);
        }

    }


        //User can order the all deleted files by newest to oldest or visa versa
    @When("user orders the deleted files by newest to oldest")
    public void user_orders_the_deleted_files_by_newest_to_oldest() {

        BrowserUtils.scrollToBottom();
        BrowserUtils.implicitlyWait(10);
        BrowserUtils.TwoClickWithJS(deletedFilesTab.sortIconDate);

    }

    @Then("the most recent ones will be listed on the top of the list")
    public void the_most_recent_ones_will_be_listed_on_the_top_of_the_list() {

        BrowserUtils.implicitlyWait(10);

        for (int i = 1; i < deletedFilesTab.trashBinElements.size()-1; i++) {

            long intDate1 = Long.parseLong(deletedFilesTab.trashBinElements.get(i).getAttribute("data-mtime"));
            long intDate2 = Long.parseLong(deletedFilesTab.trashBinElements.get(i+1).getAttribute("data-mtime"));

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

        BrowserUtils.scrollToBottom();
        BrowserUtils.implicitlyWait(10);

        js.executeScript("arguments[0].click()", deletedFilesTab.nameSortButton);

    }

    @Then("deleted files are ordered alphabetically by their names")
    public void deleted_files_are_ordered_alphabetically_by_their_names() {

            for (int i1 = 0; i1 < deletedFilesTab.trashBinElements.size()-1; i1++) {

                String dataName1 = deletedFilesTab.trashBinElements.get(i1).getAttribute("data-type");
                String fileName1 = deletedFilesTab.trashBinElements.get(i1).getAttribute("data-file");
//                System.out.println("i1 = " + i1);
//                System.out.println("fileName1 = " + fileName1);

                String dataName2 = deletedFilesTab.trashBinElements.get((i1+1)).getAttribute("data-type");
                String fileName2 = deletedFilesTab.trashBinElements.get((i1+1)).getAttribute("data-file");
//                System.out.println("(i1+1) = " + (i1 + 1));
//                System.out.println("fileName2 = " + fileName2);

                boolean verifyAlp = fileName1.compareToIgnoreCase(fileName2) <= 0;
                boolean dirBol = (dataName1.equals("dir")) && (dataName2.equals("dir"));
                boolean fileBol = (dataName1.equals("file")) && (dataName2.equals("file"));

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

        deletedFilesAreOrderedByNewestToOldest();

        String firstRowNameBeforeDeletion = deletedFilesTab.firstRowFileName.getText();

        BrowserUtils.implicitlyWait(10);
        BrowserUtils.clickWithJS(deletedFilesTab.firstRowThreeDots);

        Assert.assertTrue(deletedFilesTab.deletePermanentlyButton.isDisplayed());

    }

    @Then("user is able to delete file permanently")
    public void user_is_able_to_delete_file_permanently() {

        deletedFilesAreOrderedByNewestToOldest();

        String firstRowNameBeforeDeletion = deletedFilesTab.firstRowFileName.getText();

        BrowserUtils.implicitlyWait(10);
        BrowserUtils.clickWithJS(deletedFilesTab.firstRowThreeDots);
        BrowserUtils.clickWithJS(deletedFilesTab.deletePermanentlyButton);

        BrowserUtils.sleep(5);
        String firstRowNameAfterDeletion = deletedFilesTab.firstRowFileName.getText();

        Assert.assertNotEquals(firstRowNameBeforeDeletion, firstRowNameAfterDeletion);

    }


        //User can restore any deleted file and see it again under the all Files tab
    @When("user clicks on restore button and file disappears from deleted files list")
    public void user_clicks_on_restore_button_and_file_disappears_from_deleted_files_list() {

        deletedFilesAreOrderedByNewestToOldest();

        String firstRowNameBeforeRestore = deletedFilesTab.firstRowFileName.getText();

        BrowserUtils.implicitlyWait(10);
        BrowserUtils.clickWithJS(deletedFilesTab.restoreButton);

        BrowserUtils.sleep(5);
        String firstRowNameAfterRestore = deletedFilesTab.firstRowFileName.getText();

        Assert.assertNotEquals(firstRowNameBeforeRestore, firstRowNameAfterRestore);

    }

    @Then("user can see the restored file in all files list")
    public void user_can_see_the_restored_file_in_all_files_list() {

        deletedFilesAreOrderedByNewestToOldest();

        String firstRowNameBeforeRestore = deletedFilesTab.firstRowFileName.getText();
        BrowserUtils.clickWithJS(deletedFilesTab.restoreButton);

        BrowserUtils.implicitlyWait(10);
        BrowserUtils.clickWithJS(allFilesTab.deletedFilesTab);

        BrowserUtils.sleep(5);

        for (WebElement each : allFilesTab.allFilesElements) {

            String fileName = each.getAttribute("data-file");
            String firstRowNameAfterRestore = fileName.substring(0, fileName.indexOf("."));


            if(firstRowNameBeforeRestore.equals(firstRowNameAfterRestore)){
                Assert.assertTrue(true);
                break;
            }

        }


    }
}





