package cn.laochou.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;

import cn.laochou.db.DBUtils;
import cn.laochou.pojo.Comment;

public class CommentDao {
	
	// 根据Id找到所有评论
	public List<Comment> selectCommentById(Integer id){
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		Comment comment = null;
		List<Comment> list = new ArrayList<Comment>();
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("SELECT comment.id, username, content, pub_time FROM comment, user where user.id = comment.user_id and quiz_id = ? order by pub_time desc");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				comment = new Comment();
				comment.setId(rs.getInt("id")).setUserName(rs.getString("username")).setContent(rs.getString("content")).setPubTime(rs.getString("pub_time"));
				list.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(conn, ps, rs);
		}
		return list;
	}
	
	// 插入记录
	public void insertComment(Integer userId, Integer quizId, String content) {
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("insert into comment(user_id, quiz_id, content, pub_time) values(?,?,?,NOW())");
			ps.setInt(1, userId);
			ps.setInt(2, quizId);
			ps.setString(3, content);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps);
		}
		
	}

}
