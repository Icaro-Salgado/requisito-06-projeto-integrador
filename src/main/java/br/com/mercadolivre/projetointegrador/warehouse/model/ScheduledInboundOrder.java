package br.com.mercadolivre.projetointegrador.warehouse.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder
@Getter @Setter
@ToString @EqualsAndHashCode
public class ScheduledInboundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
     private Product product;

    @Column private Integer quantity;

    @Column private Integer section;

    @Column private LocalDate inboundDate;
}
