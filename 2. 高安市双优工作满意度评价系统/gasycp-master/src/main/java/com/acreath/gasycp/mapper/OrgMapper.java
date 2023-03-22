package com.acreath.gasycp.mapper;

import com.acreath.gasycp.dto.OrgInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: longteng
 * @date: 2018/11/13 13:25
 * @description:
 */
@Mapper
public interface OrgMapper {
    OrgInfoDTO findOrgByOrgId(@Param("orgId") int orgId);
    List<OrgInfoDTO> getOrgByType(@Param("orgType") int orgType);
    void upDataLeaderStatus(@Param("leaderStatus") int leaderStatus,@Param("id") int id);

    void upDataExpertStatus(@Param("expertStatus") int expertStatus,@Param("id") int id);

    void upDataFileStatus(@Param("fileStatus") int fileStatus,@Param("id") int id);

    void upDataAdmStatus(@Param("admStatus") int admStatus,@Param("id") int id);
    void upDataModule1FileStatus(@Param("status") int status ,@Param("id") int id);
    void upDataModule2FileStatus(@Param("status") int status, @Param("id") int id);
    void upDataModule3FileStatus(@Param("status") int status,@Param("id") int id);
    void upDataModule4FileStatus(@Param("status") int status,@Param("id") int id);
    void upDataModule5FileStatus(@Param("status") int status,@Param("id") int id);
    void upDataModule6FileStatus(@Param("status") int status,@Param("id") int id);

}
