package com.member.model;

import java.util.List;

public interface Member_interface {
	public void insert(Member member);
    public void update_passwd(Member member);
    public void updateImg(Member member);
    public void delete(Integer member);
    //findOneRegister用再登入註冊時查詢是否有註冊
    public Member findOneRegister(String memberAccount,String memberPaswd);
    //findOneMember用再要導入更改資料畫面
    public Member findOneMember(Integer member);
    public boolean  findUkEmail(Member member);
    public List<Member> getAll();	
    public void update(Member member);
}
