package com.saessak.momo.trash.dto;

import lombok.Data;

@Data
public class TrashHistoryItem {
    int trashHistoryIdx;
    int userIdx;
    int trashIdx;
    int trashWeight;
    String date;
}
