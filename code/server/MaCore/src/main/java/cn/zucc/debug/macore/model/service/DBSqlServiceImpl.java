package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.DBSqlMapper;
import cn.zucc.debug.macore.model.pojo.DBSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("dbSqlService")
public class DBSqlServiceImpl extends BaseServiceImpl<DBSql, Integer> implements DBSqlService {
    @Autowired
    DBSqlMapper dbSqlMapper;

    @Override
    public BaseMapper<DBSql, Integer> getMapper() {
        return dbSqlMapper;
    }

    @Override
    public List<DBSql> findBySearch(Integer pageSize, Integer pageIndex, Map<String, Object> searchParams) {
        return dbSqlMapper.selectLimit((pageIndex-1) * pageSize, pageSize, searchParams);
    }
}
