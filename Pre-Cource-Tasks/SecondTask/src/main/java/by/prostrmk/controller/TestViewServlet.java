package by.prostrmk.controller;

import by.prostrmk.model.dao.TestDao;
import by.prostrmk.model.entity.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/testView")
public class TestViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        List<Post> postsByUsername = new TestDao().findPostsByUsername(username);
        req.getSession().setAttribute("posts", postsByUsername);
        req.getRequestDispatcher("testView.jsp").forward(req,resp);
    }
}
