package com.acreath.gasycp.mapper;

import com.acreath.gasycp.dto.MassQuestionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: longteng
 * @date: 2018/11/30 00:17
 * @description:
 */
@Mapper
public interface MassQuestionMapper {
    void insertMassQuestions(@Param("orgS")List<MassQuestionDTO> massQuestionDTOS);
    Integer contMassQuestionByPhone(@Param("phone") String phone);
}
