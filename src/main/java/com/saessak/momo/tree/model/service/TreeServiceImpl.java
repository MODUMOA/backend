package com.saessak.momo.tree.model.service;

import com.saessak.momo.tree.dto.TreeListItem;
import com.saessak.momo.tree.model.mapper.TreeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService{
    private final TreeMapper treeMapper;

    @Override
    public List<TreeListItem> getTreeList() throws SQLException {
        return treeMapper.getTreeList();
    }
}
