package com.bitc.board.controller;

import com.bitc.board.dto.BoardDto;
import com.bitc.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//  @Controller : 사용자가 웹브라우저를 통하여 어떠한 요청을 할 경우, 해당 요청을 처리하기 위한 비즈니스 로직을 가지고 있는 어노테이션. 클래스에 해당 어노테이션을 사용하면 해당 클래스는 사용자 요청을 처리하기 위한 클래스 라는 것을 스프링 프레임워크에 알림
//  컨트롤러가 하는 일
//  1. 사용자가 서버에 요청한 주소를 기반으로 사용자가 전송한 데이터를 받음
//  2. 사용자가 제공 할 View 파일을 연동
//  3. 사용자가 전송한 데이터를 바탕으로 서비스에게 내부 연산을 요청함
@Controller
public class BoardController {
//   @Autowired : 사용자가 해당 타입의 객체를 생성하는 것이 아니라 스프링프레임워크가 해당 타입의 객체를 생성하고 사용자는 이용만 하도록 하는 어노테이션
    @Autowired
    private BoardService boardService;

//  @RequestMapping : 사용자가 웹브라우저를 통해서 접속하는 실제 주소와 메서드를 매칭하기 위한 어노테이션
//   value 속성 : 사용자가 접속할 주소 설정, 2개 이상의 주소를 하나의 메서드와 연결하려면 {주소1, 주소2, ...} 형태로 사용, value 속성만 사용할 경우 생략 가능
//   method 속성 : 클라이언트에서 서버로 요청 시 사용하는 통신 방식을 설정하는 속성 (GET/POST), RequestMethod 타입을 사용(ex. method = RequestMethod.GET), Restful 방식을 사용할 경우 GET/POST/UPDATE/DELETE 를 사용할 수 있음, 기본값은 GET 방식
    @RequestMapping("/")
    public String index() throws Exception {
        return "index";  // resources>templates 하위의 html 파일 이름
    }

//    게시물 목록 페이지
    @RequestMapping("/board/openBoardList")  // .do : EJB 관련, 안넣어도 무방함
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("board/boardList");

        List<BoardDto> dataList = boardService.selectBoardList();
        mv.addObject("dataList", dataList);

        return mv;
    }

//    게시물 상세 보기 페이지
//    @RequestParam : jsp 의 request.getParameter() 와 같은 기능을 하는 어노테이션, 클라이언트에서 서버로 전송된 데이터를 가져오는 어노테이션
//    @RequestParam(value = "idx) : 하나 뿐일 때는 안써도 무방함
    @RequestMapping("/board/openBoardDetail") // 웹사이트 주소 : 앞에 / 붙여야함
    public ModelAndView openBoardDetail(@RequestParam int idx) throws Exception {
        ModelAndView mv = new ModelAndView("board/boardDetail");  // 리눅스는 앞의 /는 인식하지 못하는 오류가 생김(AWS 이용시)
//        mv.setViewName("board/boardDetail");  // 로그인해서 사용자 입력 받았을때 관리자 화면과 일반 사용자 화면 바꿀때 사용

        BoardDto board = boardService.selectBoardDetail(idx);
        mv.addObject("board", board);

        return mv;
    }

//    게시글 등록 view 페이지
    @RequestMapping("/board/boardWrite")
//    가져올것 없이 그냥 View만 할것이므로 String
    public String boardWrite() throws Exception {
        return "/board/boardWrite";
    }

//    게시글 등록
    @RequestMapping("/board/insertBoard")
    public String insertBoard(BoardDto board) throws Exception {
        boardService.insertBoard(board);

        return "redirect:/board/openBoardList";
    }

//    게시글 수정
    @RequestMapping("/board/updateBoard")
    public String updateBoard(BoardDto board) throws Exception {
        boardService.updateBoard(board);

        return "redirect:/board/openBoardList";
    }

//    게시글 삭제
    @RequestMapping("/board/deleteBoard")
    public String deleteBoard(@RequestParam int idx) throws Exception {
        boardService.deleteBoard(idx);

        return "redirect:/board/openBoardList";
    }
}
