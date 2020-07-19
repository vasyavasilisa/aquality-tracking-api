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
public class QuestionServlet extends BaseServlet implements IGet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //4a3858c5cc158ec0ec3072bec1bfb827
        //            CALL SELECT_QUESTIONS('', 1, '', 1, 3, 1, '');
        //         CALL   INSERT_DRAFT_QUESTIONS ('', 1, 1, 3, 'Some Draft Question2', 'Answer To SomeDraft Question2', 1)
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

    public void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        setOptionsResponseHeaders(resp);
    }

}
