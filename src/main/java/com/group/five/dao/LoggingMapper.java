package com.group.five.dao;

import com.group.five.pojo.Logging;
import com.group.five.query.LoggingQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LoggingMapper {

    Integer insertSelective(Logging logging);

    List<Logging> selectByParams(LoggingQuery loggingQuery);




    @Update("update t_logging set is_valid = 0 where id = #{id}")
    Integer deleteLogging(Integer id);

    @Select("select * from t_logging where id = #{id}")
    Logging selectByPrimary(Integer id);
}
