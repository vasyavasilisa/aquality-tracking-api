package main.model.dto.survey;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import main.annotations.DataBaseID;
import main.annotations.DataBaseInsert;
import main.annotations.DataBaseName;
import main.model.dto.BaseDto;
import main.model.dto.settings.UserDto;
import main.utils.CustomerDateAndTimeDeserialize;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionDto extends BaseDto {
    @DataBaseName(name="request_id")
    @DataBaseID
    @DataBaseInsert
    private Integer id;
    private Integer status_id;
    private QuestionStatusDto status;
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    private Date created;
    @DataBaseName(name="request_category_id")
    @DataBaseInsert
    private Integer category_id;
    private QuestionCategoryDto category;
    @DataBaseName(name="request_min_qual_id")
    @DataBaseInsert
    private Integer min_qual_id;
    private QualificationDto min_qual;
    @DataBaseName(name="request_max_qual_id")
    @DataBaseInsert
    private Integer max_qual_id;
    private QualificationDto max_qual;
    @DataBaseName(name="request_question")
    @DataBaseInsert
    private String question;
    @DataBaseName(name="request_answer")
    @DataBaseInsert
    private String answer;
    @DataBaseName(name="request_approver_id")
    private Integer approver_id;
    private UserDto approver;
    @DataBaseName(name="request_creator_id")
    @DataBaseInsert
    private Integer creator_id;
    private UserDto creator;
}
