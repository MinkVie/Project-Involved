package r2s.com.demo.SpringWebDemo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import r2s.com.demo.SpringWebDemo.dto.response.OrderResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Order;

import java.util.List;
@Component
public class OrderMapper {
    public List<OrderResponseDTO> convertEntityToResponseDtos(List<Order> orderList){
        return orderList.stream().map(this:: convertEntityToResponseDto).toList();
    }

    public OrderResponseDTO convertEntityToResponseDto(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        BeanUtils.copyProperties( order, orderResponseDTO);
        return orderResponseDTO;
    }
}
