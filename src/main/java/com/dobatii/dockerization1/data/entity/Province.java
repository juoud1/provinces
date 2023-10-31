package com.dobatii.dockerization1.data.entity;

//import javax.persistence.Entity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.annotation.Id;
//import org.springframework.data.jpa.domain.AbstractPersistable;
//import org.springframework.data.relational.core.mapping.Table;
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
 * @version 1.1 2023-10-30
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
	private String provinceName;
	private String provinceCode;

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
