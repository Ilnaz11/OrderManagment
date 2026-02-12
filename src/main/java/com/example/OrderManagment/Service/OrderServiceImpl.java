package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.*;
import com.example.OrderManagment.Exception.OrderNotFoundException;
import com.example.OrderManagment.Exception.ProductNotFoundException;
import com.example.OrderManagment.Exception.UserNotFoundException;
import com.example.OrderManagment.Repository.OrderRepository;
import com.example.OrderManagment.Repository.ProductRepository;
import com.example.OrderManagment.Repository.UserRepository;
import com.example.OrderManagment.dto.CreateOrderItemRequestDto;
import com.example.OrderManagment.dto.CreateOrderRequestDto;
import com.example.OrderManagment.dto.OrderResponseDto;
import com.example.OrderManagment.dto.UpdateOrderStatusRequestDto;
import com.example.OrderManagment.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(ProductRepository productRepository,
                            UserRepository userRepository,
                            OrderRepository orderRepository,
                            OrderMapper orderMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto) {
        Long userId = createOrderRequestDto.getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Not found User with ID: " + userId));

        Order order = new Order();
        order.setUser(user);
        order.setCurrentStatus(OrderStatus.CREATED);
        order.setTotalPrice(BigDecimal.ZERO);
        order.setCreatedAt(LocalDateTime.now());
        order.setLastChange(LocalDateTime.now());

        List<OrderItem> orderItems = order.getOrderItems();

        for(CreateOrderItemRequestDto items : createOrderRequestDto.getItems()) {
            Product product = productRepository.findById(items.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Not found product with ID: " + items.getProductId()));


            if (product.getProductStatus() != ProductStatus.ACTIVE
                    || product.getQuantity() == 0 || product.getQuantity() < items.getQuantity()) {
                throw new RuntimeException("The product is not available");
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(product.getQuantity());
            orderItem.setPriceAtOrderTime(product.getPrice());
            orderItem.setStatus(OrderItemStatus.ACTIVE);

            orderItems.add(orderItem);

        }
        order.setOrderItems(orderItems);

        BigDecimal totalPrice = orderItems.stream()
                .filter(orderItem -> orderItem.getStatus() == OrderItemStatus.ACTIVE)
                .map(orderItem -> orderItem.getPriceAtOrderTime()
                        .multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        return orderMapper.toDto(order);

    }

    @Override
    public OrderResponseDto addProductInOrder(Long id, List<CreateOrderItemRequestDto> items) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Not found Order with ID: " + id));

        List<OrderItem> orderItems = order.getOrderItems();

        for (CreateOrderItemRequestDto dto : items) {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Not found product with ID: " + dto.getProductId()));

            if(product.getProductStatus() != ProductStatus.ACTIVE
                    || product.getQuantity() == 0 || product.getQuantity() < dto.getQuantity()) {
                throw new RuntimeException("The product is not available");
            }

            Optional<OrderItem> optionalItem = orderItems.stream()
                    .filter(item -> item.getProduct().getId().equals(dto.getProductId()))
                    .findFirst();

            if (optionalItem.isPresent()) {
                OrderItem orderItem = optionalItem.get();
                orderItem.setQuantity(orderItem.getQuantity() + dto.getQuantity());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setPriceAtOrderTime(product.getPrice());
            orderItem.setQuantity(dto.getQuantity());
            orderItem.setStatus(OrderItemStatus.ACTIVE);

            orderItems.add(orderItem);

            product.setQuantity(product.getQuantity() - dto.getQuantity());


        }
        BigDecimal totalPrice = orderItems.stream() // Итоговая цена заказа цена * количество
                .filter(orderItem -> orderItem.getStatus() == OrderItemStatus.ACTIVE)
                .map(orderItem -> orderItem.getPriceAtOrderTime()
                        .multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        return orderMapper.toDto(order);

    }


    @Override
    public void removeItemOrder(Long orderId, Long orderItemId) {

    }

//    Что я меняю?
//Что может пойти не так?
//Что должно измениться в системе в итоге?

    @Override
    public Optional<OrderResponseDto> getOrderById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return List.of();
    }

    @Override
    public List<OrderResponseDto> getOrdersFromUser(Long userId) {
        return List.of();
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, UpdateOrderStatusRequestDto updateOrderStatusRequestDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void cancelOrderItem(Long orderId, Long orderItemId) {

    }
}
