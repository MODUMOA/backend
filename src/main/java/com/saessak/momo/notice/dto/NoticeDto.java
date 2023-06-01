package com.saessak.momo.notice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeDto {
    private int notice_idx;
    private int user_idx;
    private String notice_title;
    private String notice_content;
    private String date;
}
