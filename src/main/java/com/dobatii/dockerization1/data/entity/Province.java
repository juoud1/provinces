package com.dobatii.dockerization1.data.entity;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import javax.persistence.Entity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Province data
 * 
 * @author 9386-2142 Qc inc
 * @version 1.2 2023-12-04
 *
 * 
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Table("province")
public class Province {
	@Id
	private Long id;

	@NotNull(message = "required.provincename.not.null")
	@Size(min = 3, max = 50, message = "required.provincename.size")
	private String provinceName;

	@NotBlank(message = "required.provincecode.not.blank")
	@Size(min = 2, max = 3, message = "required.provincecode.size")
	private String provinceCode;

	private ZonedDateTime provinceCreated;

	@NotBlank(message = "required.provincecreatedby.not.blank")
	private String provinceCreatedBy;

	private ZonedDateTime provinceLastUpdated;

	@NotBlank(message = "required.provinceupdatedby.not.blank")
	private String provinceLastUpdatedBy;

	@Override
	public int hashCode() {
		return ObjectUtils.allNotNull(provinceCode) ? provinceCode.hashCode() : 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;

		if (!super.equals(object))
			return false;

		if (!(object instanceof Province))
			return false;

		Province other = (Province) object;
		if (provinceCode == null) {
			if (other.provinceCode != null)
				return false;
		} else if (!provinceCode.equals(other.provinceCode))
			return false;

		return true;
	}
}
