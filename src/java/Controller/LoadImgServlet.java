/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SanPham;
import Model.SanPhamDAO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Admin
 */
public class LoadImgServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "img";

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String maSP = null;
            String ten = null;
            String soluong = null;
            String gia = null;
            String maloaiSP = null;
            String action = null;
            String file = null;
            String fileName = null;
            SanPhamDAO spDAO = new SanPhamDAO();
            // configures upload settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // sets memory threshold - beyond which files are stored in disk 
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            // sets temporary location to store files
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);

            // sets maximum size of upload file
            upload.setFileSizeMax(MAX_FILE_SIZE);

            // sets maximum size of request (include file + form data)
            upload.setSizeMax(MAX_REQUEST_SIZE);

            // constructs the directory path to store upload file
            // this path is relative to application's directory
            String uploadPath = getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIRECTORY;

            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    if (item.getFieldName().equals("action")) {
                        action = item.getString("UTF-8");
                    }
                    if (item.getFieldName().equals("maSP")) {
                        maSP = item.getString("UTF-8");
                    }
                    if (item.getFieldName().equals("ten")) {
                        ten = item.getString("UTF-8");
                    }
                    if (item.getFieldName().equals("soluong")) {
                        soluong = item.getString("UTF-8");
                    }
                    if (item.getFieldName().equals("gia")) {
                        gia = item.getString("UTF-8");
                    }
                    if (item.getFieldName().equals("file")) {
                        file = item.getString("UTF-8");
                    }
                    if (item.getFieldName().equals("maloaiSP")) {
                        maloaiSP = item.getString("UTF-8");
                    }

                }
                switch (action) {
                    case "add":
                        if (formItems != null && formItems.size() > 0) {
                            // iterates over form's fields
                            for (FileItem item : formItems) {
                                if (!item.isFormField()) {
                                    if (item.getFieldName().equals("file")) {
                                        fileName = maSP + "_anh.jpg";
                                        String filePath = uploadPath + File.separator + fileName;
                                        String root = getServletContext().getRealPath("/");
                                        File storeFile = new File(filePath);
                                        File storeFile1 = new File(root + "..\\..\\web\\img" + fileName);
                                        item.write(storeFile);
                                        item.write(storeFile1);
                                    }
                                }
                            }
                        }
                        SanPham sp = new SanPham(maSP, ten, Integer.parseInt(soluong), Integer.parseInt(gia), fileName, maloaiSP);
                        if (spDAO.addSanPham(sp) == 1) {
                            response.sendRedirect("addsp.jsp");
                        }else{
                            System.out.println("loiiiuuy");
                        }
                        break;

                    case "update":
                        SanPham sp1 = new SanPham(maSP, ten, Integer.parseInt(soluong), Integer.parseInt(gia), fileName, maloaiSP);
                        if (spDAO.updateSanPham(sp1) == 1) {
                            request.setAttribute("LIST_SANPHAM", spDAO.getAllSanPham());
                            request.getRequestDispatcher("addsp.jsp").forward(request, response);
                        }
                        break;

                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(LoadImgServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoadImgServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
