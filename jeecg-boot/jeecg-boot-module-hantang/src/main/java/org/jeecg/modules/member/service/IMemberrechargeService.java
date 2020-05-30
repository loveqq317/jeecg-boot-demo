package org.jeecg.modules.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.member.entity.Memberrecharge;

import java.util.Date;

/**
 * @Description: 会员充值记录
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
public interface IMemberrechargeService extends IService<Memberrecharge> {
    Page<Memberrecharge> queryRechargeRecord (Page<Memberrecharge> page, String mbCardcode, Date syTime);
}
