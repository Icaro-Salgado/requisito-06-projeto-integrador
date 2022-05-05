package br.com.mercadolivre.projetointegrador.warehouse.controller;

import br.com.mercadolivre.projetointegrador.warehouse.assembler.ScheduledInboundOrderAssembler;
import br.com.mercadolivre.projetointegrador.warehouse.dto.request.ScheduledInboundOrderDTO;
import br.com.mercadolivre.projetointegrador.warehouse.dto.response.ResponseScheduledInboundOrderDTO;
import br.com.mercadolivre.projetointegrador.warehouse.mapper.ScheduledInboundOrderMapper;
import br.com.mercadolivre.projetointegrador.warehouse.model.ScheduledInboundOrder;
import br.com.mercadolivre.projetointegrador.warehouse.service.ProductService;
import br.com.mercadolivre.projetointegrador.warehouse.service.ScheduledInboundOrderService;
import br.com.mercadolivre.projetointegrador.warehouse.view.ScheduledInboundOrderView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse/scheduled-inbound-order")
@RequiredArgsConstructor
@Tag(name = "[Warehouse] - Scheduled Inbound Order")
public class ScheduledInboundOrderController {

    private final ScheduledInboundOrderService scheduledInboundOrderService;
    private final ScheduledInboundOrderMapper scheduledInboundOrderMapper;
    private final ScheduledInboundOrderAssembler scheduledInboundOrderAssembler;

    @PostMapping
    public ResponseEntity<ResponseScheduledInboundOrderDTO> createScheduledInboundOrder(@RequestBody @Valid ScheduledInboundOrderDTO dto) {

        ScheduledInboundOrder model = scheduledInboundOrderMapper.toModel(dto);

        ScheduledInboundOrder saved = scheduledInboundOrderService.addScheduledInboundOrder(model);

        return scheduledInboundOrderAssembler.toResponse(saved, HttpStatus.CREATED, null);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseScheduledInboundOrderDTO> findById(@PathVariable Long id) {
        ScheduledInboundOrder response = scheduledInboundOrderService.findById(id);

        return scheduledInboundOrderAssembler.toResponse(response, HttpStatus.OK, null);
    }

    @GetMapping
    public ResponseEntity<List<ResponseScheduledInboundOrderDTO>> findAll(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<ScheduledInboundOrder> response = new ArrayList<>();

        if (date != null) {
            response = scheduledInboundOrderService.findAllByDate(date);
        }
        else {
            response = scheduledInboundOrderService.findAll();
        }

        return scheduledInboundOrderAssembler.toResponse(response, HttpStatus.OK, null);
    }

    @PutMapping
    @JsonView(ScheduledInboundOrderView.update.class)
    public ResponseEntity<ResponseScheduledInboundOrderDTO> update(@RequestBody @Valid ScheduledInboundOrderDTO dto) {

        ScheduledInboundOrder model = scheduledInboundOrderMapper.toModel(dto);
        model.setId(dto.getId());

        ScheduledInboundOrder saved = scheduledInboundOrderService.updateScheduledInboundOrder(model);

        return scheduledInboundOrderAssembler.toResponse(saved, HttpStatus.CREATED, null);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        scheduledInboundOrderService.deleteScheduledInboundOrder(id);

        return ResponseEntity.noContent().build();
    }
}
