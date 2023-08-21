package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ImageListServlet")
public class ImageListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = getServletContext().getRealPath("/");
        File webAppsFolder = new File(contextPath);
        List<String> imageUrls = new ArrayList<>();

        if (webAppsFolder.exists() && webAppsFolder.isDirectory()) {
            findImageUrls(webAppsFolder, imageUrls);
        }

        request.setAttribute("imageUrls", imageUrls);
        request.getRequestDispatcher("/list_images.jsp").forward(request, response);
    }

    private void findImageUrls(File folder, List<String> imageUrls) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findImageUrls(file, imageUrls);
                } else if (isImageFile(file)) {
                    imageUrls.add(file.getPath().replace(getServletContext().getRealPath("/"), ""));
                }
            }
        }
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif");
    }
}


// import java.io.*;
// import javax.servlet.*;
// import javax.servlet.http.*;
// import java.util.*;
// import javax.servlet.ServletContext.*;

// public class ImageDisplayServlet extends HttpServlet {
 
//     protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
//         String category = request.getParameter("category");
          
//         if (category != null && (category.equals("cats") || category.equals("dogs"))) {
//             String imagePath = "/images/" + category + "/";
//             System.out.println(imagePath);
//             List<String> imageUrls= getImageUrls(request, response, imagePath);
//             response.setContentType("application/json");
//             System.out.println(imageUrls);
//             PrintWriter out = response.getWriter();
//             out.print("{\"images\":" + imageUrls + "}");
//             out.flush();
//         } else {
//             response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category");
//         }
//     }

//     private void displayImages(HttpServletRequest request, HttpServletResponse response, String imagePath) throws ServletException, IOException {
//         String contextPath = request.getContextPath();
//         String realPath = getServletContext().getRealPath(imagePath);
//         File imageDirectory = new File(realPath);
//         File[] imageFiles = imageDirectory.listFiles();
//         System.out.println("image files are "+ imageFiles);
    
//         // if (imageFiles != null) {
//         //     for (File imageFile : imageFiles) {
//         //         if (imageFile.isFile() && imageFile.getName().endsWith(".jpg")) {
//         //             FileInputStream inputStream = new FileInputStream(imageFile);
//         //             byte[] imageBytes = new byte[(int) imageFile.length()];
    
//         //             int bytesRead;
//         //             while ((bytesRead = inputStream.read(imageBytes)) != -1) {
//         //                 response.getOutputStream().write(imageBytes, 0, bytesRead);
//         //             }
    
//         //             inputStream.close();
//         //         }
//         //     }
//         }


//         private List<String> getImageUrls(HttpServletRequest request, HttpServletResponse response, String imagePath) 
//         throws ServletException{
//             List<String> imageUrls = new ArrayList<>();
//             String basePath = request.getServletContext().getResourcePaths(imagePath);
//             System.out.println("basepath is "+ basePath);
//             File folder = new File(basePath);
//             File[] listOfFiles = folder.listFiles();
        
//             if (listOfFiles != null) {
//                 for (File file : listOfFiles) {
//                     if (file.isFile()) {
//                         imageUrls.add("images/Dogs/" + file.getName());
//                     }
//                     System.out.println("inside listFiles is "+ file.getName());
//                 }
//                  System.out.println("outside listFiles is ");
//             }
        
//             return imageUrls;
//         }
//     }
    

