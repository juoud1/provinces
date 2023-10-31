package com.dobatii.dockerization1.hateoassupport;

import java.util.Objects;

import org.springframework.http.server.PathContainer;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.util.annotation.Nullable;
import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.DEFAULT_PATH;
import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.EMPTY_STRING;

public interface ProvinceHateoasSupport {
	default UriComponentsBuilder getUriComponentBuilder(@Nullable ServerWebExchange exchange) {
		if (Objects.isNull(exchange)) {
			return UriComponentsBuilder.fromPath(DEFAULT_PATH);
		}
		
		var request = exchange.getRequest();
		PathContainer contextPath = request.getPath().contextPath();
		return UriComponentsBuilder.fromHttpRequest(request)
				.replacePath(contextPath.toString())
				.replaceQuery(EMPTY_STRING);
	}
	
	
}
