package com.techBox.service;

import com.techBox.dto.MemberDTO;
import com.techBox.entity.MemberEntity;
import com.techBox.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);

    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byId = memberRepository.findByUserId(memberDTO.getUserId());
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
            // 회원 정보 미존재
            return null;
        }

    }

    public String idCheck(String userId) {
        Optional<MemberEntity> byId = memberRepository.findByUserId(userId);
        if (byId.isPresent()) {
            // 조회결과가 있다 -> 사용 불가
            return null;
        } else {
            // 조회결과 없다 -> 사용 가능
            return "ok";
        }

    }

    public String nicknameCheck(String nickname) {
        Optional<MemberEntity> byNickname = memberRepository.findByNickname(nickname);
        if (byNickname.isPresent()) {
            // 조회결과가 있다 -> 사용 불가
            return null;
        } else {
            // 조회결과 없다 -> 사용 가능
            return "ok";
        }
    }

    public MemberDTO selectMember(Long idIndex) {
        Optional<MemberEntity> byId = memberRepository.findById(idIndex);
        if (byId.isPresent()) {
            // 회원 정보 존재
            MemberEntity memberEntity = byId.get();
            MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
            return dto;
        }else {
            return null;
        }
    }

    public void userUpdate(MemberDTO memberDTO) {
        Optional<MemberEntity> byId = memberRepository.findById(memberDTO.getIdIndex());
        // 엔티티에서 직접 업데이트 메서드 호출
//        MemberEntity memberUpdate = memberEntity.updateMember(memberDTO);
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        // 수정된 엔티티 저장
        memberRepository.save(memberEntity);
    }
}

