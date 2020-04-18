package org.jeecg.modules.member.mapper;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.member.entity.Memberrecharge;

/**
 * @Description: 会员充值记录
 * @Author: juyahong
 * @Date:   2020-04-17
 * @Version: V1.0
 */

public interface MemberrechargeMapper extends BaseMapper<Memberrecharge> {

    List<Memberrecharge> queryRechargeRecord(Page<Memberrecharge> page);
}
