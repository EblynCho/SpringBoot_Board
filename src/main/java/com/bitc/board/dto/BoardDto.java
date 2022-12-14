package com.bitc.board.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// @Data : lombok 라이브러리에서 지원하는 어노테이션으로 해당 클래스의 멤버 변수에 대한 getter/setter/toString() 메서드를 자동으로 생성하는 어노테이션
//  DTO(Data Transfer Obeject) : 데이터 전송 시 사용하기 위한 Java class 객체, DB의 table과 매칭하는데 사용함
//  dto 클래스의 멤버 변수는 매칭되는 DB table의 컬럼명과 동일하게 맞춰야 함(대신 카멜 명명법 방식으로)

// @Getter, @Setter, @ToString 어노테이션을 모두 사용할 효과
@Data
//@Getter
//@Setter
//@ToString
public class BoardDto {
//    column 명과 동일하게 작성, but _ 없애고 대문자로 작성
    private int idx;
    private String title;
    private String contents;
    private String userId;
    private String pwd;
    private String createDt;
    private  String updateDt;
    private int hitCnt;
}
