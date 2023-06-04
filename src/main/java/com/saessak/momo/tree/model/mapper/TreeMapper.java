package com.saessak.momo.tree.model.mapper;

import com.saessak.momo.tree.dto.TreeListItem;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TreeMapper {
    List<TreeListItem> getTreeList() throws SQLException;

}
