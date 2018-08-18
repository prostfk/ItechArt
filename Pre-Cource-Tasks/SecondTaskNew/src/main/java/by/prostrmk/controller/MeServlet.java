package by.prostrmk.controller;

import by.prostrmk.dao.PostDao;
import by.prostrmk.model.entity.Post;
import by.prostrmk.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/me")
public class MeServlet extends HttpServlet {

    private PostDao postDao;

    public MeServlet() {
        this.postDao = new PostDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object userObj = req.getSession().getAttribute("user");
        if (userObj!=null){
            User user = (User) userObj;
            List<Post> postsByUserId = postDao.findPostsByUserId(user.getId());
            req.getSession().setAttribute("posts", postsByUserId);
            req.getRequestDispatcher("/me.jsp").forward(req,resp);
        }
        resp.sendRedirect("/auth.jsp");
    }
}
