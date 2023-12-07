package com.dobatii.dockerization1.hateoassupport;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.dobatii.dockerization1.data.entity.Member;
import com.dobatii.dockerization1.model.MemberModel;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * HATEAO Assembler component for member resource
 * 
 * @author juoud1
 * @version 1.0
 * @date 24-11-2023
 * 
 */

@Component
@Slf4j
public class MemberRepresentationModelAssembler
		implements ReactiveRepresentationModelAssembler<Member, MemberModel>, ProvinceHateoasSupport {

	private static String serverUri = Strings.EMPTY;

	@Override
	public Mono<MemberModel> toModel(Member entity, ServerWebExchange exchange) {

		return null;
	}

	public MemberModel entityToModel(Member entity, ServerWebExchange exchange) {
		MemberModel memberResource = MemberModel.builder().build();
		if (Objects.isNull(memberResource)) {
			return memberResource;
		}
		return null;
	}

	public Flux<MemberModel> entitiesToListModels(Flux<Member> entities, ServerWebExchange exchange) {

		if (Objects.isNull(entities)) {
			return Flux.empty();
		}
		log.info("All members models representation of Canada in processing ...".toUpperCase());

		return Flux.from(entities.map(e -> entityToModel(e, exchange)));
	}

	private String getServerUri(@Nullable ServerWebExchange exchange) {

		if (Strings.isBlank(serverUri)) {
			serverUri = getUriComponentBuilder(exchange).toString();
		}
		return serverUri;
	}
}
