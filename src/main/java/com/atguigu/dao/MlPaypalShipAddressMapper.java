package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.MlPaypalShipAddress;

public interface MlPaypalShipAddressMapper {

    int insert(MlPaypalShipAddress record);

    MlPaypalShipAddress selectByPrimaryKey(Integer shippingaddressId);

    int updateByPrimaryKey(MlPaypalShipAddress record);
    
    //增
    int insertSelective(MlPaypalShipAddress record);
    //删
    int deleteByPrimaryKey(Integer shippingaddressId);
    //改
    int updateByPrimaryKeySelective(MlPaypalShipAddress record);
    //通过Payinfoid查询
    List<MlPaypalShipAddress> selectMlPaypalShipAddressByPayinfoid(MlPaypalShipAddress record);

	List<MlPaypalShipAddress> selectMlPaypalShipAddressByEmail(MlPaypalShipAddress mlPaypalShipAddressifOldReq);
    
}