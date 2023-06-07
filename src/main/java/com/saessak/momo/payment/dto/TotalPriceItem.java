package com.saessak.momo.payment.dto;

import lombok.Data;

@Data
public class TotalPriceItem {
    double price;
    int paymentIdx;
}
