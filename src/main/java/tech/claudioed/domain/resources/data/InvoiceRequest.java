package tech.claudioed.domain.resources.data;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import tech.claudioed.domain.Invoice;
import tech.claudioed.domain.Item;

@Data
public class InvoiceRequest{

  private String customerId;

  private String token;

  private List<Item> items;

  public Invoice invoice(){
    return Invoice.builder().at(LocalDateTime.now()).customerId(this.customerId).token(this.token).items(this.items).build();
  }

}
