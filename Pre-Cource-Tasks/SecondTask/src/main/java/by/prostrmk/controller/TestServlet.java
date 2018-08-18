package by.prostrmk.controller;

import by.prostrmk.model.dao.TestDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/testAdd")
@MultipartConfig
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/testadd.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rootPath = req.getServletContext().getInitParameter("file-upload");
        if (!ServletFileUpload.isMultipartContent(req)){
            resp.getWriter().print("ERROR");
        }
        String filename;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List fileItems = upload.parseRequest(req);
            fileItems.forEach(fileItem -> {
                FileItem item = (FileItem) fileItem;
                if (!item.isFormField()){
                    File file = new File(rootPath + item.getName());
                    try {
                        item.write(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    if (item.getFieldName().equals("username")){
//                        new TestDao().save(item.getName(),rootPath + );
                    }
                }
            });
        }catch (Exception e){}


    }
}
