package main.view.Administration;

import main.Session;
import main.model.dto.settings.UserDto;
import main.view.BaseServlet;
import main.view.IGet;
import main.view.IPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends BaseServlet implements IGet, IPost {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        setPostResponseHeaders(resp);
        setEncoding(resp);

        try {
            Session session = createSession(req);
            UserDto userTemplate = new UserDto();
            userTemplate.getSearchTemplateFromRequestParameters(req);
            List<UserDto> users = session.controllerFactory.getHandler(userTemplate).get(userTemplate);
            setJSONContentType(resp);
            resp.getWriter().write(mapper.serialize(users));
        } catch (Exception e) {
            handleException(resp, e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        setPostResponseHeaders(resp);
        setEncoding(resp);

        try {
            Session session = createSession(req);
            UserDto user = mapper.mapObject(UserDto.class, getRequestJson(req));
            user = session.controllerFactory.getHandler(user).create(user);
            setJSONContentType(resp);
            resp.getWriter().write(mapper.serialize(user));
        } catch (Exception e) {
            handleException(resp, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        setDeleteResponseHeaders(resp);
        setEncoding(resp);

        try {
            Session session = createSession(req);
            UserDto user = new UserDto();
            user.setId(Integer.parseInt(req.getParameter("id")));
            session.controllerFactory.getHandler(user).delete(user);
        } catch (Exception e) {
            handleException(resp, e);
        }
    }

    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        setOptionsResponseHeaders(resp);
    }
}

