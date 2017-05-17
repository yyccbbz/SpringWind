package com.baomidou.springwind.service;

import com.baomidou.kisso.Token;
import com.baomidou.springwind.entity.Privilege;
import com.baomidou.springwind.entity.vo.MenuVO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 数据服务层接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
public interface IPrivilegeService extends IService<Privilege> {


    List<MenuVO> selectMenuVOByUserId(Long userId );


    List<Privilege> selectAllByUserId(Long userId );


    /**
     * <p>
     * 是否为可操作的权限
     * </p>
     * @param token
     * 				SSO 票据顶级父类
     * @param permission
     * 				操作权限编码
     * @return
     */
    boolean isActionable(Token token, String permission );

    /**
     * 菜单级别、权限验证
     *
     * @param token
     * @param permission
     * @return
     */
    boolean isPermitted(Token token, String permission);

    boolean insertWithId(Privilege privilege);

}
