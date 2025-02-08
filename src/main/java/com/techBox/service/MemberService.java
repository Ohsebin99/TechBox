package com.techBox.service;

import com.techBox.dto.MemberDTO;
import com.techBox.entity.MemberEntity;
import com.techBox.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        MemberEntity save = memberRepository.save(memberEntity);

    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byId = memberRepository.findById(memberDTO.getId());
        if (byId.isPresent()) {
            // 회원 정보 존재
            MemberEntity memberEntity = byId.get();
            if (memberEntity.getPassword().equals(memberDTO.getPassword())) {
                // 비밀번호 일치
                // entity 객체를 dto 객체로 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // 비밀번호 불일치
                return null;
            }
        } else {
            // 회원 정보 미본재
            return null;
        }

    }
}
