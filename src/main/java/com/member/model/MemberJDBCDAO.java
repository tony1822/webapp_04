package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberJDBCDAO implements Member_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tia101_g2?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_INTO = "INSERT INTO member(member_Account,member_Password,member_Name,member_Phone,member_Email)VALUES(?,?,?,?,?);";
	private static final String FIND_ONE_MEMBER=" SELECT* FROM member WHERE member_Id=?";
	
	private static final String UPDATE_MEMBER_PASSWD="UPDATE member SET memberPassword=? WHERE member_Id=?"; 
	
	private static final String UPDATE_MEMBER_IMG="UPDATE member SET member_img=? WHERE member_Id=?"; 

	private static final String DELETE = "DELETE FROM member WHERE member_Id=?";

	private static final String FIND_ONE_REGISTER = "SELECT* FROM member WHERE member_account=? and member_password=? ";

	private static final String FIND_ALL_MEMBER = "SELECT *FROM member ORDER BY member_Id";
	private static final String CEHCK_UK_EMAIL = "SELECT COUNT(*) FROM member WHERE member_email = ?";
	
	private static final String UPDATE = "UPDATE member SET member_Name=?,member_Account=?,member_Email=?, member_Phone=?,member_Password=? where member_Id=?";

	@Override
	//新增一筆資料
	public void insert(Member member) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_INTO);
			pstmt.setString(1, member.getMemberAccount());
			pstmt.setString(2, member.getMemberPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberEmail());

	
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
	
	public Member findOneMember(Integer memberId) {	
		Member member = new Member();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_ONE_MEMBER);
			pstmt.setInt(1, memberId);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				member.setMemberId(rs.getInt("member_Id"));
				member.setMemberAccount(rs.getString("member_Account"));
				member.setMemberPassword(rs.getString("member_Password"));
				member.setMemberName(rs.getString("member_Name"));
				member.setMemberPhone(rs.getString("member_Phone"));
				member.setMemberEmail(rs.getString("member_Email"));
				member.setMemberRegisterDatetime(rs.getDate("member_Register_Datetime"));
				member.setMemberImg(rs.getBytes("member_Img"));		
			}			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return member;
	}

	
	
	
	
	

	
	@Override
