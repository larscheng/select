

package com.slxy.www.common;


import com.slxy.www.domain.dto.SelectSubjectDto;
import com.slxy.www.domain.dto.SelectTopicDto;
import com.slxy.www.domain.dto.SelectUserBaseDto;
import com.slxy.www.domain.po.SelectSubject;
import com.slxy.www.domain.po.SelectTopic;
import com.slxy.www.domain.po.SelectUserBase;
import com.slxy.www.domain.vo.SelectSubjectVo;
import com.slxy.www.domain.vo.SelectTopicVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
@SuppressWarnings("all")
public interface SelectMapStructMapper {

    SelectMapStructMapper INSTANCE = Mappers.getMapper(SelectMapStructMapper.class);


    SelectUserBaseDto SelectUserBasePo2Dto(SelectUserBase user);

    List<SelectUserBaseDto> SelectUserBasesPo2Dto(List<SelectUserBase> userList);

    SelectSubjectDto SelectSubjectPoToDto(SelectSubject subject);

    List<SelectSubjectDto> SelectSubjectsPoToDto(List<SelectSubject> subjectList);

    SelectSubject SelectSubjectVoToPo(SelectSubjectVo vo);

    SelectTopicDto SelectTopicPoToDto(SelectTopic topic);

    SelectTopic SelectTopicVoToPo(SelectTopicVo vo);
}


	