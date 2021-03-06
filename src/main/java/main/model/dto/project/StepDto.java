package main.model.dto.project;

import lombok.Data;
import lombok.EqualsAndHashCode;
import main.annotations.DataBaseID;
import main.annotations.DataBaseInsert;
import main.annotations.DataBaseName;
import main.annotations.DataBaseSearchable;
import main.model.dto.BaseDto;

@Data
@EqualsAndHashCode(callSuper = true)
public class StepDto extends BaseDto {
    @DataBaseName(name="request_id")
    @DataBaseID
    @DataBaseInsert
    @DataBaseSearchable
    private Integer id;
    @DataBaseName(name="request_name")
    @DataBaseInsert
    @DataBaseSearchable
    private String name;
    @DataBaseName(name="request_type_id")
    @DataBaseInsert
    private Integer type_id;
    @DataBaseName(name="request_project_id")
    @DataBaseID
    @DataBaseInsert
    @DataBaseSearchable
    private Integer project_id;
    private Integer order;
    private Integer link_id;
}
