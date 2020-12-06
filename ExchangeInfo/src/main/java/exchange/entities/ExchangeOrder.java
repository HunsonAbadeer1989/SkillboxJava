package exchange.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ExchangeOrder {

    private String internalOrderId;
    private String exchangeOrderId;
    private double filledQty;

    @Override
    public String toString() {
        return internalOrderId + ", " + exchangeOrderId +
                ", " + filledQty;
    }

}
