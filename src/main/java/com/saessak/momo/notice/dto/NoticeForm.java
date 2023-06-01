package com.saessak.momo.notice.dto;

import lombok.Data;

@Data
public class NoticeForm {
    int noticeIdx;
    int userIdx;
    String noticeTitle;
    String noticeContent;
    String date;
}
