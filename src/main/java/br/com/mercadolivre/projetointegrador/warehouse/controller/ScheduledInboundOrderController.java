package br.com.mercadolivre.projetointegrador.warehouse.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/warehouse/scheduled-inbound-order")
@RequiredArgsConstructor
@Tag(name = "[Warehouse] - Scheduled Inbound Order")
public class ScheduledInboundOrderController {
}
