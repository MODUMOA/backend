package com.saessak.momo.payment.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentItem {
    int paymentIdx;
    int totalPrice;
    int status;
    String date;
    String weekday;
    List<PaymentDetailItem> details;
}
