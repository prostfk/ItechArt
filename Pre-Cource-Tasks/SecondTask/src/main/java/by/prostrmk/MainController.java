package by.prostrmk;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import by.prostrmk.model.Post;
import by.prostrmk.model.dao.PostDao;
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
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(req)){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024);
        File file = new File("/home/prostrmk/Documents/Programs/Java/Java EE/ITechArt/Pre-Cource-Tasks/SecondTask/src/main/webapp/resources/pics/");
        if (!file.exists()){
            file.mkdir();
        }
        factory.setRepository(file);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024 * 1024 * 10);
        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            Post post = new Post();
            fileItems.forEach(fileItem -> {
                if (!fileItem.isFormField()){
                    String path = processUploadedFile(fileItem, file);
                    post.setPathToPhoto(path);
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



    }
    private String processUploadedFile(FileItem fileItem, File file)  {
        File uploadedFile = new File(file.getPath() + "/" + fileItem.getName());
        try {
            uploadedFile.createNewFile();
            fileItem.write(uploadedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "resources/pics/" +  fileItem.getName();
    }
}
