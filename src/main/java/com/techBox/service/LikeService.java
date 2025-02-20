package com.techBox.service;

import com.techBox.dto.LikeDTO;
import com.techBox.entity.LikeEntity;
import com.techBox.entity.MemberEntity;
import com.techBox.entity.ProductEntity;
import com.techBox.repository.LikeRepository;
import com.techBox.repository.MemberRepository;
import com.techBox.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

//    public void deleteLike(ProductEntity productId, MemberEntity idIndex) {
//        likeRepository.deleteByProductIdAndIdIndex(productId, idIndex);
//    }

//    public void save(Long productId, Long idIndex) {
//        LikeEntity likeEntity = LikeEntity.toLikeEntity(productId, idIndex);
//        likeRepository.save(likeEntity);
//    }


    public void deleteLike(LikeDTO likeDTO) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(likeDTO.getIdIndex());
        Optional<ProductEntity> optionalProduct = productRepository.findById(likeDTO.getProductId());


        MemberEntity memberEntity = optionalMember.get();
        ProductEntity productEntity = optionalProduct.get();
        likeRepository.deleteByProductIdAndIdIndex(productEntity, memberEntity);
    }

    public void save(LikeDTO likeDTO) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(likeDTO.getIdIndex());
        Optional<ProductEntity> optionalProduct = productRepository.findById(likeDTO.getProductId());


        MemberEntity memberEntity = optionalMember.get();
        ProductEntity productEntity = optionalProduct.get();

        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setIdIndex(memberEntity);
        likeEntity.setProductId(productEntity);
        likeRepository.save(likeEntity);
    }
}
