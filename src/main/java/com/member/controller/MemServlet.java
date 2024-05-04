package com.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.member.model.Member;
import com.member.model.MemberService;

@MultipartConfig
public class MemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("From_Register".equals(action)) {// 來自register
			Map<String, String> erros = new <String, String>HashMap();
			req.setAttribute("erros", erros);
			String mename = req.getParameter("mename");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (mename == null || mename.trim().length() == 0) {
				erros.put("emptyMename", "會員名稱請勿空白!");
			} else if (!mename.trim().matches(enameReg)) {
				erros.put("invalidMename", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String email = req.getParameter("email");
			if (email == null || email.trim().length() == 0) {
				erros.put("emptyEmail", "會員信箱請勿空白!");
			} else {
				MemberService memSer = new MemberService();
				boolean isUK = memSer.findUkEmail(email);
				if (isUK) {
					erros.put("ukEmail", "會員信箱已使用請重新輸入!");
				}
			}

			String phoneReg = "^09[0-9]{8}$";
			String phone = req.getParameter("phone");
			if (phone == null || phone.trim().length() == 0) {
				erros.put("emptyPhone", "會員電話請勿空白!");
			} else if (!phone.trim().matches(phoneReg)) {
				erros.put("invalidPhone", "電話開頭請為0和9後面為8位數字");
			}

			String account = req.getParameter("account");
			if (account == null || account.trim().length() == 0) {
				erros.put("emptyAccount", "會員帳號請勿空白!");
			}

			String paswd = req.getParameter("paswd");
			if (paswd == null || paswd.trim().length() == 0) {
				erros.put("emptyPaswd", "會員密碼請勿空白!!");
			}

			String paswdc = req.getParameter("paswdc");
			if (paswdc == null || paswdc.trim().length() == 0) {
				erros.put("emptyPaswdc", "確認密碼請勿空白!");
			} else if (!paswd.equals(paswdc)) {
				erros.put("wrongPaswdc", "確認密碼與密碼不同，請重新輸入!");
			}

			Member member = new Member();
			member.setMemberName(mename);
			member.setMemberEmail(email);
			member.setMemberAccount(account);
			member.setMemberPhone(phone);
			member.setMemberPassword(paswd);
			member.setMemberImg(IOUtils.toByteArray(req.getPart("").getInputStream()));
			if (!erros.isEmpty()) {
				req.setAttribute("member", member);
				RequestDispatcher failureView = req.getRequestDispatcher("memRegis.jsp");
				failureView.forward(req, res);
				return;
			}

			// 呼叫service把註冊資料傳入資料庫
			MemberService memSer = new MemberService();
			memSer.addMem(mename, email, phone, account, paswd);
			String url = "memLogIn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		// 來自登入的請求
		if ("Log_In".equals(action)) {
			Map<String, String> erros = new <String, String>HashMap();
			req.setAttribute("erros", erros);
			String account = req.getParameter("account");
			if (account == null || account.trim().length() == 0) {
				erros.put("emptyAccount", "會員帳號請勿空白");
			}
			String paswd = req.getParameter("paswd");
			if (paswd == null || paswd.trim().length() == 0) {
				erros.put("emptyPaswd", "會員密碼請勿空白");
			}

			if (!erros.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("memLogIn.jsp");
				failureView.forward(req, res);
				return;
			}

			// 去資料庫撈資料比對帳號碼是否有此用戶
			MemberService memSer = new MemberService();
			Member stMember = memSer.findOneRegister(account, paswd);
			if (stMember.getMemberAccount() == null || stMember.getMemberPassword() == null) {
				erros.put("wrongMem", "帳號或密碼錯誤");
			}

			if (!erros.isEmpty()) {
				String url = "memLogIn.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			// 導入到會員資料頁面
			req.setAttribute("member", stMember);
			String url = "memProfile.jsp";
			RequestDispatcher failureView = req.getRequestDispatcher(url);
			failureView.forward(req, res);
		}
		///////////////////////////////////////////
		// 來自會員中心修改資料的請求，導向到修改資料頁面
		if ("Get_One_Update".equals(action)) {
			String str = req.getParameter("memId");
			Integer memId = Integer.valueOf(str);
			MemberService memSer = new MemberService();
			Member stMember = memSer.findOneMem(memId);
			req.setAttribute("member", stMember);
			String url = "memUpdate.jsp";
			RequestDispatcher failureView = req.getRequestDispatcher(url);
			failureView.forward(req, res);
		}

		///////////////////////////////////////////
		// 來自修改資料傳送過來的數據，先在此驗證檢查，成功才導向
		if ("Mem_Update".equals(action)) {
			Map<String, String> erros = new <String, String>HashMap();
			req.setAttribute("erros", erros);
			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
			Date memDate = Date.valueOf(req.getParameter("memDate"));

			String membername = req.getParameter("mename");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (membername == null || membername.trim().length() == 0) {
				erros.put("emptyMename", "會員名稱請勿空白!");
			} else if (!membername.trim().matches(enameReg)) {
				erros.put("invalidMename", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			String account = req.getParameter("account");
			if (account == null || account.trim().length() == 0) {
				erros.put("emptyAccount", "會員帳號請勿空白!");
			}
			String email = req.getParameter("email");
			if (email == null || email.trim().length() == 0) {
				erros.put("emptyEmail", "會員信箱請勿空白!");
			}
//			else {
//				MemberService memSer = new MemberService();
//				boolean isUK = memSer.findUkEmail(email);
//				if (isUK) {
//					erros.put("ukEmail", "會員信箱已使用請重新輸入!");
//				}
//			}

			String phoneReg = "^09[0-9]{8}$";
			String phone = req.getParameter("phone");
			if (phone == null || phone.trim().length() == 0) {
				erros.put("emptyPhone", "會員電話請勿空白!");
			} else if (!phone.trim().matches(phoneReg)) {
				erros.put("invalidPhone", "電話開頭請為0和9後面為8位數字");
			}

			String paswd = req.getParameter("paswd");
			if (paswd == null || paswd.trim().length() == 0) {
				erros.put("emptyPaswd", "會員密碼請勿空白!!");
			}

			String paswdc = req.getParameter("paswdc");
			if (paswdc == null || paswdc.trim().length() == 0) {
				erros.put("emptyPaswdc", "確認密碼請勿空白!");
			} else if (!paswd.equals(paswdc)) {
				erros.put("wrongPaswdc", "確認密碼與密碼不同，請重新輸入!");
			}

			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberName(membername);
			member.setMemberEmail(email);
			member.setMemberAccount(account);
			member.setMemberPhone(phone);
			member.setMemberPassword(paswd);
			member.setMemberRegisterDatetime(memDate);

			if (!erros.isEmpty()) {
				req.setAttribute("member", member);
				RequestDispatcher failureView = req.getRequestDispatcher("memUpdate.jsp");
				failureView.forward(req, res);
				return;
			}

			MemberService memSer = new MemberService();
			member = memSer.update(membername, account, email, phone, paswd, memberId);
			member.setMemberRegisterDatetime(memDate);
			req.setAttribute("member", member);
			String url = "memProfile.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		///////////////////////////////////////////////
		// 來自listall的刪除
		if ("delete".equals(action)) {

			Integer memberId = Integer.valueOf(req.getParameter("memberId"));

			MemberService memSer = new MemberService();
			memSer.deleteMem(memberId);

			String url = "listAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		/////////////////////////
		////上傳圖片到資料庫還未完成
		if ("Update_Image".equals(action)) {
			Member member = new Member();
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			byte[] images = IOUtils.toByteArray(req.getPart("images").getInputStream());
			member.setMemberId(memberId);
			member.setMemberImg(images);
			MemberService memSer = new MemberService();
			memSer.updateImg(member);
			String url = "#";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		

	}
}
