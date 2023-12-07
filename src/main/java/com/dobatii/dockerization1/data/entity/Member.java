package com.dobatii.dockerization1.data.entity;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
 * @version 1.1
 * @date 04-12-2023
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

	@NotBlank(message = "required.memberfirstname.not.blank")
	@Size(min = 3, max = 50, message = "required.memberfirstname.size")
	private String memberFirstName;

	@NotBlank(message = "required.memberlastName.not.blank")
	@Size(min = 3, max = 50, message = "required.memberlastName.size")
	private String memberLastName;

	@Email(message = "required.memberemail.email")
	private String memberEmail;

	private String memberPhoneNumber;

	@NotBlank(message = "required.memberusername.not.blank")
	@Size(min = 3, max = 20, message = "required.memberusername.size")
	private String memberUsername;

	@NotBlank(message = "required.memberpassword.not.blank")
	@Size(min = 6, max = 12, message = "required.memberpassword.size")
	private String memberPassword;

	@NotBlank(message = "required.memberrole.not.blank")
	@Size(min = 3, max = 20, message = "required.memberrole.size")
	private String memberRole;

	@NotNull(message = "required.memberstatus.not.null")
	@Size(min = 4, max = 15, message = "required.memberstatus.size")
	private String memberStatus; // accountLocked, disabled, creadentialsExpired, ... À PRENDRE EN COMPTE

	private ZonedDateTime memberCreated;
	private String memberCreatedBy;
	private ZonedDateTime memberLastUpdated;
	private String memberLastUpdatedBy;

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
