package by.prostrmk.controller;

import by.prostrmk.model.dao.PostDao;
import by.prostrmk.model.entity.Post;
import by.prostrmk.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/delete/*")
public class DeleteController extends HttpServlet {

    private PostDao postDao;

    public DeleteController() {
        postDao = new PostDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            req.getRequestDispatcher("/auth.jsp").forward(req, resp);
        }
        String s = req.getPathInfo().split("/")[1];
        long id = Long.parseLong(s);
        Post byId = postDao.findById(id);
        req.getSession().setAttribute("post", byId);
        req.getRequestDispatcher("/delete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getPathInfo().split("/")[1];
        Post byId = postDao.findById(Long.parseLong(s));
        boolean delete = new File(req.getSession().getServletContext().getRealPath(byId.getPathToPhoto())).delete();
        boolean delete1 = new File("/home/prostrmk/Documents/Programs/Java/Java EE/ITechArt/Pre-Cource-Tasks/SecondTask/src/main/webapp" + byId.getPathToPhoto()).delete();
        if (delete && delete1){
            postDao.delete(byId.getId());
        }
        resp.sendRedirect("/photos");

    }
}
