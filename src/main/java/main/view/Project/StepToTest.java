package main.view.Project;

import main.Session;
import main.model.dto.Step2TestDto;
import main.model.dto.StepDto;
import main.view.BaseServlet;
import main.view.IDelete;
import main.view.IPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/step/toTest")
public class StepToTest extends BaseServlet implements IPost, IDelete {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        setPostResponseHeaders(resp);
        setEncoding(resp);

        try {
            Session session = createSession(req);
            String requestedJson = getRequestJson(req);
            Step2TestDto step2Test = mapper.mapObject(Step2TestDto.class, requestedJson);
            session.controllerFactory.getHandler(new StepDto()).assignToTest(step2Test);
            setJSONContentType(resp);
        }catch (Exception e) {
            handleException(resp, e);
        }
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        setPostResponseHeaders(resp);
        setEncoding(resp);

        try {
            Session session = createSession(req);
            String requestedJson = getRequestJson(req);
            Step2TestDto step2Test = mapper.mapObject(Step2TestDto.class, requestedJson);
            session.controllerFactory.getHandler(new StepDto()).removeFromTest(step2Test);
            setJSONContentType(resp);
        }catch (Exception e) {
            handleException(resp, e);
        }
    }

    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        setOptionsResponseHeaders(resp);
    }
}
