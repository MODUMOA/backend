package com.saessak.momo.trash.dto;

import lombok.Data;

@Data
public class DashboardItem {
    String category;
    int[] weeks = new int[7];
    int avg;
    int total;
    int prevPercent;
    boolean prevPercentStatus;
    int avgPercent;
    boolean avgPercentStatus;
}
