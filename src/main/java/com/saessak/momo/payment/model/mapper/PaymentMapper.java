package com.saessak.momo.payment.model.mapper;

import com.saessak.momo.payment.dto.PaymentDetailItem;
import com.saessak.momo.payment.dto.PaymentItem;
import com.saessak.momo.payment.dto.TotalPriceItem;
import com.saessak.momo.trash.dto.TrashHistoryItem;
import com.saessak.momo.user.dto.UserExpItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {
    // 오늘 쓰레기 버린 userIdx 받아오기
    List<Integer> getTodayUsers() throws Exception;

    // user_idx 별로 payment 테이블에 insert 하기
    void createPayment(int userIdx) throws Exception;

    // 오늘 날짜 trash_history 가져오기
    List<TrashHistoryItem> getTrashHistory() throws Exception;

    // userIdx로 paymentIdx 받아오기
    int getPaymentIdx(int userIdx) throws Exception;

    // trashIdx로 trash_price 받아오기
    double getTrashPrice(int trashIdx) throws Exception;

    // payment_detail 테이블에 insert 하기
    void createPaymentDetail(PaymentDetailItem paymentDetailItem) throws Exception;

    // payment 테이블에 total_price update 하기
    void updateTotalPrice(TotalPriceItem totalPriceItem) throws Exception;

    // user_idx와 trash_idx 일치하는 trash_history 받아오기 (최대 2개)
    List<TrashHistoryItem> getTrashHistoryByIdx(@Param("userIdx") int userIdx, @Param("trashIdx") int trashIdx) throws Exception;

    // user테이블 exp UPDATE
    void updateUserExp(UserExpItem userExpItem) throws Exception;

    // 두 날짜 간 차이 구하기
    int getDateDiff(@Param("date1") String date1, @Param("date2") String date2) throws Exception;

    // 사용자 exp 구하기
    int getUserExp(int userIdx) throws Exception;

    // 사용자 exp UPDATE ( 10000 빼기 , leve += 1 )
    void updateExpMinus(int userIdx) throws Exception;

    // tree_collection UPDATE 하기
    void updateTreeCollection(int userIdx) throws Exception;

    // payment list
    List<PaymentItem> getPaymentList(int userIdx) throws Exception;

    // 요일 얻어오기
    int getWeekDay(String date) throws Exception;

    // paymentDetail 받아오기
    List<PaymentDetailItem> getPaymentDetail(int paymentIdx) throws Exception;
}
