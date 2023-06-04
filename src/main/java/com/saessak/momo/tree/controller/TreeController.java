package com.saessak.momo.tree.controller;

import com.saessak.momo.global.dto.ResponseDto;
import com.saessak.momo.tree.dto.MyTreeListItem;
import com.saessak.momo.tree.dto.TreeListItem;
import com.saessak.momo.tree.model.service.TreeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tree")
@RequiredArgsConstructor
public class TreeController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private final TreeService treeService;

    @GetMapping()
    public ResponseEntity<ResponseDto> treeList() throws Exception{
        List<TreeListItem> list = null;

        try{
            list = treeService.getTreeList();
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT.value(), FAIL, null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, list));
    }

    @GetMapping("/{userIdx}")
    public ResponseEntity<ResponseDto> myTreeList(@PathVariable("userIdx") String userIdx) throws Exception{
        List<MyTreeListItem> list = null;

        try{
            list = treeService.getMyTreeList(Integer.parseInt(userIdx));
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT.value(), FAIL, null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, list));
    }


}
