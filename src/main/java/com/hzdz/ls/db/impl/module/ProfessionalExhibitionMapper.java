package com.hzdz.ls.db.impl.module;

import com.hzdz.ls.db.entity.module.ProfessionalExhibition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalExhibitionMapper {
    int addNewProfessionalExhibition(ProfessionalExhibition professionalExhibition);
    int updateImageUrl(ProfessionalExhibition professionalExhibition);
    List<ProfessionalExhibition> queryProfessionalExhibition(@Param("activityId") Integer activityId);
}
