package com.saessak.momo.payment.model.service;

import com.saessak.momo.payment.dto.PaymentDetailItem;
import com.saessak.momo.payment.dto.TotalPriceItem;
import com.saessak.momo.payment.model.mapper.PaymentMapper;
import com.saessak.momo.trash.dto.TrashHistoryItem;
import com.saessak.momo.user.dto.UserExpItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final PaymentMapper paymentMapper;

    @Override
    public List<Integer> getTodayUsers() throws Exception {
        return paymentMapper.getTodayUsers();
    }

    @Override
    public void createPayment(int userIdx) throws Exception {
        paymentMapper.createPayment(userIdx);
    }

    @Override
    public List<TrashHistoryItem> getTrashHistory() throws Exception {
        return paymentMapper.getTrashHistory();
    }

    @Override
    public int getPaymentIdx(int userIdx) throws Exception {
        return paymentMapper.getPaymentIdx(userIdx);
    }

    @Override
    public double getTrashPrice(int trashIdx) throws Exception {
        return paymentMapper.getTrashPrice(trashIdx);
    }

    @Override
    public void createPaymentDetail(PaymentDetailItem paymentDetailItem) throws Exception {
        paymentMapper.createPaymentDetail(paymentDetailItem);
    }

    @Override
    public void updateTotalPrice(TotalPriceItem totalPriceItem) throws Exception {
        paymentMapper.updateTotalPrice(totalPriceItem);
    }

    @Override
    public List<TrashHistoryItem> getTrashHistoryByIdx(int userIdx, int trashIdx) throws Exception {
        return paymentMapper.getTrashHistoryByIdx(userIdx, trashIdx);
    }

    @Override
    public void updateUserExp(UserExpItem userExpItem) throws Exception {
        paymentMapper.updateUserExp(userExpItem);
    }

    @Override
    public int getDateDiff(String date1, String date2) throws Exception {
        return paymentMapper.getDateDiff(date1, date2);
    }

    @Override
    public int getUserExp(int userIdx) throws Exception {
        return paymentMapper.getUserExp(userIdx);
    }

    @Override
    public void updateExpMinus(int userIdx) throws Exception {
        paymentMapper.updateExpMinus(userIdx);
    }

    @Override
    public void updateTreeCollection(int userIdx) throws Exception {
        paymentMapper.updateTreeCollection(userIdx);
    }
}
