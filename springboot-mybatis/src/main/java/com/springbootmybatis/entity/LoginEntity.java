package com.springbootmybatis.entity;

public class LoginEntity {
	private String loginId;
	private String userPwd;
	private String validateCode;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	@Override
	public String toString() {
		return "LoginEntity [loginId=" + loginId + ", userPwd=" + userPwd + ", validateCode=" + validateCode + "]";
	}
	
	
}
