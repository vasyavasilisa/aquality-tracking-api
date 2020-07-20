package main.view.QualificationSurvey;

import main.Session;
import main.model.dto.survey.QuestionDto;
import main.view.BaseServlet;
import main.view.IDelete;
import main.view.IGet;
import main.view.IPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/questions")
public class QuestionServlet extends BaseServlet implements IGet, IPost, IDelete {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        setGetResponseHeaders(resp);
        setEncoding(resp);
        try {
            Session session = createSession(req);
            QuestionDto question = new QuestionDto();
            question.getSearchTemplateFromRequestParameters(req);
            List<QuestionDto> questions = session.controllerFactory.getHandler(question).get(question);
            setJSONContentType(resp);
            resp.getWriter().write(mapper.serialize(questions));
        }catch (Exception e) {
            handleException(resp, e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        setPostResponseHeaders(resp);
        setEncoding(resp);
        try {
            Session session = createSession(req);
            String requestedJson = getRequestJson(req);
            QuestionDto question = mapper.mapObject(QuestionDto.class, requestedJson);
            question = session.controllerFactory.getHandler(question).create(question);
            setJSONContentType(resp);
            resp.getWriter().write(mapper.serialize(question));
        }catch (Exception e) {
            handleException(resp, e);
        }
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        setDeleteResponseHeaders(resp);
        try {
            Session session = createSession(req);
            QuestionDto questionDto = new QuestionDto();
            questionDto.setId(Integer.parseInt(req.getParameter("id")));
            session.controllerFactory.getHandler(questionDto).delete(questionDto);
        }catch (Exception e) {
            handleException(resp, e);
        }
    }

    public void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        setOptionsResponseHeaders(resp);
    }

}
