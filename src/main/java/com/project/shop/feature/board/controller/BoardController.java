package com.project.shop.feature.board.controller;

import com.project.shop.feature.board.dto.*;
import com.project.shop.feature.board.entity.Board;
import com.project.shop.feature.board.service.BoardService;
import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.board.dto.PostPrintList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board/")
public class BoardController {

    private static final String VIEW_PREFIX = "board/";

    private final MemberService memberService;
    private final BoardService boardService;

    @GetMapping("/")
    public String getBoard(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }
    @ResponseBody
    @PostMapping("/list")
    public PostPrintListResponse postBoard(@RequestBody PostPrintList postPrintList) {
        int total = boardService.count();

        Paging paging = new Paging(postPrintList.getCurrentPage(), 5, 5, total);
        List<Board> boardList = boardService.selectAll(paging);

        return new PostPrintListResponse(paging, boardList);
    }

    @GetMapping("/write")
    public String getWrite(Model model, HttpSession session) {
        int idx = (int)session.getAttribute("idx");

        if(Integer.valueOf(idx) != null) {
            String memberID = session.getAttribute("loggedIn").toString();

            Member member = memberService.select(idx);

            GetWriteResponse pageResponse = new GetWriteResponse();
            pageResponse.setIdx(idx);
            pageResponse.setMemberID(memberID);
            pageResponse.setWriter(member.getName());

            model.addAttribute("getWriteResponse", pageResponse);
            model.addAttribute("main", VIEW_PREFIX + "write");
        } else {
            model.addAttribute("main", "main/default");
        }
        return "view";
    }

    @PostMapping("/write")
    public String postWrite(Model model, PostWrite postWrite) {
        boardService.insert(postWrite.toEntity());
        return "redirect:/board/";
    }

    @GetMapping("/read/{boardID}")
    public String getRead(@PathVariable("boardID")int boardID, Model model) {
        Board board = boardService.select(boardID);
        GetReadResponse pageResponse = new GetReadResponse();
        pageResponse.setTitle(board.getTitle());
        pageResponse.setContent(board.getContent());
        pageResponse.setWriter(board.getWriter());
        pageResponse.setCreateDate(board.getCreateDate());
        pageResponse.setUpdateDate(board.getUpdateDate());
        pageResponse.setBoardID(board.getBoardID());
        model.addAttribute("getReadResponse", pageResponse);
        model.addAttribute("main", VIEW_PREFIX + "read");
        return "view";
    }

    @GetMapping("/delete")
    public String getDelete(PostDelete postDelete) {
        boardService.delete(postDelete.getBoardID());
        return "redirect:/board/";
    }

    @GetMapping("/update/{boardID}")
    public String getUpdate(@PathVariable("boardID")int boardID, Model model) {
        Board board = boardService.select(boardID);

        GetUpdateResponse pageResponse = new GetUpdateResponse();
        pageResponse.setBoardID(boardID);
        pageResponse.setTitle(board.getTitle());
        pageResponse.setContent(board.getContent());
        pageResponse.setWriter(board.getWriter());

        model.addAttribute("getUpdateResponse", pageResponse);
        model.addAttribute("main", VIEW_PREFIX + "update");
        return "view";
    }

    @PostMapping("/update")
    public String postUpdate(PostUpdate postUpdate) {
        boardService.update(postUpdate.toEntity());
        return "redirect:/board/read/" + postUpdate.getBoardID();
    }
}
