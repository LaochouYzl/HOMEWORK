package cn.laochou.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;

import cn.laochou.db.DBUtils;
import cn.laochou.pojo.User;

public class UserDao {
	
	/**
	 * 	查询用户通过用户名和密码
	 * @param userName
	 * @param password
	 * @return
	 */
	public User selectUserByUserNameAndPassword(String userName, String password) {
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("select * from user where username = ? and password = ?");
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getInt("type"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return null;
	}
	
	public int insertUser(String userName, String password, String email) {
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("insert into user(username, password, email) values(?,?,?)");
			ps.setString(1, userName.trim());
			ps.setString(2, password.trim());
			ps.setString(3, email.trim());
			return ps.executeUpdate();			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps);
		}
		return 0;
	}
	
	public List<User> selectAllUser(){
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		List<User> list = new ArrayList<User>();
		ResultSet rs = null;
		User user = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("select * from user");
			rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setType(rs.getInt("type"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return list;
	}

}
