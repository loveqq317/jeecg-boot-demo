package org.jeecg.modules.xfrecord.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.member.entity.Memberrecharge;
import org.jeecg.modules.xfrecord.entity.Xfrecord;
import org.jeecg.modules.xfrecord.service.IXfrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 消费记录
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
@Api(tags="消费记录")
@RestController
@RequestMapping("/xfrecord/xfrecord")
@Slf4j
public class XfrecordController extends JeecgController<Xfrecord, IXfrecordService> {
	@Autowired
	private IXfrecordService xfrecordService;
	
	/**
	 * 分页列表查询
	 *
	 * @param xfrecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "消费记录-分页列表查询")
	@ApiOperation(value="消费记录-分页列表查询", notes="消费记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xfrecord xfrecord,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<Page<Xfrecord>> result = new Result<Page<Xfrecord>>();
		Page<Xfrecord> pageList=new Page<Xfrecord>(pageNo,pageSize);
		String begin=req.getParameter("xfTime_begin");
		String end=req.getParameter("xfTime_end");
		pageList=xfrecordService.queryXfRecord(pageList,xfrecord,begin,end);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param xfrecord
	 * @return
	 */
	@AutoLog(value = "消费记录-添加")
	@ApiOperation(value="消费记录-添加", notes="消费记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xfrecord xfrecord) {
		xfrecordService.save(xfrecord);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param xfrecord
	 * @return
	 */
	@AutoLog(value = "消费记录-编辑")
	@ApiOperation(value="消费记录-编辑", notes="消费记录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Xfrecord xfrecord) {
		xfrecordService.updateById(xfrecord);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "消费记录-通过id删除")
	@ApiOperation(value="消费记录-通过id删除", notes="消费记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xfrecordService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "消费记录-批量删除")
	@ApiOperation(value="消费记录-批量删除", notes="消费记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xfrecordService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "消费记录-通过id查询")
	@ApiOperation(value="消费记录-通过id查询", notes="消费记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Xfrecord xfrecord = xfrecordService.getById(id);
		if(xfrecord==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(xfrecord);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param xfrecord
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Xfrecord xfrecord) {
        return super.exportXls(request, xfrecord, Xfrecord.class, "消费记录");
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
        return super.importExcel(request, response, Xfrecord.class);
    }

}
