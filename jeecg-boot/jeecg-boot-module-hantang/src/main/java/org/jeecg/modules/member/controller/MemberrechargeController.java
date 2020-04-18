package org.jeecg.modules.member.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.member.entity.Memberrecharge;
import org.jeecg.modules.member.service.IMemberrechargeService;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 会员充值记录
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
@Api(tags="会员充值记录")
@RestController
@RequestMapping("/member/memberrecharge")
@Slf4j
public class MemberrechargeController extends JeecgController<Memberrecharge, IMemberrechargeService> {
	@Autowired
	private IMemberrechargeService memberrechargeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param memberrecharge
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "会员充值记录-分页列表查询")
	@ApiOperation(value="会员充值记录-分页列表查询", notes="会员充值记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Memberrecharge memberrecharge,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<Page<Memberrecharge>> result = new Result<Page<Memberrecharge>>();
		Page<Memberrecharge> pageList=new Page<Memberrecharge>(pageNo,pageSize);
		pageList=memberrechargeService.queryRechargeRecord(pageList);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param memberrecharge
	 * @return
	 */
	@AutoLog(value = "会员充值记录-添加")
	@ApiOperation(value="会员充值记录-添加", notes="会员充值记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Memberrecharge memberrecharge) {
		memberrechargeService.save(memberrecharge);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param memberrecharge
	 * @return
	 */
	@AutoLog(value = "会员充值记录-编辑")
	@ApiOperation(value="会员充值记录-编辑", notes="会员充值记录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Memberrecharge memberrecharge) {
		memberrechargeService.updateById(memberrecharge);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "会员充值记录-通过id删除")
	@ApiOperation(value="会员充值记录-通过id删除", notes="会员充值记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		memberrechargeService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "会员充值记录-批量删除")
	@ApiOperation(value="会员充值记录-批量删除", notes="会员充值记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.memberrechargeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "会员充值记录-通过id查询")
	@ApiOperation(value="会员充值记录-通过id查询", notes="会员充值记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Memberrecharge memberrecharge = memberrechargeService.getById(id);
		if(memberrecharge==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(memberrecharge);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param memberrecharge
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Memberrecharge memberrecharge) {
        return super.exportXls(request, memberrecharge, Memberrecharge.class, "会员充值记录");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Memberrecharge.class);
    }

}
