package com.dobatii.dockerization1.data.security;

/**
 * 
 * Creadential's data information
 * 
 * @author juoud1
 * @version 1.0
 * @date 16-11-2023
 * 
 */

public class AccountCredential {
	private String memberUsername;
	private String memberPassword;

	public String getMemberUsername() {
		return memberUsername;
	}

	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

}
