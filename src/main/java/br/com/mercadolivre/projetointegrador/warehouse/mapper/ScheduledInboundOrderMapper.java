package br.com.mercadolivre.projetointegrador.warehouse.mapper;

import br.com.mercadolivre.projetointegrador.warehouse.dto.request.ScheduledInboundOrderDTO;
import br.com.mercadolivre.projetointegrador.warehouse.dto.response.ResponseScheduledInboundOrderDTO;
import br.com.mercadolivre.projetointegrador.warehouse.model.ScheduledInboundOrder;
import br.com.mercadolivre.projetointegrador.warehouse.service.ProductService;
import br.com.mercadolivre.projetointegrador.warehouse.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledInboundOrderMapper {

    private final ProductService productService;
    private final SectionService sectionService;

    public ResponseScheduledInboundOrderDTO toResponseDTO(ScheduledInboundOrder model) {

        return ResponseScheduledInboundOrderDTO
                .builder()
                .id(model.getId())
                .section(model.getSection())
                .product(model.getProduct())
                .quantity(model.getQuantity())
                .inboundDate(model.getInboundDate())
                .build();
    };

    public  ScheduledInboundOrder toModel(ScheduledInboundOrderDTO dto) {
        return ScheduledInboundOrder
                .builder()
                .product(productService.findById(dto.getProduct()))
                .quantity(dto.getQuantity())
                .section(sectionService.findSectionById(dto.getSection()))
                .inboundDate(dto.getInboundDate())
                .build();
    }
}
