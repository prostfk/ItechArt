package by.prostrmk.controller;

import by.prostrmk.dao.PostDao;
import by.prostrmk.model.entity.Post;
import by.prostrmk.model.entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/addPost")
public class AddPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user!=null){
            req.getRequestDispatcher("/addPost.jsp").forward(req,resp);
        }else{

            resp.sendRedirect("/auth");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rootPath = req.getServletContext().getInitParameter("file-upload");
        User user = (User) req.getSession().getAttribute("user");
        if (!ServletFileUpload.isMultipartContent(req)){
            resp.getWriter().print("ERROR");
        }
        Post post = new Post();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List fileItems = upload.parseRequest(req);

            for (Object fileItem : fileItems) {
                FileItem item = (FileItem) fileItem;
                if (!item.isFormField()){
                    String path = String.format("%s/%s/",req.getSession().getServletContext().getRealPath("resources"),user.getUsername());
                    File file = new File(path);
                    if (!Files.exists(Paths.get(path))){
                        file.mkdir();
                    }
                    file = new File(path + item.getName());
                    System.out.println("ok");
                    item.write(file);
                    post.setPath(String.format("/resources/%s/%s", user.getUsername(), item.getName()));
                }else{
                    if (item.getFieldName().equals("title")){
                        post.setTitle(item.getString());
                    }
                }
            }
            post.setUserId(user.getId());
            new PostDao().save(post);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
