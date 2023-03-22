package com.acreath.gasycp.mapper;

import com.acreath.gasycp.dto.MakeScoreDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: longteng
 * @date: 2018/11/28 01:16
 * @description:
 */
@Mapper
public interface MakeScoreMapper {
    void addScore(@Param("makeScoreDTOS")List<MakeScoreDTO> makeScoreDTOS);
    void deleteAll(@Param("accountId") int accountId);
    void deleteOne(@Param("accountId") int accountId ,@Param("orgId") int orgId);
    Integer contScore(@Param("scoreType") int scoreType , @Param("orgId") int orgId);
    Integer contScoreByAccount(@Param("accountId") int account);

    void expertAddScore(@Param("makeScoreDTOS")List<MakeScoreDTO> makeScoreDTOS);
    void expertDeleteAll(@Param("accountId") int accountId);
    void expertDeleteOne(@Param("accountId") int accountId ,@Param("orgId") int orgId);
    Integer expertContScore(@Param("scoreType") int scoreType , @Param("orgId") int orgId);
    Integer expertContScoreByAccount(@Param("accountId") int account);
    Integer expertContScoreByAccountAndOrgId(@Param("accountId") int accountId , @Param("orgId") int orgId);
}
