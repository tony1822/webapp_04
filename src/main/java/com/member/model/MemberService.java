package com.member.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MemberService {

	private Member_interface dao=null;
	
	public MemberService() {
		dao=new MemberJDBCDAO();
	}
	
	
	public Member addMem(String memberName,String memberEmail,String memberPhone
			,String memberAccount,String memberPassword) {
		Member member =new Member();
		member.setMemberName(memberName);
		member.setMemberEmail(memberEmail);
		member.setMemberPhone(memberPhone);
		member.setMemberAccount(memberAccount);
		member.setMemberPassword(memberPassword);
		dao.insert(member);
		return member;
	}
	
	public Member updatePasw(String memberPassword) {
		Member member =new Member();
		member.setMemberPassword(memberPassword);
		dao.update_passwd(member);
		return member;
	}
	
	public Member update(String memberName,String memberAccount,String memberEmail,String memberPhone
			,String memberPassword,Integer memberId) {
		Member member =new Member();
		member.setMemberName(memberName);
		member.setMemberAccount(memberAccount);
		member.setMemberEmail(memberEmail);
		member.setMemberPhone(memberPhone);
		member.setMemberPassword(memberPassword);
		member.setMemberId(memberId);
		dao.update(member);
		return member;
	}
	
	public void deleteMem(Integer memberId) {
		dao.delete(memberId);
	}
	
	
	public Member findOneRegister(String memberAccount,String memberPaswd) {
		return dao.findOneRegister(memberAccount,memberPaswd);
	}
	
	public Member findOneMem(Integer memId) {
		return dao.findOneMember(memId);
	}
	
	
	
	public Member updateImg(Member member ) {
		dao.updateImg(member);
		return member;
	}
	
	//用來判斷使用者輸入的email是否已存在資料庫
	public boolean findUkEmail(String memberEmail) {
		Member member =new Member();
		member.setMemberEmail(memberEmail);
		boolean is=dao.findUkEmail(member);
		return is;
	}
	
	public List<Member>getAll(){
		return dao.getAll();
	}
}
