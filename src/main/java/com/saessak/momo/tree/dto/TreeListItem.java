package com.saessak.momo.tree.dto;

import lombok.Data;

@Data
public class TreeListItem {
    int treeIdx;
    String treeName;
    String treeImg;
    String treeInactiveImg;
    String weight;
}
