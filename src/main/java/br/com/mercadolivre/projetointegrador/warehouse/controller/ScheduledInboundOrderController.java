package br.com.mercadolivre.projetointegrador.warehouse.controller;

import br.com.mercadolivre.projetointegrador.warehouse.assembler.ScheduledInboundOrderAssembler;
import br.com.mercadolivre.projetointegrador.warehouse.dto.request.ScheduledInboundOrderDTO;
import br.com.mercadolivre.projetointegrador.warehouse.dto.response.ResponseScheduledInboundOrderDTO;
import br.com.mercadolivre.projetointegrador.warehouse.mapper.ScheduledInboundOrderMapper;
import br.com.mercadolivre.projetointegrador.warehouse.model.ScheduledInboundOrder;
import br.com.mercadolivre.projetointegrador.warehouse.service.ProductService;
import br.com.mercadolivre.projetointegrador.warehouse.service.ScheduledInboundOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/warehouse/scheduled-inbound-order")
@RequiredArgsConstructor
@Tag(name = "[Warehouse] - Scheduled Inbound Order")
public class ScheduledInboundOrderController {

    private final ScheduledInboundOrderService scheduledInboundOrderService;
    private final ScheduledInboundOrderMapper scheduledInboundOrderMapper;
    private final ScheduledInboundOrderAssembler scheduledInboundOrderAssembler;

    // REMOVA
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseScheduledInboundOrderDTO> createScheculedInboundOrder(@RequestBody @Valid ScheduledInboundOrderDTO dto) {

        ScheduledInboundOrder model = scheduledInboundOrderMapper.toModel(dto);

        ScheduledInboundOrder saved = scheduledInboundOrderService.addScheduledInboundOrder(model);

        return scheduledInboundOrderAssembler.toResponse(saved, HttpStatus.CREATED, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseScheduledInboundOrderDTO> findById(@PathVariable Long id) {
        ScheduledInboundOrder response = scheduledInboundOrderService.findById(id);

        return scheduledInboundOrderAssembler.toResponse(response, HttpStatus.OK, null);
    }
}
