package r2s.com.demo.SpringWebDemo.service;

import r2s.com.demo.SpringWebDemo.dto.request.InsertOrderRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateOrderRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.OrderResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrderDatabase();

    PageResponseDTO getOrderPaging();

    Order insertOrder(InsertOrderRequestDTO requestDTO);

    OrderResponseDTO getOrderbyId(Integer id);

    OrderResponseDTO updateOrder(UpdateOrderRequestDTO requestDTO, Integer id);

    void deleteOrderbyId(Integer id);
}
