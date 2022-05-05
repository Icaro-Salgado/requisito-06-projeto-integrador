package br.com.mercadolivre.projetointegrador.warehouse.dto.request;

import br.com.mercadolivre.projetointegrador.warehouse.model.ScheduledInboundOrder;
import br.com.mercadolivre.projetointegrador.warehouse.view.ScheduledInboundOrderView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ScheduledInboundOrderDTO {
    @JsonView(ScheduledInboundOrderView.update.class)
    @NotNull private Long id;

    @NotNull private Long section;

    @NotNull private Long product;
    @NotNull private Integer quantity;
    @NotNull private LocalDate inboundDate;
}
