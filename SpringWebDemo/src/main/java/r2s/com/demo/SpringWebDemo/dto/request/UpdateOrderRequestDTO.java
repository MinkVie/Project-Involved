package r2s.com.demo.SpringWebDemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequestDTO implements Serializable {
    private int id;
    private long totalPay;
    private Date orderDate;
    private boolean status;
    private int cartId;
}
