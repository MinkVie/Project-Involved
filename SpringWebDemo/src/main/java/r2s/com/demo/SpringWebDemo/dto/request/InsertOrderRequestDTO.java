package r2s.com.demo.SpringWebDemo.dto.request;

import lombok.Data;

import java.util.Date;
@Data
public class InsertOrderRequestDTO {
    private long totalPay;
    private Date orderDate;
    private boolean status;
    private int cartId;
}
