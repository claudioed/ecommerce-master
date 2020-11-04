package tech.claudioed.domain.service.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

  private String type;

  private String subType;

  private String fromAccount;

  private String toAccount;

  private String value;

  private String time;

  private String deviceType;

}
