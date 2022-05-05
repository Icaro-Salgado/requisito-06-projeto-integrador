package br.com.mercadolivre.projetointegrador.warehouse.service.validators;

import br.com.mercadolivre.projetointegrador.warehouse.repository.BatchRepository;
import br.com.mercadolivre.projetointegrador.warehouse.model.InboundOrder;
import br.com.mercadolivre.projetointegrador.warehouse.repository.SectionRepository;
import br.com.mercadolivre.projetointegrador.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarehouseValidatorExecutor {

  @Autowired private SectionRepository sectionRepository;

  @Autowired private WarehouseRepository warehouseRepository;

  @Autowired private BatchRepository batchRepository;

  public void executeValidators(InboundOrder inboundOrder) {
    List<ModelValidator> validators = buildValidators(inboundOrder);

    validators.forEach(ModelValidator::Validate);
  }

  public void executeValidators(
      InboundOrder inboundOrder, List<ModelValidator> additionalValidators) {
    List<ModelValidator> validators = new java.util.ArrayList<>(buildValidators(inboundOrder));
    validators.addAll(additionalValidators);

    validators.forEach(ModelValidator::Validate);
  }

  private List<ModelValidator> buildValidators(InboundOrder inboundOrder) {
    return List.of(
        new SectionExistsValidator(inboundOrder.getSectionCode(), sectionRepository),
        new WarehouseExistsValidator(inboundOrder.getWarehouseCode(), warehouseRepository),
        new SectionCapacityValidator(inboundOrder, sectionRepository, batchRepository),
        new SectionAndProductMatchValidator(inboundOrder, sectionRepository),
        new SectionManagerIdValidator(inboundOrder, sectionRepository));
  }
}
