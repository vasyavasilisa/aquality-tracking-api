package main.controllers.QualificationSurvey;

import main.controllers.BaseController;
import main.exceptions.AqualityException;
import main.model.db.dao.project.UserDao;
import main.model.db.dao.survey.QualificationDao;
import main.model.db.dao.survey.QuestionCategoryDao;
import main.model.db.dao.survey.QuestionDao;
import main.model.db.dao.survey.QuestionStatusDao;
import main.model.dto.settings.UserDto;
import main.model.dto.survey.QualificationDto;
import main.model.dto.survey.QuestionCategoryDto;
import main.model.dto.survey.QuestionDto;
import main.model.dto.survey.QuestionStatusDto;

import java.util.List;

public class QuestionController extends BaseController<QuestionDto> {
    private QuestionDao questionDao;
    private QuestionStatusDao questionStatusDao;
    private QuestionCategoryDao questionCategoryDao;
    private QualificationDao qualificationDao;
    private UserDao userDao;


    public QuestionController(UserDto user) {
        super(user);
        this.questionDao = new QuestionDao();
        this.questionStatusDao = new QuestionStatusDao();
        this.questionCategoryDao = new QuestionCategoryDao();
        this.qualificationDao = new QualificationDao();
        this.userDao = new UserDao();
    }

    @Override
    public List<QuestionDto> get(QuestionDto entity) throws AqualityException {
//        if (baseUser.isAdmin()) {
            return fillQuestions(questionDao.searchAll(entity));
//        } else {
////            throw new AqualityPermissionsException("Account is not allowed to view Suite Dashboards", baseUser);
////        }
    }

    @Override
    public QuestionDto create(QuestionDto entity) throws AqualityException {
        return questionDao.create(entity);
    }

    @Override
    public boolean delete(QuestionDto entity) throws AqualityException {
        return questionDao.delete(entity);
    }

    private List<QuestionDto> fillQuestions(List<QuestionDto> questions) throws AqualityException {
        if (questions.size() > 0) {
            return fillStatusesCategoriesUsersQualifications(questions);
        }
        return questions;
    }

    private List<QuestionDto> fillStatusesCategoriesUsersQualifications(List<QuestionDto> questions) throws AqualityException {
        QuestionStatusDto questionStatusDto = new QuestionStatusDto();
        QuestionCategoryDto questionCategoryDto = new QuestionCategoryDto();
        QualificationDto qualificationDto = new QualificationDto();
        UserDto approverDto = new UserDto();

        List<QuestionStatusDto> statuses = questionStatusDao.searchAll(questionStatusDto);
        List<QuestionCategoryDto> categories = questionCategoryDao.searchAll(questionCategoryDto);
        List<QualificationDto> qualifications = qualificationDao.searchAll(qualificationDto);
        List<UserDto> users = userDao.searchAll(approverDto);

        for (QuestionDto question : questions) {
            question.setStatus(statuses.stream().filter(x -> x.getId().equals(question.getStatus_id())).findFirst().orElse(null));
            question.setCategory(categories.stream().filter(x -> x.getId().equals(question.getCategory_id())).findFirst().orElse(null));
            question.setMin_qual(qualifications.stream().filter(x -> x.getId().equals(question.getMinqual_id())).findFirst().orElse(null));
            question.setMax_qual(qualifications.stream().filter(x -> x.getId().equals(question.getMaxqual_id())).findFirst().orElse(null));
            question.setApprover(users.stream().filter(x -> x.getId().equals(question.getApprover_id())).findFirst().orElse(null));
            question.setCreator(users.stream().filter(x -> x.getId().equals(question.getCreator_id())).findFirst().orElse(null));
        }
        return questions;
    }
}
