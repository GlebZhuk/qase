package model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Suite {
    private String suiteName;
    private String title;
    private String description;
    private String preconditions;
    @SerializedName("parent_id")
    private int parentId;
}
