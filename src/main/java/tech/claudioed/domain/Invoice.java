package tech.claudioed.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.claudioed.domain.service.data.Transaction;

@Data
@Builder
@Entity
@Table(name = "invoice")
public class Invoice extends PanacheEntity {

  private LocalDateTime at;

  @Column(name = "customer_id")
  private String customerId;

  private String token;

  @ElementCollection
  @CollectionTable(name = "invoice_items")
  private List<Item> items;

  Invoice(){
    // do nothing
  }

  public Invoice(LocalDateTime at, String customerId, String token,
      List<Item> items) {
    this.at = at;
    this.customerId = customerId;
    this.token = token;
    this.items = items;
  }

  public LocalDateTime getAt() {
    return at;
  }

  public String getCustomerId() {
    return customerId;
  }

  public String getToken() {
    return token;
  }

  public List<Item> getItems() {
    return items;
  }

  private BigDecimal total() {
    return BigDecimal.valueOf(this.items.stream().mapToDouble(element -> element.value().doubleValue()).sum());
  }

  public Transaction transaction() {
    Transaction transaction = Transaction.builder().deviceType("WEB-ECOMMERCE-VISA")
        .value(total().toString()).type("CARD").subType("VISA").toAccount("000002453")
        .fromAccount(this.token).build();
    return transaction;
  }

}
