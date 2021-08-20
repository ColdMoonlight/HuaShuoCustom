package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.MlbackProfbreviewArea;

public interface MlbackProfbreviewAreaMapper {
    int deleteByPrimaryKey(Integer profbreviewAreaId);

    int insert(MlbackProfbreviewArea record);

    int insertSelective(MlbackProfbreviewArea record);

    MlbackProfbreviewArea selectByPrimaryKey(Integer profbreviewAreaId);

    int updateByPrimaryKeySelective(MlbackProfbreviewArea record);

    int updateByPrimaryKeyWithBLOBs(MlbackProfbreviewArea record);

    int updateByPrimaryKey(MlbackProfbreviewArea record);

	List<MlbackProfbreviewArea> selectMlbackProfbreviewAreabyPid(MlbackProfbreviewArea mlbackProfbreviewAreaReq);

	List<MlbackProfbreviewArea> selectMlbackProfbreviewAreaGetAllSimple();

	List<MlbackProfbreviewArea> selectMlbackProfbreviewAreabyParam(MlbackProfbreviewArea mlbackProfbreviewAreaReq);

	List<MlbackProfbreviewArea> selectMlbackProductBackLike(MlbackProfbreviewArea mlbackProfbreviewAreaReq);
}