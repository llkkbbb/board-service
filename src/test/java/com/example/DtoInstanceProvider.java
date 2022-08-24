package com.example;

import com.example.boardservice.web.model.member_model.MemberSaveRequestDto;

public class DtoInstanceProvider {

    public static MemberSaveRequestDto createMemberSaveRequestDto() {
        return MemberSaveRequestDto.builder()
                .name("이기영")
                .nickname("까까머리")
                .email("기영@naver.com")
                .password("test1234")
                .build();
    }
}
