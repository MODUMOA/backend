package com.saessak.momo.notice.dto;

import lombok.Data;

@Data
public class ResponseNoticeForm {
    int noticeIdx;
    String writerName;
    String noticeTitle;
    String noticeContent;
    String date;
}
