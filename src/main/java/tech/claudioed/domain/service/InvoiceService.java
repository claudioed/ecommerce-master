package tech.claudioed.domain.service;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import tech.claudioed.domain.Invoice;
import tech.claudioed.domain.repository.InvoiceRepository;
import tech.claudioed.domain.resources.data.InvoiceRequest;

@ApplicationScoped
public class InvoiceService {

  private static final Logger LOG = Logger.getLogger(InvoiceService.class);

  final InvoiceRepository invoiceRepository;

  final TransactionCloudEventsSender transactionCloudEventsSender;

  @ConfigProperty(name = "ce.type")
  String ceType;

  @ConfigProperty(name = "ce.source")
  String ceSource;

  @ConfigProperty(name = "ce.spec.version")
  String ceSpecVersion;

  @ConfigProperty(name = "ce.content.type")
  String ceContentType;

  @Inject
  public InvoiceService(InvoiceRepository invoiceRepository,@RestClient TransactionCloudEventsSender transactionCloudEventsSender) {
    this.invoiceRepository = invoiceRepository;
    this.transactionCloudEventsSender = transactionCloudEventsSender;
  }

  public Invoice find(Long id){
    return this.invoiceRepository.findById(id);
  }

  public List<Invoice> invoices(){
    return this.invoiceRepository.listAll();
  }

  @Transactional
  public Invoice add(InvoiceRequest invoiceRequest){
    var invoice = invoiceRequest.invoice();
    this.invoiceRepository.persist(invoice);
    this.transactionCloudEventsSender.send(this.ceType,this.ceSource,this.ceSpecVersion,String.valueOf(invoice.id),this.ceContentType,invoice.transaction());
    return invoice;
  }

}
