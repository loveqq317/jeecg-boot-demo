package org.jeecg.modules.member.entity;

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
 * @Description: 会员充值记录
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
@Data
@TableName("memberrecharge")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="memberrecharge对象", description="会员充值记录")
public class Memberrecharge implements Serializable {
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
	/**充值分店*/
	@Excel(name = "充值分店", width = 15)
    @ApiModelProperty(value = "充值分店")
    private String czfd;
	/**充值分店编码*/
	@Excel(name = "充值分店编码", width = 15)
    @ApiModelProperty(value = "充值分店编码")
    private String czfdCode;
	/**充值金额（本金+赠额）*/
	@Excel(name = "充值金额（本金+赠额）", width = 15)
    @ApiModelProperty(value = "充值金额（本金+赠额）")
    private BigDecimal ccMoney;
	/**充值本金*/
	@Excel(name = "充值本金", width = 15)
    @ApiModelProperty(value = "充值本金")
    private BigDecimal syMoney;
	/**赠额*/
	@Excel(name = "赠额", width = 15)
    @ApiModelProperty(value = "赠额")
    private BigDecimal caMoney;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额")
    private BigDecimal kqMoney;
	/**付款类型*/
	@Excel(name = "付款类型", width = 15)
    @ApiModelProperty(value = "付款类型")
    private String fkType;
	/**收银员*/
	@Excel(name = "收银员", width = 15)
    @ApiModelProperty(value = "收银员")
    private String syMen;
	/**收银时间*/
	@Excel(name = "收银时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "收银时间")
    private Date syTime;
	/**操作类型(发卡、充值）*/
	@Excel(name = "操作类型(发卡、充值）", width = 15)
    @ApiModelProperty(value = "操作类型(发卡、充值）")
    private String opType;
}
