package cn.laochou.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;

import cn.laochou.db.DBUtils;

public class CodeDao {
	
	public void insertCode(String email, String code) {
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("insert into code(email, code) values(?,?)");
			ps.setString(1, email);
			ps.setString(2, code);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps);
		}
	}
	
	
	public void updateCode(String email, String code) {
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("update code set code = ? where email = ?");
			ps.setString(1, code);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps);
		}
	}
	
	/**
	 * 	根据email和code进行查询
	 * @param email
	 * @param code
	 * @return
	 */
	public boolean selectCodeByEmailAndCode(String email, String code) {
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("select * from code where email = ? and code = ?");
			ps.setString(1, email);
			ps.setString(2, code);
			rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return false;
	}
	
	public boolean checkTheEmailIsRegistered(String email) {
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("select * from user where email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return false;
	}
	
	public boolean checkTheEmailIsOnRecord(String email) {
		ConnectionImpl conn = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (ClientPreparedStatement) conn.prepareStatement("select * from code where email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return false;
	}

}
