package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.*;
import com.example.OrderManagment.Exception.BusinessException;
import com.example.OrderManagment.Exception.OrderNotFoundException;
import com.example.OrderManagment.Exception.ProductNotFoundException;
import com.example.OrderManagment.Exception.UserNotFoundException;
import com.example.OrderManagment.Repository.OrderRepository;
import com.example.OrderManagment.Repository.ProductRepository;
import com.example.OrderManagment.Repository.UserRepository;
import com.example.OrderManagment.dto.*;
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
        BigDecimal totalPrice = orderItems.stream() // Итоговая цена заказа цена * количество товаров
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
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Not found Order with id: " + orderId));

        List<OrderItem> orderItems = order.getOrderItems();

        if (order.getCurrentStatus() == OrderStatus.DELIVERED) {
            throw new OrderNotFoundException("You cannot change the order information");
        }

        Optional<OrderItem> orderItem = orderItems.stream()
                .filter(orderItem1 -> orderItem1.getId().equals(orderItemId)).findFirst();

        if (orderItem.isPresent()) {
            orderItems.removeIf(orderItem1 -> orderItem1.getId().equals(orderItemId));
        }

        Product product = orderItem.get().getProduct();
        product.setQuantity(product.getQuantity() + orderItem.get().getQuantity());

        BigDecimal totalPrice = orderItems.stream()
                .filter(orderItem1 -> orderItem1.getStatus() == OrderItemStatus.ACTIVE)
                .map(orderItem1 -> orderItem1.getPriceAtOrderTime()
                        .multiply(BigDecimal.valueOf(orderItem1.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

    }

    @Override
    public Optional<OrderResponseDto> getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        Order order = optionalOrder.orElseThrow(() -> new OrderNotFoundException("Not found Order with id: " + id));
        return optionalOrder.map(orderMapper::toDto);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> order = orderRepository.findAll();
        return orderMapper.toDtoList(order);
    }

    @Override
    public List<OrderResponseDto> getOrdersFromUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Not found User with id: " + userId));
        List<Order> orders = orderRepository.findByUserId(userId);
        return orderMapper.toDtoList(orders);
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, UpdateOrderStatusRequestDto dto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Not found Order with id: " + orderId));

        if (order.getCurrentStatus() == OrderStatus.DELIVERED
                || order.getCurrentStatus() == OrderStatus.CANCELLED) {
            throw new BusinessException("You cannot change Order status");
        }

        List<OrderHistory> orderHistories = order.getOrderHistories();

        if (!order.getCurrentStatus().canTransitionTo(dto.getOrderStatus())) {
            throw new BusinessException("Нельзя перейти с " + order.getCurrentStatus() + "в " + dto.getOrderStatus());
        }

        if (order.getCurrentStatus() != dto.getOrderStatus()) {

            OrderStatus oldStatus = order.getCurrentStatus();

            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setOrder(order);
            orderHistory.setUpdateDate(LocalDateTime.now());
            orderHistory.setOldStatus(oldStatus);
            orderHistory.setNewStatus(dto.getOrderStatus());
            orderHistory.setComment("Статус заказа был изменен с " + order.getCurrentStatus()
                    + "в статус" + dto.getOrderStatus());

            orderHistories.add(orderHistory);

            order.setCurrentStatus(dto.getOrderStatus());
        }


        Order savedOrderStatus = orderRepository.save(order);

        return orderMapper.toDto(savedOrderStatus);

    }

//    @Override
//    public void deleteById(Long id) {
//        orderRepository.deleteById(id);
//    }

    @Override
    public void cancelOrderItem(Long orderId, Long orderItemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Not found order with id: " + orderId));

        List<OrderItem> orderItems = order.getOrderItems();

        List<OrderHistory> orderHistories = order.getOrderHistories();

        if (order.getCurrentStatus() == OrderStatus.DELIVERED ||
                order.getCurrentStatus() == OrderStatus.CANCELLED) {
            throw new BusinessException("You cannot change the order information");
        }

        OrderItem orderItem = orderItems.stream()
                .filter(orderItem1 -> orderItem1.getId().equals(orderItemId)).findFirst()
                .orElseThrow(() -> new OrderNotFoundException("Not found OrderItem"));

        if (orderItem.getStatus() == OrderItemStatus.CANCELLED) {
            throw new BusinessException("You cannot cancel a repeat order position");
        }

        orderItem.setStatus(OrderItemStatus.CANCELLED);

        Product product = orderItem.getProduct();
        product.setQuantity(product.getQuantity() + orderItem.getQuantity());

        BigDecimal totalPrice = orderItems.stream()
                .filter(orderItem2 ->  orderItem2.getStatus() == OrderItemStatus.ACTIVE)
                .map(orderItem2 -> orderItem2.getPriceAtOrderTime()
                        .multiply(BigDecimal.valueOf(orderItem2.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);


        boolean allItemsCancelled = orderItems.stream()
                .allMatch(orderItem2 -> orderItem2.getStatus() == OrderItemStatus.CANCELLED);

        if (allItemsCancelled) {
            OrderStatus oldStatus = order.getCurrentStatus();
            if (!oldStatus.canTransitionTo(OrderStatus.CANCELLED)) {
                throw new BusinessException("Cannot cancel order from status: " + oldStatus);
            }

            order.setCurrentStatus(OrderStatus.CANCELLED);

            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setOrder(order);
            orderHistory.setUpdateDate(LocalDateTime.now());
            orderHistory.setOldStatus(oldStatus);
            orderHistory.setNewStatus(OrderStatus.CANCELLED);
            orderHistory.setComment("Статус заказа был изменен с " + oldStatus + "на " + OrderStatus.CANCELLED);

            orderHistories.add(orderHistory);

        }

        orderRepository.save(order);
    }

    @Override
    public Long getTotalCountOrders() {
        return orderRepository.count();
    }

    @Override
    public List<OrderStatusCountDto> getCountOrderByStatus() {
        return orderRepository.countOrderByStatus(); //количество заказов по каждому статусу
    }

    @Override
    public BigDecimal getSumOrderByStatus() {
        return orderRepository.sumTotalPriceByStatus(OrderStatus.DELIVERED);
    }

}