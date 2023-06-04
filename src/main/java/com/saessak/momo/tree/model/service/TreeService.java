package com.saessak.momo.tree.model.service;


import com.saessak.momo.tree.dto.TreeListItem;

import java.sql.SQLException;
import java.util.List;

public interface TreeService {
    List<TreeListItem> getTreeList() throws SQLException;

}
