package main.model.db.dao.survey;

import main.model.db.dao.DAO;
import main.model.dto.survey.QuestionStatusDto;

public class QuestionStatusDao extends DAO<QuestionStatusDto> {
    public QuestionStatusDao() {
        super(QuestionStatusDto.class);
        select = "{call SELECT_QUESTION_STATUSES(?)}";

    }
}
