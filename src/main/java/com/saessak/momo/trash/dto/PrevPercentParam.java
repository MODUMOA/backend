package com.saessak.momo.trash.dto;

import lombok.Data;

@Data
public class PrevPercentParam {
    int userIdx;
    int trashIdx;
    int startDay;
    int endDay;
}
