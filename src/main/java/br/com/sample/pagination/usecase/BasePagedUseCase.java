package br.com.sample.pagination.usecase;

import br.com.sample.pagination.entity.Meta;
import br.com.sample.pagination.entity.Response;
import br.com.sample.pagination.entity.ResponseLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public abstract class BasePagedUseCase<T extends Serializable>  {

    private static final String HTTPS = "https";
    private static final String GATEWAY_HOST = "www.api.bradesco.com";
    private static final String GATEWAY_PORT = "8443";

    private PagedResourcesAssembler<T> pagedResourcesAssembler;

    @Autowired
    public void setPagedResourcesAssembler(final PagedResourcesAssembler<T> pagedResourcesAssembler) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    public Response<T> execute(final List<T> pagedCollection, final int page, final int size, final int countCollection) {
        final Pageable pageable = PageRequest.of(page - 1, size);

        final Page<T> pagedResult = new PageImpl<>(pagedCollection, pageable, countCollection);

        return Response.<T>builder()
                .data(pagedResult.getContent())
                .links(buildResponseLinks(pagedResult))
                .meta(buildResponseMeta(pagedResult))
                .build();
    }

    private ResponseLinks buildResponseLinks(final Page<T> pagedCollection) {
        final PagedModel<EntityModel<T>> pagedModel = pagedResourcesAssembler.toModel(pagedCollection);
        return ResponseLinks.builder()
                .self(getLink(pagedModel.getLink(IanaLinkRelations.SELF)))
                .first(getLink(pagedModel.getLink(IanaLinkRelations.FIRST)))
                .next(getLink(pagedModel.getNextLink()))
                .prev(getLink(pagedModel.getPreviousLink()))
                .last(getLink(pagedModel.getLink(IanaLinkRelations.LAST)))
                .build();
    }

    private String getLink(final Optional<Link> link) {
        String returnLink = link.map(Link::getHref).orElse("");
        if (link.isPresent()) {
            final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(link.get().getHref());
            returnLink = builder.scheme(HTTPS).host(GATEWAY_HOST).port(GATEWAY_PORT).build().toUriString();
        }
        return returnLink;
    }

    private Meta buildResponseMeta(final Page<T> pagedCollection) {
        return Meta.builder()
                .totalRecords(pagedCollection.getTotalElements())
                .totalPages(pagedCollection.getTotalPages())
                .requestDateTime(ZonedDateTime.now().toString())
                .build();
    }
}