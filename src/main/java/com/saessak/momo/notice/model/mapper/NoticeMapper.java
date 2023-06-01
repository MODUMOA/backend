package com.saessak.momo.notice.model.mapper;

import com.saessak.momo.notice.dto.NoticeForm;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface NoticeMapper {

    void writeNotice(NoticeForm noticeForm) throws SQLException;
    List<NoticeForm> getNoticeList() throws SQLException;

    NoticeForm getNoticeOne(int notice_idx) throws SQLException;

    void modifyNotice(NoticeForm noticeForm) throws SQLException;

    void deleteNotice(int notice_idx) throws SQLException;
}
