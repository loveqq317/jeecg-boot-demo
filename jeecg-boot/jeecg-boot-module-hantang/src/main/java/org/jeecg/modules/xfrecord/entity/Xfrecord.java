package org.jeecg.modules.xfrecord.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 消费记录
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
@Data
@TableName("xfrecord")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="xfrecord对象", description="消费记录")
public class Xfrecord implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**会员卡号*/
	@Excel(name = "会员卡号", width = 15)
    @ApiModelProperty(value = "会员卡号")
    private String mbCardcode;
	/**会员姓名*/
	@Excel(name = "会员姓名", width = 15)
    @ApiModelProperty(value = "会员姓名")
    private String mbCardname;
	/**账单号*/
	@Excel(name = "账单号", width = 15)
    @ApiModelProperty(value = "账单号")
    private String zdCode;
	/**消费金额(消费单子上的金额)*/
	@Excel(name = "消费金额(消费单子上的金额)", width = 15)
    @ApiModelProperty(value = "消费金额(消费单子上的金额)")
    private BigDecimal xfdzMoney;
	/**实际消费*/
	@Excel(name = "实际消费", width = 15)
    @ApiModelProperty(value = "实际消费")
    private BigDecimal sjMoney;
	/**优惠率*/
	@Excel(name = "优惠率", width = 15)
    @ApiModelProperty(value = "优惠率")
    private BigDecimal yhRate;
	/**消费时间*/
	@Excel(name = "消费时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "消费时间")
    private Date xfTime;
	/**消费类型(已结，还款，反冲)*/
	@Excel(name = "消费类型(已结，还款，反冲)", width = 15)
    @ApiModelProperty(value = "消费类型(已结，还款，反冲)")
    private String xfType;
	/**收银员*/
	@Excel(name = "收银员", width = 15)
    @ApiModelProperty(value = "收银员")
    private String syOprator;
	/**消费余额*/
	@Excel(name = "消费余额", width = 15)
    @ApiModelProperty(value = "消费余额")
    private BigDecimal xfreMoney;
	/**分店编码*/
	@Excel(name = "分店编码", width = 15)
    @ApiModelProperty(value = "分店编码")
    private String fdCode;
	/**消费分店*/
	@Excel(name = "消费分店", width = 15)
    @ApiModelProperty(value = "消费分店")
    private String xffdName;
}
