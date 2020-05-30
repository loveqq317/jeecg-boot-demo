package org.jeecg.modules.member.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.member.entity.Memberrecharge;
import org.jeecg.modules.member.mapper.MemberrechargeMapper;
import org.jeecg.modules.member.service.IMemberrechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 会员充值记录
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
@Service
public class MemberrechargeServiceImpl extends ServiceImpl<MemberrechargeMapper, Memberrecharge> implements IMemberrechargeService {
    @Autowired
    private MemberrechargeMapper memberrechargeMapper;
    @Override
    @DS("multi-datasource1")
    public Page<Memberrecharge> queryRechargeRecord(Page<Memberrecharge> page, String mbCardcode, Date syTime) {
        return page.setRecords(memberrechargeMapper.queryRechargeRecord(page, mbCardcode, syTime));
    }
}
