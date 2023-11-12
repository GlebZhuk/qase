package ui.service;

import io.qameta.allure.Step;
import model.Project;
import ui.page.ProjectSettingsPage;

public class ProjectSettingsPageService {
    private final ProjectSettingsPage projectSettingsPage = new ProjectSettingsPage();

    @Step("Clear project name")
    public ProjectSettingsPageService renameProject(Project project) {
        projectSettingsPage.clearProjectNameField()
                .inputNewNameOfProject(project)
                .clickButtonUpdateSettings();
        return this;
    }

    @Step("Get message about successful update project")
    public String getMessageProjectUpdate() {
        return projectSettingsPage.getMessageSuccessfulUpdate();
    }

    @Step("Click button 'Projects' in menu")
    public ProjectsPageService clickButtonMenuProjects() {
        projectSettingsPage.clickButtonMenuProjects();
        return new ProjectsPageService();
    }

    @Step("Click button 'Archive project'")
    public ProjectSettingsPageService clickButtonArchiveProject() {
        projectSettingsPage.clickButtonArchiveProject();
        return new ProjectSettingsPageService();
    }

    @Step("Get message about successful archive project")
    public String getMessageProjectArchived() {
        return projectSettingsPage.getMessageSuccessfulArchived();
    }
}
