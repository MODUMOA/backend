package com.saessak.momo.notice.model.service;

import com.saessak.momo.notice.dto.NoticeForm;

import java.sql.SQLException;
import java.util.List;

public interface NoticeService {

    void writeNotice(NoticeForm noticeForm) throws SQLException;
    List<NoticeForm> getNoticeList() throws SQLException;

    NoticeForm getNoticeOne(int notice_idx) throws SQLException;

    void modifyNotice(NoticeForm noticeForm) throws SQLException;

    void deleteNotice(int notice_idx) throws SQLException;
}
