package r2s.com.demo.SpringWebDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import r2s.com.demo.SpringWebDemo.dto.request.InsertOrderRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateOrderRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.OrderResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Order;
import r2s.com.demo.SpringWebDemo.mapper.OrderMapper;
import r2s.com.demo.SpringWebDemo.repository.OrderRepository;

import java.util.List;
@Component
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getAllOrderDatabase() {
        Iterable<Order> orderIterable = orderRepository.findAll();
        return (List<Order>) orderIterable;
    }
    @Override
    public PageResponseDTO getOrderPaging() {
        Pageable pageable = PageRequest.of(0,2);

        Page<Order> orderPage = orderRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get order by paging"));

        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(orderPage.getNumber());
        pageResponseDTO.setSize(orderPage.getSize());
        pageResponseDTO.setTotalPages(orderPage.getTotalPages());
        pageResponseDTO.setTotalRecord(orderPage.getTotalElements());

        List<OrderResponseDTO> orderResponseDTOS = orderMapper.convertEntityToResponseDtos(orderPage.getContent());
        pageResponseDTO.setData(orderResponseDTOS);

        return pageResponseDTO;
    }

    @Override
    @Transactional
    public Order insertOrder(InsertOrderRequestDTO requestDTO) {
        Order order= new Order();
        order.setOrderDate(requestDTO.getOrderDate());
        order.setStatus(requestDTO.isStatus());
        order.setCartId(requestDTO.getCartId());
        order.setTotalPay(requestDTO.getTotalPay());
        orderRepository.save(order);
        return order;
    }

    @Override
    public OrderResponseDTO getOrderbyId(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't get order by this id"));

        OrderResponseDTO orderResponseDTOS = orderMapper.convertEntityToResponseDto(order);
        return orderResponseDTOS;
    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrder(UpdateOrderRequestDTO requestDTO, Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't update order by this id"));
        order.setOrderDate(requestDTO.getOrderDate());
        order.setStatus(requestDTO.isStatus());
        order.setCartId(requestDTO.getCartId());
        order.setTotalPay(requestDTO.getTotalPay());
        orderRepository.save(order);
        OrderResponseDTO orderResponseDTO = orderMapper.convertEntityToResponseDto(order);
        return orderResponseDTO;
    }

    @Override
    @Transactional
    public void deleteOrderbyId(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't delete order by this id"));

        orderRepository.delete(order);
    }
}
