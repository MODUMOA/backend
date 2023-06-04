package com.saessak.momo.tree.model.mapper;

import com.saessak.momo.tree.dto.MyTreeListItem;
import com.saessak.momo.tree.dto.TreeListItem;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TreeMapper {
    // 전체 나무 리스트 조회
    List<TreeListItem> getTreeList() throws SQLException;

    // 사용자가 보유한 나무 리스트 조회 - 나무도감
    List<MyTreeListItem> getMyTreeList(int userIdx) throws SQLException;

}
