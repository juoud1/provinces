package com.dobatii.dockerization1.model;

import java.time.ZonedDateTime;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Member model representation transfert for any external exchange
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
public class MemberModel extends RepresentationModel<MemberModel> {
	private String memberFirstName;
	private String memberLastName;
	private String memberEmail;
	private String memberPhoneNumber;
	private String memberUsername;
	private String memberPassword;
	private String memberRole;
	private String memberStatus;
	private ZonedDateTime memberCreated;
	private String memberCreatedBy;
	private ZonedDateTime memberLastUpdated;
	private String memberLastUpdatedBy;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(memberEmail, memberFirstName, memberLastName, memberPassword,
				memberPhoneNumber, memberRole, memberStatus, memberUsername);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberModel other = (MemberModel) obj;
		return Objects.equals(memberEmail, other.memberEmail) && Objects.equals(memberFirstName, other.memberFirstName)
				&& Objects.equals(memberLastName, other.memberLastName)
				&& Objects.equals(memberPassword, other.memberPassword)
				&& Objects.equals(memberPhoneNumber, other.memberPhoneNumber)
				&& Objects.equals(memberRole, other.memberRole) && Objects.equals(memberStatus, other.memberStatus)
				&& Objects.equals(memberUsername, other.memberUsername);
	}

}
