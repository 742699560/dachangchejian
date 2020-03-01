package com.dccj.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.dccj.entity.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users selectByUnionIdAndOpenIdAndId(@Param("unionId")String unionId,@Param("openId")String openId,@Param("id")Integer id);

}