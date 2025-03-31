package com.techBox.repository;

import com.techBox.dto.MemberDTO;
import com.techBox.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // 아이디 조회
    Optional<MemberEntity> findByUserId(String userId);

    // 닉네임 조회
    Optional<MemberEntity> findByNickname(String nickname);

}





