package br.com.mercadolivre.projetointegrador.warehouse.dto.response;

import br.com.mercadolivre.projetointegrador.warehouse.model.Product;
import br.com.mercadolivre.projetointegrador.warehouse.model.Section;
import br.com.mercadolivre.projetointegrador.warehouse.view.ScheduledInboundOrderView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseScheduledInboundOrderDTO {
    @NotNull
    private Long id;

    @NotNull private Section section;

    @NotNull private Product product;
    @NotNull private Integer quantity;
    @NotNull private LocalDate inboundDate;

    @JsonView(ScheduledInboundOrderView.created.class)
    private List<Map<String, String>> links;
}
