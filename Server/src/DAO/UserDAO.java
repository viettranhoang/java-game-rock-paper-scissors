package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BEAN.User;
import DBController.DBConnection;

public class UserDAO {
	public static boolean checkLogin(String user_name,String user_pass) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql="select user_login from user where user_login = ? and user_pass = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, user_name);
			ptmt.setString(2, user_pass);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(conn);
			DBConnection.closePreparedStatement(ptmt);
			DBConnection.closeResultSet(rs);
		}
		return false;
	}
	public static boolean checkUser(String user_name) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql="select user_login from user where user_login = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, user_name);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(conn);
			DBConnection.closePreparedStatement(ptmt);
			DBConnection.closeResultSet(rs);
		}
		return false;
	}
	public static void creatUser(User user) {
		Connection conn= null;
		PreparedStatement ptmt= null;
		String sql="insert into user value(?,?,?,?,?,?)";
		try {
			conn = DBConnection.getConnection();
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, 0);
			ptmt.setString(2, user.getUser_login());
			ptmt.setString(3, user.getUser_name());
			ptmt.setString(4, user.getUser_pass());
			ptmt.setInt(5,0);
			ptmt.setInt(6,0);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(conn);
			DBConnection.closePreparedStatement(ptmt);
		}
	}
	public static void online(String user_name) {
		Connection conn = DBConnection.getConnection();
		String sql = "update user set user_status = ?  where user_login = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,1);
			ps.setString(2, user_name);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
			DBConnection.closePreparedStatement(ps);
		}
	}
	public static void offline(String user_name) {
		Connection conn = DBConnection.getConnection();
		String sql = "update user set user_status = ?  where user_login = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,0);
			ps.setString(2, user_name);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
			DBConnection.closePreparedStatement(ps);
		}
	}
	
}
