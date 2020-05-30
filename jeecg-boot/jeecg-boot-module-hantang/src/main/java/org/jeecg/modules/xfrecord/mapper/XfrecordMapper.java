package org.jeecg.modules.xfrecord.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.xfrecord.entity.Xfrecord;

import java.util.List;

/**
 * @Description: 消费记录
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
public interface XfrecordMapper extends BaseMapper<Xfrecord> {
    List<Xfrecord> queryXfRecord(Page<Xfrecord> page,@Param("xfrecordParams")Xfrecord xfrecord,@Param("beginDate")String beginDate,@Param("endDate")String endDate);
}
