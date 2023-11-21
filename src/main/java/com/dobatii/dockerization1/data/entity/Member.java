package com.dobatii.dockerization1.data.entity;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * Member's data information
 * 
 * @author juoud1
 * @version 1.0
 * @date 05-11-2023
 * 
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Table("member")
public class Member {
	@Id
	private Long id;
	private String memberFirstName;
	private String memberLastName;
	private String memberEmail;
	private String memberPhoneNumber;
	private String memberUsername;
	private String memberPassword;
	private String memberRole;
	private String memberStatus; // accountLocked, disabled, creadentialsExpired, ... À PRENDRE EN COMPTE

	@Override
	public int hashCode() {
		return Objects.hash(id, memberEmail, memberFirstName, memberLastName, memberPassword, memberPhoneNumber,
				memberUsername);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(id, other.id) && Objects.equals(memberEmail, other.memberEmail)
				&& Objects.equals(memberFirstName, other.memberFirstName)
				&& Objects.equals(memberLastName, other.memberLastName)
				&& Objects.equals(memberPassword, other.memberPassword)
				&& Objects.equals(memberPhoneNumber, other.memberPhoneNumber)
				&& Objects.equals(memberUsername, other.memberUsername);
	}

}
