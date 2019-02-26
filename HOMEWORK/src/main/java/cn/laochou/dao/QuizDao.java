package cn.laochou.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;

import cn.laochou.db.DBUtils;
import cn.laochou.pojo.Quiz;

public class QuizDao {
	
	// 查找所有问题
	public List<Quiz> selectAllQuiz(){
		List<Quiz> list = new ArrayList<Quiz>();
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		Quiz quiz = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("select id, user_id, author, title, content, image, pub_time, category from quiz order by pub_time desc");
			rs = ps.executeQuery();
			while(rs.next()) {
				quiz = new Quiz();
				quiz.setId(rs.getInt("id")).setUserId(rs.getInt("user_id")).setAuthor(rs.getString("author"))
				.setTitle(rs.getString("title")).setContent(rs.getString("content"))
				.setImage(rs.getString("image")).setPubTime(rs.getString("pub_time")).setCategory(rs.getString("category"));
				list.add(quiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return list;
	}
	
	// 根据ID查找问题
	public Quiz selectQuizById(Integer id) {
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		Quiz quiz = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("select id, user_id, author, title, content, image, pub_time, category from quiz where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				quiz = new Quiz();
				quiz.setId(rs.getInt("id")).setUserId(rs.getInt("user_id")).setAuthor(rs.getString("author"))
				.setTitle(rs.getString("title")).setContent(rs.getString("content"))
				.setImage(rs.getString("image")).setPubTime(rs.getString("pub_time")).setCategory(rs.getString("category"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return quiz;
	}
	
	// 根据条件查询quiz
	public List<Quiz> searchQuizByCondition(String condition){
		List<Quiz> list = new ArrayList<Quiz>();
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		Quiz quiz = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("select id, user_id, author, title, content, image, pub_time, category from quiz where title like ?");
			ps.setString(1, "%"+condition+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				quiz = new Quiz();
				quiz.setId(rs.getInt("id")).setUserId(rs.getInt("user_id")).setAuthor(rs.getString("author"))
				.setTitle(rs.getString("title")).setContent(rs.getString("content"))
				.setImage(rs.getString("image")).setPubTime(rs.getString("pub_time")).setCategory(rs.getString("category"));
				list.add(quiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return list;
	}
	
	// 新建问答
	public void insertQuiz(Map<String, String> map, String path) {
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("insert into quiz(user_id, author, title, content, image, category, pub_time) values(?,?,?,?,?,?,NOW())");
			ps.setInt(1, Integer.parseInt(map.get("userId")));
			ps.setString(2, map.get("author"));
			ps.setString(3, map.get("title"));
			ps.setString(4, map.get("content"));
			ps.setString(5, path);
			ps.setString(6, map.get("category"));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps);
		}
	}
	
	// 删除问答
	public void deleteQuiz(Integer id) {
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("delete from quiz where id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps);
		}
	}

}
