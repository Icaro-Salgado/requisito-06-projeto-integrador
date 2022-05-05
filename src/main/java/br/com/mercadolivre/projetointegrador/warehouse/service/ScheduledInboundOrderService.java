package br.com.mercadolivre.projetointegrador.warehouse.service;

import br.com.mercadolivre.projetointegrador.warehouse.exception.db.ScheduledInboundOrderNotFound;
import br.com.mercadolivre.projetointegrador.warehouse.model.ScheduledInboundOrder;
import br.com.mercadolivre.projetointegrador.warehouse.repository.ScheduledInboundOrderRepository;
import br.com.mercadolivre.projetointegrador.warehouse.service.validators.SectionAndProductMatchValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduledInboundOrderService {

    private final ScheduledInboundOrderRepository scheduledInboundOrderRepository;


    public ScheduledInboundOrder addScheduledInboundOrder(ScheduledInboundOrder scheduledInboundOrder) {

        SectionAndProductMatchValidator.validate(scheduledInboundOrder.getProduct(), scheduledInboundOrder.getSection());

        return scheduledInboundOrderRepository.save(scheduledInboundOrder);
    }

    public ScheduledInboundOrder updateScheduledInboundOrder(ScheduledInboundOrder newScheduledInboundOrder) {

        Optional<ScheduledInboundOrder> foundedScheduledInboundOrder = scheduledInboundOrderRepository.findById(newScheduledInboundOrder.getId());

        if (foundedScheduledInboundOrder.isEmpty()) {
            throw new ScheduledInboundOrderNotFound("Ordem de serviço programada não encontrada");
        }

        SectionAndProductMatchValidator.validate(newScheduledInboundOrder.getProduct(), newScheduledInboundOrder.getSection());

        return scheduledInboundOrderRepository.save(newScheduledInboundOrder);
    }


    public void deleteScheduledInboundOrder(ScheduledInboundOrder toDelete) {
        try {
            scheduledInboundOrderRepository.delete(toDelete);
        } catch (IllegalArgumentException e) {
            throw new ScheduledInboundOrderNotFound("Ordem de serviço programada não encontrada");
        }
    }

    public List<ScheduledInboundOrder> findAllByDate(LocalDate date) {
        return scheduledInboundOrderRepository.findAllByInboundDate(date);
    }

    public ScheduledInboundOrder findById(Long id) {
        Optional<ScheduledInboundOrder> response = scheduledInboundOrderRepository.findById(id);

        if (response.isEmpty()) {
            throw new ScheduledInboundOrderNotFound("Ordem de serviço programada não encontrada");
        }

        return response.get();
    }
}
