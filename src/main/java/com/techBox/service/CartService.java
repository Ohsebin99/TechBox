package com.techBox.service;

import com.techBox.dto.CartDTO;
import com.techBox.dto.MemberDTO;
import com.techBox.entity.CartEntity;
import com.techBox.entity.LikeEntity;
import com.techBox.entity.MemberEntity;
import com.techBox.entity.ProductEntity;
import com.techBox.repository.CartRepository;
import com.techBox.repository.MemberRepository;
import com.techBox.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public String save(CartDTO cartDTO) {

        Optional<MemberEntity> optionalMember = memberRepository.findById(cartDTO.getIdIndex());
        Optional<ProductEntity> optionalProduct = productRepository.findById(cartDTO.getProductId());

        if(optionalMember.isEmpty() || optionalProduct.isEmpty()){
            return "fail";
        }

        MemberEntity IdIndex = optionalMember.get();
        ProductEntity productId = optionalProduct.get();

        Optional<CartEntity> optionalCart = cartRepository.findByProductIdAndIdIndex(productId, IdIndex);
        if (optionalCart.isEmpty()){
            CartEntity cartEntity = CartEntity.toCartEntity(cartDTO);
            cartEntity.setIdIndex(IdIndex);
            cartEntity.setProductId(productId);
            cartRepository.save(cartEntity);
            return "success";
        }else {
            // 이미 있으면 수량을 업데이트
            CartEntity cartEntity = optionalCart.get();
            cartEntity.setQuantity(cartDTO.getQuantity());
            cartEntity.setSumPrice(cartDTO.getSumPrice());
            cartRepository.save(cartEntity);
            return "update";
        }
    }

    public List<CartEntity> getCartProducts(Long idIndex) {

        Optional<MemberEntity> optionalMember = memberRepository.findById(idIndex);
        if (optionalMember.isPresent()) {
            MemberEntity member = optionalMember.get();
            return cartRepository.findByIdIndex(member);
        } else {
            // 회원이 존재하지 않으면 빈 리스트 반환
            return new ArrayList<>();
        }

    }

    /*carts.stream()
            .map(CartEntity::getProductId)
                .collect(Collectors.toList());*/

    public Long count(Long idIndex) {

        Optional<MemberEntity> optionalMember = memberRepository.findById(idIndex);

        if (optionalMember.isPresent()) {
            return cartRepository.countByIdIndex(optionalMember.get());
        }else {
            return 0L;
        }

    }
}
