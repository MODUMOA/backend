package com.saessak.momo.tree.dto;

import lombok.Data;

@Data
public class MyTreeListItem {
    int treeIdx;
    String treeName;
    int count;
    String treeImg;
    String treeInactiveImg;
    String weight;
}
