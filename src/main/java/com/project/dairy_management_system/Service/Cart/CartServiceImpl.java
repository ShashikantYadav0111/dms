package com.project.dairy_management_system.Service.Cart;

import com.project.dairy_management_system.Entity.Cart;
import com.project.dairy_management_system.Entity.CartItem;
import com.project.dairy_management_system.Entity.Product;
import com.project.dairy_management_system.Repository.CartItemRepository;
import com.project.dairy_management_system.Repository.CartRepository;
import com.project.dairy_management_system.Repository.CustomerRepository;
import com.project.dairy_management_system.Repository.ProductRepository;
import com.project.dairy_management_system.dto.ItemDto;
import com.project.dairy_management_system.dto.ItemRequest;
import com.project.dairy_management_system.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemMapper itemMapper;


    @Override
    public String addItem(ItemRequest itemRequest) {

        Optional<Cart> cart = cartRepository.findByCustomer_CustomerId(itemRequest.customerId());
        if (cart.isEmpty()) {
            Cart newCart = Cart.builder()
                    .customer(customerRepository.findById(itemRequest.customerId()).get())
                    .build();

            cartRepository.save(newCart);

            // 2. Find product
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));


            CartItem cartItem = cartItemRepository.save(CartItem.builder()
                    .cart(newCart)
                    .product(product)
                    .priceAtAddition(product.getPrice())
                    .quantity(1)
                    .build());

            newCart.setTotal(BigDecimal.valueOf(cartItem.getQuantity()).multiply(product.getPrice()));
            cartRepository.save(newCart);
            return "Item Created";
        }
        Cart fetchedCart = cart.get();
        Product product = productRepository.findById(itemRequest.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));


        CartItem cartItem = cartItemRepository.save(CartItem.builder()
                .cart(fetchedCart)
                .product(product)
                .priceAtAddition(product.getPrice())
                .quantity(1)
                .build());

        fetchedCart.setTotal(BigDecimal.valueOf(cartItem.getQuantity()).multiply(product.getPrice()).add(fetchedCart.getTotal()));
        cartRepository.save(fetchedCart);
        return "Item Created";
    }

    @Override
    public String removeItem(ItemRequest itemRequest) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findTop1ByProduct_ProductId(itemRequest.productId());
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();


            Optional<Cart> cartOptional = cartRepository.findByCustomer_CustomerId(itemRequest.customerId());
            if (cartOptional.isPresent()) {
                Cart cart = cartOptional.get();
                cart.setTotal(cart.getTotal().subtract(cartItem.getProduct().getPrice()));
                cartRepository.save(cart);
            }
            cartItemRepository.delete(cartItem);

        }
        return "Item Removed";
    }

    @Override
    public List<ItemDto> getCart(Long id) {
       Optional<Cart> cartOptional = cartRepository.findByCustomer_CustomerId(id);
        if(cartOptional.isPresent()){
            return cartOptional.get().getItems().stream().map(itemMapper::toDto).toList();
        }
        return null;
    }
}
