package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.Privilege;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 权限表 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
public interface PrivilegeMapper extends BaseMapper<Privilege> {

    List<MenuVO> selectMenuByUserId(@Param("userId") Long userId, @Param("pid") Long pid);

    List<Privilege> selectAllByUserId(@Param("userId") Long userId);

    Integer insertWithId(Privilege permission);

}