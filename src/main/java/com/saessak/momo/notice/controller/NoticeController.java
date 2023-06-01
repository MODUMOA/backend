package com.saessak.momo.notice.controller;

import com.saessak.momo.global.dto.ResponseDto;
import com.saessak.momo.notice.dto.NoticeForm;
import com.saessak.momo.notice.model.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private final NoticeService noticeService;

    @PostMapping()
    public ResponseEntity<ResponseDto> writeNotice(@RequestBody NoticeForm noticeForm) throws Exception{

        try{
            noticeService.writeNotice(noticeForm);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT.value(), FAIL, null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, null));
    }

    @GetMapping()
    public ResponseEntity<ResponseDto> noticeList() throws Exception{
        List<NoticeForm> list = null;

        try{
            list = noticeService.getNoticeList();
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT.value(), FAIL, null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, list));
    }

    @GetMapping("/{notice_idx}")
    public ResponseEntity<ResponseDto> noticeOne(@PathVariable("notice_idx") String noticeIdx) throws Exception{
        NoticeForm noticeForm = null;

        try{
             noticeForm = noticeService.getNoticeOne(Integer.parseInt(noticeIdx));
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT.value(), FAIL, null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, noticeForm));
    }

    @PutMapping("/{notice_idx}")
    public ResponseEntity<ResponseDto> updateNotice(@PathVariable("notice_idx") String noticeIdx, @RequestBody NoticeForm noticeForm) throws Exception{

        try{
            noticeService.modifyNotice(noticeForm);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT.value(), FAIL, null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, null));
    }

    @DeleteMapping("/{notice_idx}")
    public ResponseEntity<ResponseDto> deleteNotice(@PathVariable("notice_idx") String noticeIdx) throws Exception{

        try{
            noticeService.deleteNotice(Integer.parseInt(noticeIdx));
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT.value(), FAIL, null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, null));

    }
}
