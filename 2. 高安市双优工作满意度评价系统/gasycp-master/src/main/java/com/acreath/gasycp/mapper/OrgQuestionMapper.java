package com.acreath.gasycp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: longteng
 * @date: 2018/12/7 22:21
 * @description:
 */
@Mapper
public interface OrgQuestionMapper {
    void insertOrgQuestions(@Param("accountId")int accountId, @Param("optionId") String optionId,@Param("optionNumber") int optionNumber);
    void deletedAll(@Param("accountId") int accountId);
}
