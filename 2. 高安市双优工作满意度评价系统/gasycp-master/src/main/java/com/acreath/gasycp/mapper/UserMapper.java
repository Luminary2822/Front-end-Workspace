package com.acreath.gasycp.mapper;

import com.acreath.gasycp.po.LoginUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: longteng
 * @date: 2018/10/23 13:27
 * @description:
 */
@Mapper
public interface UserMapper {
    LoginUserPO getUserAccountByLoginNameAndPassword(@Param("username") String username, @Param("password") String password);
    Integer updatePassword(@Param("username") String username ,@Param("newPassword") String newPassword);
    LoginUserPO searchAccount(@Param("username")  String username);
    void upDateIsScore(@Param("id") int id);
    List<LoginUserPO> allByType(@Param("identitys") int identitys);
    LoginUserPO searchAccountById(@Param("accountId")  int accountId);
    List<LoginUserPO> admByType(@Param("identitys") int identitys,@Param("identitysTwo") int identitysTwo);
    List<LoginUserPO> searchAccountByLoginName (@Param("loginName") String loginName);
    List<LoginUserPO> searchAccountByLoginNameList (@Param("loginName") String loginName);
    Integer searchAccountByLoginNameListCont(@Param("loginName") String loginName);
    Integer allTypeCont(@Param("identitys") int identitys);
}
