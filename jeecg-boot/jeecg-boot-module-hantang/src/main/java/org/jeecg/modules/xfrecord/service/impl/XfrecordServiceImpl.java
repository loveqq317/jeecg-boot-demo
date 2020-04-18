package org.jeecg.modules.xfrecord.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.xfrecord.entity.Xfrecord;
import org.jeecg.modules.xfrecord.mapper.XfrecordMapper;
import org.jeecg.modules.xfrecord.service.IXfrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 消费记录
 * @Author: juyahong
 * @Date:   2020-04-17
 * @Version: V1.0
 */
@Service
public class XfrecordServiceImpl extends ServiceImpl<XfrecordMapper, Xfrecord> implements IXfrecordService {
    @Autowired
    private XfrecordMapper xfrecordMapper;
    @Override
    @DS("multi-datasource1")
    public Page<Xfrecord> queryXfRecord(Page<Xfrecord> page) {
        return page.setRecords(xfrecordMapper.queryXfRecord(page));
    }
}
