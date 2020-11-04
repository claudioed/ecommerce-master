package tech.claudioed.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import tech.claudioed.domain.Invoice;

@ApplicationScoped
public class InvoiceRepository implements PanacheRepository<Invoice> { }
