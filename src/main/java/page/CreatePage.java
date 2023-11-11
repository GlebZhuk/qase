package page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.CaseModel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class CreatePage extends Page {
    @FindBy(xpath = "//input[@id='title']")
    private WebElement titleField;
    @FindBy(xpath = "//button[@id='save-case']")
    private WebElement saveButton;

    @Step("Fill in case name field")
    public CreatePage inputCaseName(CaseModel caseModel) {
        log.info("Fill in suite name field");
        titleField.sendKeys(caseModel.getTitle());
        return this;
    }

    @Step("Click 'save' button")
    public ProjectNamePage clickSaveButton() {
        log.info("Click 'save' button");
        saveButton.click();
        return new ProjectNamePage();
    }

}
