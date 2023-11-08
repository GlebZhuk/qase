package service;

import io.qameta.allure.Step;
import page.ProjectNamePage;


public class ProjectNamePageService {
    private ProjectNamePage projectNamePage = new ProjectNamePage();

    @Step("Get text from name of new project")
    public String getNameOfNEwProject() {
        return projectNamePage.getNameOfNEwProject();
    }

    @Step("Get message if code already is used")
    public String getMassageIfProjectCodeUsed() {
        return projectNamePage.getTextCodeIsUsed();
    }


    @Step("Click button menu 'projects'")
    public ProjectsPageService clickButtonProjects() {
       projectNamePage.clickButtonMenuProjects();
        return new ProjectsPageService();
    }

    @Step("Create new suite")
    public String createNewSuite(String suiteName) {
return projectNamePage.clickButtonNewSuite()
        .inputSuiteName(suiteName)
         .clickButtonCreateSuite()
        .getMessageOfCreatedSuite();
    }
    @Step("Click create new case button")
    public CreatePageService clickCreateNewCase() {
         projectNamePage
                .clickButtonNewCase();
        return new CreatePageService();

    }
    @Step("Get message about case created")
    public String getMessageCaseCreated() {
       return   projectNamePage.getMessageOfCreatedCase();
    }



}
