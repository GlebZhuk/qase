package service;

import io.qameta.allure.Step;
import model.Project;
import page.ProjectNamePage;
import page.ProjectsPage;

public class ProjectsPageService {
    private ProjectsPage projectsPage = new ProjectsPage();


    @Step("Get text on the page after registration")
    public String getTextProjectsOnPage() {
        return projectsPage.findTextProjectsOnPage();
    }


    @Step("Input data")
    public ProjectsPageService inputProjectsData(Project project) {
        projectsPage.clickCreateNewProjectButton()
                .fillInProjectName(project)
                .fillInProjectDescription(project)
                .setProjectAccessType();
        return this;
    }

    @Step("Get project code")
    public String getProjectCode() {
        return projectsPage.getProjectCode();
    }

    @Step("Create project")
    public ProjectNamePageService createNewProject() {
        projectsPage.clickCreateProjectButton();
        return new ProjectNamePageService();
    }

    @Step("Get message that you don't have any projects")
    public String getMessageYouDontHaveProjects() {
        return projectsPage.getMeassageDontHaveProjects();
    }

    @Step("Search project from value")
    public ProjectsPageService searchProjectFromValue(String value) {
        projectsPage.inputSearchField(value);
        return this;
    }

    @Step("Get projects search result")
    public String getSearchResult() {
        return projectsPage.getSearchResult();
    }


    @Step("Remove project")
    public ProjectsPageService removeProject() {

        for (int i = projectsPage.getCountProjects(); i >= 0; i--) {
            projectsPage
                    .clickButtonsRemoveOrSettingProject(i)
                    .clickButtonRemoveProject()
                    .clickButtonDeleteProject();
        }
        return new ProjectsPageService();
    }

    @Step("Rename project")
    public ProjectSettingsPageService clickSettingProjectButton() {
        for (int i = projectsPage.getCountProjects(); i >= 0; i--) {
            projectsPage
                    .clickButtonsRemoveOrSettingProject(i)
                    .clickButtonSettingsProject();
        }
        return new ProjectSettingsPageService();
    }

    @Step("Click clear search button")
    public ProjectsPageService clickCleanSearchButton() {
        projectsPage.clickCleanSearchButton();
        return this;
    }

    @Step("Set visibility for all projects")
    public ProjectsPageService setVisibilityProjects() {
        projectsPage.clickButtonStatusProjects()
                .setVisibilityStatusProjects()
                .clickButtonStatusProjects();
        return new ProjectsPageService();
    }

    @Step("Click project name")
    public ProjectNamePageService clickProjectName() {
        projectsPage.clickProjectName();
    return new ProjectNamePageService();
    }


}
