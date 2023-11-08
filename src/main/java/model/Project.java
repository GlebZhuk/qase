package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Project {
    private String projectName="Web Application";
    private String projectDescription="This is my test project";
    private String projectRename="QA Automation";

    public Project(String projectName, String projectDescription, String projectRename) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectRename = projectRename;
    }

    public Project(String projectName, String projectDescription) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }

    public Project() {
    }
}