//	更新除了大頭貼跟註冊時間的其餘資料
	public void update(Member member) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getMemberAccount());
			pstmt.setString(3, member.getMemberEmail());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberPassword());
			pstmt.setInt(6, member.getMemberId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	

	
	//更新會員的密碼
	public void update_passwd(Member member) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_MEMBER_PASSWD);
			pstmt.setString(1, member.getMemberPassword());
			pstmt.setInt(2, member.getMemberId());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	
	
	
	//更新大頭貼
	@Override
	public void updateImg(Member member) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(UPDATE_MEMBER_IMG);
			pstmt.setBytes(1, member.getMemberImg());
			pstmt.setInt(2,member.getMemberId());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
	}
	

	@Override
	public void delete(Integer memberId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, memberId);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	@Override 
	public Member findOneRegister(String memberAccount,String memberPaswd) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		Member member = new Member();
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_ONE_REGISTER);
			pstmt.setString(1, memberAccount);
			pstmt.setString(2, memberPaswd);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				member.setMemberId(rs.getInt("member_Id"));
				member.setMemberAccount(rs.getString("member_Account"));
				member.setMemberPassword(rs.getString("member_Password"));
				member.setMemberName(rs.getString("member_Name"));
				member.setMemberPhone(rs.getString("member_Phone"));
				member.setMemberEmail(rs.getString("member_Email"));
				member.setMemberRegisterDatetime(rs.getDate("member_Register_Datetime"));
				member.setMemberImg(rs.getBytes("member_Img"));		
			}		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return member;
	}

	//用來判斷使用者輸入的email是否已存在資料庫
	public boolean  findUkEmail(Member member) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean emailExists=false;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CEHCK_UK_EMAIL);
			pstmt.setString(1, member.getMemberEmail());
			rs=pstmt.executeQuery();
			
			 if (rs.next()) {
	                int count = rs.getInt(1);
	         emailExists = count > 0;
	            }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return emailExists;
	}
	
	
	
	
	
	
	@Override
	public List<Member> getAll() {
		List<Member> list = new ArrayList<Member>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_ALL_MEMBER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getInt("member_Id"));
				member.setMemberAccount(rs.getString("member_Account"));
				member.setMemberPassword(rs.getString("member_Password"));
				member.setMemberName(rs.getString("member_Name"));
				member.setMemberPhone(rs.getString("member_Phone"));
				member.setMemberEmail(rs.getString("member_Email"));
				member.setMemberRegisterDatetime(rs.getDate("member_Register_Datetime"));
				member.setMemberImg(rs.getBytes("member_Img"));
				member.setIsAdmin(rs.getBoolean("is_admin"));
				list.add(member); //把每一筆資料包裝成emberVO再丟進去list集合裡面
			}

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
	
	public static void main(String[] args) {
//		 INSERT_INTO測試
//		Member member = new Member("test3332", "GEGEG", "林可欣", "0912551222", "l22@g2i");
//		MemberJDBCDAO memberJDBCDAO = new MemberJDBCDAO();
//		memberJDBCDAO.insert(member);

//		 UPDATE測試
//		Member member = new Member("updatetest", "test", "呂威", "0911111292", "09822@gamii");
//		member.setMemberId(2);
//		MemberJDBCDAO memberJDBCDAO = new MemberJDBCDAO();
//		memberJDBCDAO.update(member);
//		
		//UPDATE_MEMBER_PASSWD測試
//		Member member=new Member();
//		member.setMemberId(11);
//		member.setMemberPassword("050505");
//		MemberJDBCDAO memberJDBCDAO = new MemberJDBCDAO();
//		memberJDBCDAO.update_passwd(member);

		// DELETE測試
//		MemberJDBCDAO memberJDBCDAO = new MemberJDBCDAO();
//		memberJDBCDAO.delete(6);
		
		//FIND_ONE_MEMBER
//		MemberJDBCDAO memberJDBCDAO = new MemberJDBCDAO();
//		Member member=memberJDBCDAO.findOneMember(1);
//		System.out.println("memberId= "+member.getMemberId());
//		System.out.println("memberAccount=  "+member.getMemberAccount());
//		System.out.println("memberPassword= "+member.getMemberPassword());
//		System.out.println("memberName= "+member.getMemberName());
//		System.out.println("memberPhone= "+member.getMemberPhone());
//		System.out.println("memberEmail= "+member.getMemberEmail());
//		System.out.println("memberRegister_datetime= "+member.getMemberRegisterDatetime());
//		System.out.println("membermgId= "+member.getMemberImg());
//		System.out.println("isAdmin= "+member.getIsAdmin());
//		
		//FIND_ALL_MEMBER
//		MemberJDBCDAO memberJDBCDAO = new MemberJDBCDAO();
//		List<Member> list = memberJDBCDAO.getAll();
//		for(Member mev:list) {
//			System.out.print(mev.getMemberId()+",");
//			System.out.print(mev.getMemberAccount()+",");
//			System.out.print(mev.getMemberName()+",");
//			System.out.print(mev.getMemberPhone()+",");
//			System.out.print(mev.getMemberEmail()+",");
//			System.out.print(mev.getMemberRegisterDatetime()+",");
//			System.out.print(mev.getMemberImg()+",");
//			System.out.print(mev.getIsAdmin()+",");
//			System.out.println();
//		}
//		
		
		
		//UPDATE_IMG測試
		//傳入跟更新會員大頭照
//		MemberJDBCDAO memberJDBCDAO = new MemberJDBCDAO();
//		Member member=new Member();
//		try {
//			FileInputStream fis = new FileInputStream("images/FC_Barcelona.png");
//			member.setMemberImg(fis.readAllBytes());
//			member.setMemberId(3);
//			memberJDBCDAO.updateImg(member);
//			fis.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}


	
}


