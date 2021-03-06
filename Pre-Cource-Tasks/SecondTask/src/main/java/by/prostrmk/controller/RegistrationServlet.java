package by.prostrmk.controller;

import by.prostrmk.model.dao.UserDao;
import by.prostrmk.model.entity.User;
import by.prostrmk.model.util.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (!username.equals("") ||!password.equals("")){
            password = CommonUtil.hash(password);
            new UserDao().save(new User(username,password));
        }
        resp.sendRedirect("/auth");
    }
}
