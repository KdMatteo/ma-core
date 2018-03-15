package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.DBSql;

import java.util.List;
import java.util.Map;

public interface DBSqlService extends BaseService<DBSql, Integer> {

    List<DBSql> findBySearch(Integer pageSize, Integer pageIndex, Map<String, Object> searchParams);
}
