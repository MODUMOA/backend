package com.saessak.momo.notice.model.service;

import com.saessak.momo.notice.dto.NoticeForm;
import com.saessak.momo.notice.dto.ResponseNoticeForm;
import com.saessak.momo.notice.model.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;

    @Override
    public void writeNotice(NoticeForm noticeForm) throws SQLException {
        noticeMapper.writeNotice(noticeForm);
    }

    @Override
    public List<ResponseNoticeForm> getNoticeList() throws SQLException {
        return noticeMapper.getNoticeList();
    }

    @Override
    public NoticeForm getNoticeOne(int noticeIdx) throws SQLException {
        return noticeMapper.getNoticeOne(noticeIdx);
    }

    @Override
    public void modifyNotice(NoticeForm noticeForm) throws SQLException {
        noticeMapper.modifyNotice(noticeForm);
    }

    @Override
    public void deleteNotice(int noticeIdx) throws SQLException {
        noticeMapper.deleteNotice(noticeIdx);
    }
}
