package service;

import io.qameta.allure.Step;
import model.CaseModel;
import page.CreatePage;

public class CreatePageService {
    CreatePage createPage = new CreatePage();

    @Step("Create new case")
    public ProjectNamePageService createNewCase(CaseModel caseModel) {
        createPage.inputCaseName(caseModel)
                .clickSaveButton();
        return new ProjectNamePageService();

    }
}
