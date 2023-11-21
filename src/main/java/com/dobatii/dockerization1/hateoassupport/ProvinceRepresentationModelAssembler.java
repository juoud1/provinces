package com.dobatii.dockerization1.hateoassupport;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.dobatii.dockerization1.data.entity.Province;
import com.dobatii.dockerization1.model.ProvinceModel;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ProvinceRepresentationModelAssembler
		implements ReactiveRepresentationModelAssembler<Province, ProvinceModel>, ProvinceHateoasSupport {

	private static String serverUri = Strings.EMPTY;

	@Override
	public Mono<ProvinceModel> toModel(Province entity, ServerWebExchange exchange) {

		return Mono.just(entityToModel(entity, exchange));
	}

	public ProvinceModel entityToModel(Province entity, ServerWebExchange exchange) {
		ProvinceModel provinceResource = ProvinceModel.builder().build();
		if (Objects.isNull(entity)) {
			return provinceResource;
		}
		log.info("province and/or territory model representation in processing ...".toUpperCase());
		BeanUtils.copyProperties(entity, provinceResource);
		var serverUri = getServerUri(exchange);

		provinceResource.add(Link.of(String.format("%s/olibillapi/v1/provinces", serverUri)).withRel("provinces"));

		provinceResource.add(Link
				.of(String.format("%s/olibillapi/v1/provinces/%s", serverUri, entity.getProvinceCode())).withSelfRel());

		return provinceResource;
	}

	public Flux<ProvinceModel> entitiesToListModels(Flux<Province> entities, ServerWebExchange exchange) {

		if (Objects.isNull(entities)) {
			return Flux.empty();
		}
		log.info("All provinces and/or territories models representation of Canada in processing ...".toUpperCase());

		return Flux.from(entities.map(e -> entityToModel(e, exchange)));
	}

	private String getServerUri(@Nullable ServerWebExchange exchange) {
		if (Strings.isBlank(serverUri)) {
			serverUri = getUriComponentBuilder(exchange).toUriString();
		}
		return serverUri;
	}

}
