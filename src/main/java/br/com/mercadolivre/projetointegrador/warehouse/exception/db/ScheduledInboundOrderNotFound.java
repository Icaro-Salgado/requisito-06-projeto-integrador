package br.com.mercadolivre.projetointegrador.warehouse.exception.db;

public class ScheduledInboundOrderNotFound extends RuntimeException{
    public ScheduledInboundOrderNotFound(String msg) {
        super(msg);
    }
}
