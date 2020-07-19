package main.model.db.dao.survey;

import main.model.db.dao.DAO;
import main.model.dto.survey.QuestionCategoryDto;

public class QuestionCategoryDao extends DAO<QuestionCategoryDto> {
    public QuestionCategoryDao() {
        super(QuestionCategoryDto.class);
        select = "{call SELECT_QUESTION_CATEGORIES(?)}";

    }
}