package by.prostrmk.controller;

import by.prostrmk.model.entity.Post;
import by.prostrmk.model.dao.PostDao;
import by.prostrmk.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/photos")
public class PhotoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Post> postsByUser = new PostDao().findPostsByUser(user);
        req.getSession().setAttribute("posts", postsByUser);
        req.getRequestDispatcher("/photos.jsp").forward(req,resp);
    }
}
