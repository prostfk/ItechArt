package by.prostrmk.controller;

import by.prostrmk.dao.UserDao;
import by.prostrmk.model.entity.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user")==null){
            req.getRequestDispatcher("/auth.jsp").forward(req,resp);
        }
        req.getRequestDispatcher("/me.jsp").forward(req,resp);
    }
}
