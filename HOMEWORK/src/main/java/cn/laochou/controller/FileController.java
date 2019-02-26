package cn.laochou.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import cn.laochou.utils.FileUtils;

@WebServlet("/file")
public class FileController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	 // �ϴ��ļ��洢Ŀ¼
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // �ϴ�����
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
        // ����Ƿ�Ϊ��ý���ϴ�
        if (!ServletFileUpload.isMultipartContent(request)) {
            // ���������ֹͣ
            writer.println("Error: ��������� enctype=multipart/form-data...");
            writer.flush();
            return;
        }
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // �����ϴ�����
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // ������ʱ�洢Ŀ¼
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        // ��������ļ��ϴ�ֵ
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // �����������ֵ (�����ļ��ͱ�����)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        // ������ʱ·�����洢�ϴ����ļ�
        // ���·����Ե�ǰӦ�õ�Ŀ¼
        String uploadPath = request.getContextPath() + File.separator + UPLOAD_DIRECTORY;
        //��ȡ��Ŀ����·��  �µ�upload�ļ���
        uploadPath = request.getSession().getServletContext().getRealPath("/upload");
        // ���Ŀ¼�������򴴽�
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
        	String content = "";
        	String number = "";
            // ���������������ȡ�ļ�����
            List<FileItem> formItems = upload.parseRequest(new ServletRequestContext(request));
            if (formItems != null && formItems.size() > 0) {
                // ����������
                for (FileItem item : formItems) {
                    // �����ڱ��е��ֶ�
                    if (!item.isFormField()) {
                        //���ﴦ���ļ���������û�á�����������������
                        System.out.println(new String(item.getName().getBytes("utf-8")));
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // �ڿ���̨����ļ����ϴ�·��
                        System.out.println(filePath);
                        // �����ļ���Ӳ��
                        item.write(storeFile);
                        // ��ȡ�ļ����ݷ��ص�ǰ��
                        content = FileUtils.readTextFile(filePath);
                    }else {
                    	number = new String(item.get());
                    }
                }
                writer.println("{\"content\":["+content+"], \"number\":"+number+", \"row\":"+getRow(content)+", \"col\":"+getCol(content)+"}");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static int getRow(String content) {
		String[] rows = content.split("\n");
		return rows.length;
	}
	
	public static int getCol(String content) {
		String[] cols = content.split("\n")[0].split(",");
		return cols.length;
	}

}
