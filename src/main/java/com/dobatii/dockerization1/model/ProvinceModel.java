package com.dobatii.dockerization1.model;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ProvinceModel extends RepresentationModel<ProvinceModel> {
	private String provinceName;
	private String provinceCode;

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
