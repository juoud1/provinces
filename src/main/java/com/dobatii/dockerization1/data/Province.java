package com.dobatii.dockerization1.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Province data
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-05-09
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Province {
	private String provinceName;
	private String provinceCode;
}
