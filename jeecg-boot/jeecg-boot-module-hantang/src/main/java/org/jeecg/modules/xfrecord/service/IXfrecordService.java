package org.jeecg.modules.xfrecord.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.member.entity.Memberrecharge;
import org.jeecg.modules.xfrecord.entity.Xfrecord;

/**
 * @Description: 消费记录
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
public interface IXfrecordService extends IService<Xfrecord> {
    Page<Xfrecord> queryXfRecord (Page<Xfrecord> page);
}
