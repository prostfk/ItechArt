package by.prostrmk.controller;

import by.prostrmk.model.Post;
import by.prostrmk.model.dao.PostDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/photo/*")
public class PhotoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestId = req.getPathInfo().split("/")[1];
        Post byDescription = new PostDao().findById(requestId);
        req.setAttribute("post", byDescription);
        req.getRequestDispatcher("/photos.jsp").forward(req,resp);
    }
}
