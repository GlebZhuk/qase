package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String projectName;
    private String projectRename;
    private String title;
    private String code;
    private String description;
    private String access;
    private String group;
}
