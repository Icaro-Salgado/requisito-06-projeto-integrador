package br.com.mercadolivre.projetointegrador.warehouse.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ScheduledInboundOrderDTO {
    @NotNull private Long section;

    @NotNull private Long product;
    @NotNull private Integer quantity;
    @NotNull private LocalDate inboundDate;
}
