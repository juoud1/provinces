package com.dobatii.dockerization1.model;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Province model representation transfert for any external exchange
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
public class ProvinceModel extends RepresentationModel<ProvinceModel> {
	@NotNull(message = "required.provincename.not.null")
	@Size(min = 3, max = 50, message = "required.provincename.size")
	private String provinceName;

	@NotBlank(message = "required.provincecode.not.blank")
	@Size(min = 2, max = 3, message = "required.provincecode.size")
	private String provinceCode;

	private ZonedDateTime provinceCreated;
	private String provinceCreatedBy;
	private ZonedDateTime provinceLastUpdated;
	private String provinceLastUpdatedBy;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(provinceCode, provinceName);
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
		ProvinceModel other = (ProvinceModel) obj;
		return Objects.equals(provinceCode, other.provinceCode) && Objects.equals(provinceName, other.provinceName);
	}
}
