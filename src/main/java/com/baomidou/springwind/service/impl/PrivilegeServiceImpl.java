package com.baomidou.springwind.service.impl;

import com.baomidou.kisso.Token;
import com.baomidou.springwind.entity.Privilege;
import com.baomidou.springwind.entity.vo.MenuVO;
import com.baomidou.springwind.mapper.PrivilegeMapper;
import com.baomidou.springwind.service.IPrivilegeService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限表 数据服务层接口实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
@Service
public class PrivilegeServiceImpl extends BaseServiceImpl<PrivilegeMapper, Privilege> implements IPrivilegeService {


    /**
     * 获取页面的左侧菜单栏
     *
     * @param userId
     * @return
     */
    @Cacheable(value = "permissionCache", key = "#userId")
    @Override
    public List<MenuVO> selectMenuVOByUserId(Long userId) {
        List<MenuVO> perList = baseMapper.selectMenuByUserId(userId, 0L);
        if (perList == null || perList.isEmpty()) {
            return null;
        }
        List<MenuVO> mvList = new ArrayList<MenuVO>();
        for (MenuVO mv : perList) {
            mv.setMvList(baseMapper.selectMenuByUserId(userId, mv.getId()));
            mvList.add(mv);
        }
        return mvList;
    }

    /**
     * 菜单级别、权限验证，生产环境建议加上缓存处理。
     *
     * @param token
     * @param permission
     * @return
     */
    @Override
    public boolean isPermitted(Token token, String permission) {
        if (StringUtils.isNotBlank(permission)) {
            List<Privilege> pl = this.selectAllByUserId(token.getId());
            if (pl != null) {
                for (Privilege p : pl) {
                    if (permission.equals(p.getPermCode())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 根据用户ID查询用户拥有的权限
     *
     * @param userId
     * @return
     */
    @Cacheable(value = "permissionCache", key = "#userId")
    @Override
    public List<Privilege> selectAllByUserId(Long userId) {
        return baseMapper.selectAllByUserId(userId);
    }

    /**
     * 按钮级别、权限验证，生产环境建议加上缓存处理。
     *
     * 演示  admin 返回 true
     *
     * @param token
     * 				SSO 票据顶级父类
     * @param permission
     * 				操作权限编码
     * @return
     */
    @Override
    public boolean isActionable( Token token, String permission ) {
        System.err.println(" isActionable =" + permission);
        if ( token.getId() == 1L ) {
            return true;
        }
        return false;
    }

    /**
     * 新增权限，包含主键ID这个字段
     *
     * @param permission
     * @return
     */
    @Override
    public boolean insertWithId(Privilege permission) {
        Integer result = baseMapper.insertWithId(permission);
        return retBool(result);
    }

}
