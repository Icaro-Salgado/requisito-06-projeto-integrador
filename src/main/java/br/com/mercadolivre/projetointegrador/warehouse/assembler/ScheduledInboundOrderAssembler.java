package br.com.mercadolivre.projetointegrador.warehouse.assembler;

import br.com.mercadolivre.projetointegrador.warehouse.controller.ScheduledInboundOrderController;
import br.com.mercadolivre.projetointegrador.warehouse.dto.request.ScheduledInboundOrderDTO;
import br.com.mercadolivre.projetointegrador.warehouse.dto.response.ResponseScheduledInboundOrderDTO;
import br.com.mercadolivre.projetointegrador.warehouse.mapper.ScheduledInboundOrderMapper;
import br.com.mercadolivre.projetointegrador.warehouse.model.ScheduledInboundOrder;
import br.com.mercadolivre.projetointegrador.warehouse.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class ScheduledInboundOrderAssembler {

    private final ScheduledInboundOrderMapper scheduledInboundOrderMapper;

    public ResponseEntity<ResponseScheduledInboundOrderDTO> toResponse(
            ScheduledInboundOrder entity, HttpStatus status, HttpHeaders headers) {
        ResponseScheduledInboundOrderDTO dto = scheduledInboundOrderMapper.toResponseDTO(entity);

        Links links =
                Links.of(
                        linkTo(methodOn(ScheduledInboundOrderController.class).findById(entity.getId()))
                                .withSelfRel());

        dto.setLinks(List.of(ResponseUtils.parseLinksToMap(links)));

        return new ResponseEntity<>(dto, headers, status);
    }
}
