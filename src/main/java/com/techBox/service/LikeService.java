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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;


    // 좋아요 삭제
    public void deleteLike(LikeDTO likeDTO) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(likeDTO.getIdIndex());
        Optional<ProductEntity> optionalProduct = productRepository.findById(likeDTO.getProductId());


        MemberEntity memberEntity = optionalMember.get();
        ProductEntity productEntity = optionalProduct.get();
        likeRepository.deleteByProductIdAndIdIndex(productEntity, memberEntity);
    }

    // 좋아요 활성화
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

    // 좋아요 여부 확인
    public boolean isLiked(Long productId, Long idIndex) {
        Optional<MemberEntity> member = memberRepository.findById(idIndex);
        Optional<ProductEntity> product = productRepository.findById(productId);

        if (member.isPresent() && product.isPresent()) {
            return likeRepository.existsByProductIdAndIdIndex(product.get(), member.get());
        }else {
            return false;

        }
    }

    // 좋아요 카운트
    public Long count(Long idIndex) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(idIndex);

        if (optionalMember.isPresent()) {
            return likeRepository.countByIdIndex(optionalMember.get());
        }else {
            return 0L;
        }

    }

    // 좋아요 표시한 상품 가져오기
    public List<ProductEntity> getLikedProducts(Long idIndex) {

        Optional<MemberEntity> optionalMember = memberRepository.findById(idIndex);
        List<LikeEntity> likes = likeRepository.findByIdIndex(optionalMember.get());

            return likes.stream()
                    .map(LikeEntity::getProductId)
                    .collect(Collectors.toList());
    }
}
