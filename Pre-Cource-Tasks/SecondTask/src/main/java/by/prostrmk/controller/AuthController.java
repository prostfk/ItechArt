package by.prostrmk.controller;

import by.prostrmk.model.dao.UserDao;
import by.prostrmk.model.entity.User;
import by.prostrmk.model.util.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth")
public class AuthController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User userByUsername = new UserDao().findUserByUsername(username);
        User user = new User(userByUsername.getId(),username,HashUtil.hash(password));
        if (userByUsername.equals(user)){
            req.getSession().setAttribute("user", user);
            resp.getWriter().print("<h1>Congrats</h1>");
        }else{
            resp.getWriter().print("<h1>Loh idi nahui</h1>");
        }
    }
}
