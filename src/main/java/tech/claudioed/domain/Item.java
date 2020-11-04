package tech.claudioed.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Item {

  private String productId;

  private BigInteger quantity;

  private BigDecimal price;

  public BigDecimal value(){
    return BigDecimal.valueOf(this.quantity.intValue()).multiply(this.price);
  }

}
