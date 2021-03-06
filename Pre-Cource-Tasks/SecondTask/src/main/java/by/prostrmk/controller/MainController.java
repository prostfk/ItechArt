package by.prostrmk.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import by.prostrmk.model.entity.Post;
import by.prostrmk.model.dao.PostDao;
import by.prostrmk.model.entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("")
@MultipartConfig
public class MainController extends HttpServlet {

    private PostDao postDao;

    public MainController() {
        postDao = new PostDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println();
        if (req.getSession().getAttribute("user")!=null){
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
        req.getRequestDispatcher("/auth.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (!ServletFileUpload.isMultipartContent(req)){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024);
        File file = new File("/home/prostrmk/Документы/Programs/Java/Java EE/ITechArt/Pre-Cource-Tasks/SecondTask/src/main/webapp/resources/pics/" + user.getUsername()+"/");
        File file2 = new File(req.getSession().getServletContext().getRealPath("resources") + "/pics/" + user.getUsername() + "/");
        if (!file.exists()){
            file.mkdir();
        }
        if (!file2.exists()){
            file.mkdir();
        }
        factory.setRepository(file);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024 * 1024 * 10);
        Post post = new Post();
        post.setUserId(user.getId());
        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            fileItems.forEach(fileItem -> {
                if (!fileItem.isFormField()){
                    String path = getNewName("/home/prostrmk/Документы/Programs/Java/Java EE/ITechArt/Pre-Cource-Tasks/SecondTask/src/main/webapp/resources/pics/" + user.getUsername() + "/",post.getDescription(),fileItem, user.getUsername());
                    post.setPathToPhoto(path);
                    getNewName(file2.getAbsolutePath() + "/",post.getDescription(),fileItem, user.getUsername());
                }else{
                    if(fileItem.getFieldName().equals("description")){
                        post.setDescription(fileItem.getString());
                    }
                }
            });
            if (post.getDescription() !=null && post.getPathToPhoto()!=null){
                postDao.save(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        resp.sendRedirect("/photos");
    }

    private String getNewName(String path,String desc, FileItem item, String username){
        String format = ".jpg";
        File file = new File(path + desc + format);
        try {
            file.createNewFile();
            item.write(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/resources/pics/" + username + "/" + desc + format;
    }

}
