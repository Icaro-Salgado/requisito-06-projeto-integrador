package br.com.mercadolivre.projetointegrador.warehouse.repository;

import br.com.mercadolivre.projetointegrador.warehouse.model.ScheduledInboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduledInboundOrderRepository extends JpaRepository<ScheduledInboundOrder, Long> {

    List<ScheduledInboundOrder> findAllByInboundDate(LocalDate date);
}
