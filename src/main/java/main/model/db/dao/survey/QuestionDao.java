package main.model.db.dao.survey;

import main.model.db.dao.DAO;
import main.model.dto.survey.QuestionDto;

public class QuestionDao extends DAO<QuestionDto> {
    public QuestionDao() {
        super(QuestionDto.class);
        insert = "{call INSERT_DRAFT_QUESTIONS(?,?,?,?,?,?,?)}";
        select = "{call SELECT_QUESTIONS()}";//todo

    }
}
