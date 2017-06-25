package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.FinalUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 正式名单 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
public interface FinalUserMapper extends BaseMapper<FinalUser> {

    List<FinalUser> selectPageByParams(@Param("current") int current, @Param("size") int size, @Param("finalUser") FinalUser finalUser);

    int selectCountByParams(@Param("finalUser") FinalUser finalUser);
}
