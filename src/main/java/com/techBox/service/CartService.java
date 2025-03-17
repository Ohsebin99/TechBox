package com.techBox.service;

import com.techBox.dto.CartDTO;
import com.techBox.entity.CartEntity;
import com.techBox.entity.MemberEntity;
import com.techBox.entity.ProductEntity;
import com.techBox.repository.CartRepository;
import com.techBox.repository.MemberRepository;
import com.techBox.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public void save(CartDTO cartDTO) {

        Optional<MemberEntity> optionalMember = memberRepository.findById(cartDTO.getIdIndex());
        Optional<ProductEntity> optionalProduct = productRepository.findById(cartDTO.getProductId());

        MemberEntity IdIndex = optionalMember.get();
        ProductEntity productId = optionalProduct.get();

        Optional<CartEntity> optionalCart = cartRepository.findByProductIdAndIdIndex(productId, IdIndex);
        if (optionalCart.isPresent()){
            System.out.println("상품 이미 존재");
        }else {
            CartEntity cartEntity = new CartEntity();
            cartEntity.setIdIndex(IdIndex);
            cartEntity.setProductId(productId);
            cartRepository.save(cartEntity);
        }


    }
}
