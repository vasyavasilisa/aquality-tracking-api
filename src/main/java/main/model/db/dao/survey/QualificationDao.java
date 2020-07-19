package main.model.db.dao.survey;

import main.model.db.dao.DAO;
import main.model.dto.survey.QualificationDto;

public class QualificationDao  extends DAO<QualificationDto> {
    public QualificationDao() {
        super(QualificationDto.class);
        select = "{call SELECT_QUALIFICATIONS(?)}";

    }
}