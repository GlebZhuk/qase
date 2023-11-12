package ui.service;

import io.qameta.allure.Step;
import model.Suite;
import ui.page.ProjectNamePage;


public class ProjectNamePageService {
    private final ProjectNamePage projectNamePage = new ProjectNamePage();

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
    public String createNewSuite(Suite suite) {
        return projectNamePage.clickButtonNewSuite()
                .inputSuiteName(suite)
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
        return projectNamePage.getMessageOfCreatedCase();
    }
}
