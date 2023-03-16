package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.request.InsertOrderRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateOrderRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateOrderRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.OrderResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.OrderResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.UserResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Order;
import r2s.com.demo.SpringWebDemo.service.OrderService;

import java.util.List;

@RestController()
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    
    @GetMapping
    public ResponseEntity<?> getOrderPaging() {
        PageResponseDTO pageResponseDTO = orderService.getOrderPaging();
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<?> getOrderById(@PathVariable(value = "order-id") int orderId) {
        OrderResponseDTO OrderResponseDTO = orderService.getOrderbyId(orderId);
        return new ResponseEntity<>(OrderResponseDTO, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity insertOrder(@RequestBody InsertOrderRequestDTO requestDTO) {
        Order order = orderService.insertOrder(requestDTO);
        return new ResponseEntity(order, HttpStatus.OK);
    }

    @PutMapping("/{order-id}")
    public ResponseEntity updateOrder(@PathVariable(value = "order-id") int orderId,
                                     @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO) {
        OrderResponseDTO response = orderService.updateOrder(updateOrderRequestDTO, orderId);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrder(@PathVariable(value = "order-id") int orderId) {
        orderService.deleteOrderbyId(orderId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
