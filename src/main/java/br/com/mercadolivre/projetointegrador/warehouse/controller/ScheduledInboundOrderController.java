package br.com.mercadolivre.projetointegrador.warehouse.controller;

import br.com.mercadolivre.projetointegrador.warehouse.assembler.ScheduledInboundOrderAssembler;
import br.com.mercadolivre.projetointegrador.warehouse.dto.request.CreateWarehousePayloadDTO;
import br.com.mercadolivre.projetointegrador.warehouse.dto.request.ScheduledInboundOrderDTO;
import br.com.mercadolivre.projetointegrador.warehouse.dto.response.ResponseScheduledInboundOrderDTO;
import br.com.mercadolivre.projetointegrador.warehouse.exception.ErrorDTO;
import br.com.mercadolivre.projetointegrador.warehouse.mapper.ScheduledInboundOrderMapper;
import br.com.mercadolivre.projetointegrador.warehouse.model.ScheduledInboundOrder;
import br.com.mercadolivre.projetointegrador.warehouse.service.ProductService;
import br.com.mercadolivre.projetointegrador.warehouse.service.ScheduledInboundOrderService;
import br.com.mercadolivre.projetointegrador.warehouse.view.ScheduledInboundOrderView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "CRIA UM NOVO INBOUND ORDER AGENDADO", description = "Cria um novo inbound order agendado")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "ScheduledInboundOrder criado com sucesso",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseScheduledInboundOrderDTO.class))
                            }),
            })
    @PostMapping
    public ResponseEntity<ResponseScheduledInboundOrderDTO> createScheduledInboundOrder(@RequestBody @Valid ScheduledInboundOrderDTO dto) {

        ScheduledInboundOrder model = scheduledInboundOrderMapper.toModel(dto);

        ScheduledInboundOrder saved = scheduledInboundOrderService.addScheduledInboundOrder(model);

        return scheduledInboundOrderAssembler.toResponse(saved, HttpStatus.CREATED, null);
    }

    @Operation(summary = "LISTA O SCHEDULED INBOUND ORDER REQUISITADO", description = "Lista o Scheduled Inbound Order requisitado")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Encontrado",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseScheduledInboundOrderDTO.class))
                            }),
            })
    @GetMapping("{id}")
    public ResponseEntity<ResponseScheduledInboundOrderDTO> findById(@PathVariable Long id) {
        ScheduledInboundOrder response = scheduledInboundOrderService.findById(id);

        return scheduledInboundOrderAssembler.toResponse(response, HttpStatus.OK, null);
    }

    @Operation(summary = "LISTA TODOS SCHEDULED INBOUND ORDERS", description = "Lista todos Scheduled Inbound Order requisitados")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Encontrado",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseScheduledInboundOrderDTO.class))
                            }),
            })
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

    @Operation(summary = "ATUALIZA O SCHEDULED INBOUND ORDER REQUISITADO", description = "Atualiza o Scheduled Inbound Order requisitado")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Criado",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseScheduledInboundOrderDTO.class))
                            }),
            })
    @PutMapping("{id}")
    @JsonView(ScheduledInboundOrderView.update.class)
    public ResponseEntity<ResponseScheduledInboundOrderDTO> update(@RequestBody @Valid ScheduledInboundOrderDTO dto, @PathVariable Long id) {

        ScheduledInboundOrder model = scheduledInboundOrderMapper.toModel(dto);
        model.setId(id);

        ScheduledInboundOrder saved = scheduledInboundOrderService.updateScheduledInboundOrder(model);

        return scheduledInboundOrderAssembler.toResponse(saved, HttpStatus.CREATED, null);
    }

    @Operation(summary = "REMOVE O SCHEDULED INBOUND ORDER REQUISITADO", description = "Remove o Scheduled Inbound Order requisitado")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Deletado com sucesso",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = void.class))
                            }),
            })
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        scheduledInboundOrderService.deleteScheduledInboundOrder(id);

        return ResponseEntity.noContent().build();
    }
}
