

package com.slxy.www.common.utils;


import com.slxy.www.model.SelectUserBase;
import com.slxy.www.model.dto.SelectUserBaseDto;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
@SuppressWarnings("all")
public interface SelectMapStructMapper {

    SelectMapStructMapper INSTANCE = Mappers.getMapper(SelectMapStructMapper.class);


    SelectUserBaseDto SelectUserBasePo2Dto(SelectUserBase user);

    List<SelectUserBaseDto> SelectUserBasesPo2Dto(List<SelectUserBase> userList);
}


	