package cn.laochou.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import cn.laochou.dao.QuizDao;
import cn.laochou.pojo.User;

/**
 * 	�����ʴ�
 * @author Administrator
 *
 */
@WebServlet("/putQuiz")
public class PutQuiz extends HttpServlet implements Servlet {
	
	private QuizDao quizDao = new QuizDao();
	
	private static final long serialVersionUID = 1L;
	
	/** �ϴ�Ŀ¼��*/
    private static final String UPLOAD_DIR = "uploadDir/img/";
    /** �ϴ���ʱ�ļ��洢Ŀ¼*/
    private static final String TEMP_UPLOAD_DIR ="uploadDir/temp/";
    /** ���ϴ��ļ����Ϊ10M*/
    private static final Long TOTAL_FILE_MAXSIZE = 10000000L;
    /** �����ϴ��ļ����Ϊ10M*/
    private static final int SINGLE_FILE_MAXSIZE = 2*1024*1024;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "";
        //���ñ����ʽ��ǰ�˺�̨ͳһ��utf-8
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
        //
        PrintWriter out = response.getWriter();
        // Servlet�����Ķ���
        ServletContext servletContext = this.getServletConfig().getServletContext();
        // tomcat����Ŀ·��
        String realPath = servletContext.getRealPath(UPLOAD_DIR)+"/";
        String tempPath = servletContext.getRealPath(TEMP_UPLOAD_DIR)+"/";
        File tempPathFile = new File(tempPath);
        File realPathFile = new File(realPath);
        if (!tempPathFile.exists()) {
            tempPathFile.mkdirs();
        }
        if (!realPathFile.exists()) {
            realPathFile.mkdirs();
        }
        // �ļ�����Ĺ�����
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // ��������ϴ���С
        factory.setSizeThreshold(SINGLE_FILE_MAXSIZE);
        // ����ʱ�ļ��н����ļ�����Ĺ�����
        factory.setRepository(tempPathFile);
        // ����һ���ϴ��ļ��Ĵ�����
        ServletFileUpload upload = new ServletFileUpload(factory);
        // ��������������ܴ�С
         upload.setSizeMax(TOTAL_FILE_MAXSIZE);
        // ����request
        List<FileItem> items;
        Map<String, String> map = new HashMap<String, String>();
        try {
            items = upload.parseRequest(new ServletRequestContext(request));
            // �������������������
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                //�ж����ļ������ı�
                if (!item.isFormField()) {
                    //�ļ�������UUid�����Է�ֹ�ظ�
                    String fileName = UUID.randomUUID().toString().replace("-", "")+"_"+item.getName();
                    System.out.println("UploadTestServlet file path:"+UPLOAD_DIR+fileName);
                    //�жϿ�
                    if (fileName!=null && !"".equals(fileName)) {
                        //���ļ�д��Ӳ��
                        item.write(new File(realPath+fileName));
                        //��ͼƬ��ַ���浽request�У���ת���ظ�jsp
                        //UPLOAD_DIR+fileName��������·������ǰ��ҳ��
                        //realPath+fileName�Ǿ���·��
                        path = UPLOAD_DIR+fileName;
                    }
                } else {
                	 String fieldName = new String(item.getFieldName().getBytes("iso8859-1"), "utf-8");
                     String value = new String(item.getString().getBytes("iso8859-1"), "utf-8");
                     map.put(fieldName, value);
                }
            }
            if(map.get("content").equals("")|| map.get("title").equals("")|| map.get("category").equals("")||path.equals("")) {
            	request.setAttribute("code", "404");
            	request.setAttribute("msg", "���Ĳ���������");
            	request.getRequestDispatcher("/Error.jsp").forward(request, response);
            	return;
            }
            map.put("author", ((User)request.getSession().getAttribute("user")).getUserName());
            map.put("userId", ((User)request.getSession().getAttribute("user")).getId()+"");
            quizDao.insertQuiz(map, path);
            response.sendRedirect("blog?method=toBlogPage");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			out.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
