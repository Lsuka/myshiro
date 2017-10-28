package cn.unicorn.myshiro.realm.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.unicorn.myshiro.realm.service.IMemberService;
import cn.unicorn.myshiro.realm.vo.Member;

public class MemberServiceImpl implements IMemberService {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/mldn";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "mysqladmin";
	private Connection conn;

	public MemberServiceImpl() {
		try {
			Class.forName(DRIVER);
			this.conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Member get(String mid) {
		String sql = "SELECT mid,name,password,locked FROM member WHERE mid=?";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Member vo = new Member();
				vo.setMid(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setPassword(rs.getString(3));
				vo.setLocked(rs.getInt(4));
				return vo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return null;
	}

	public void close() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
