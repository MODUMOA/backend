package com.saessak.momo.notice.dto;

import lombok.Data;

@Data
public class NoticeDto {
    int notice_idx;
    int user_idx;
    String notice_title;
    String notice_content;
    String date;
}
