package com.paladin.account.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paladin.account.entity.MemberRuleSetting;
import com.paladin.account.resp.RespOk;
import com.paladin.account.service.IMemberRuleSettingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 会员积分成长规则表 前端控制器
 * </p>
 *
 * @author paladin
 * @since 2020-10-07
 */
@RestController
@RequestMapping("/member/rule/setting")
public class MemberRuleSettingController {

    public static final Logger LOGGER = LoggerFactory.getLogger(MemberRuleSettingController.class);

    @Resource
    private IMemberRuleSettingService memberRuleSettingService;

    @PostMapping(value = "/", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "添加成功"), @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code = 403, message = "请求被拒绝"), @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")})
//    @ApiImplicitParams({@ApiImplicitParam})
    @ApiOperation(value = "添加会员规则设置", notes = "添加会员规则设置", response = RespOk.class)
    public RespOk addCarItem(@RequestBody MemberRuleSetting memberRuleSetting) {
        boolean result = memberRuleSettingService.save(memberRuleSetting);
        return result ? new RespOk(200, "添加成功") : new RespOk(200, "添加失败");
    }

    @DeleteMapping(value = "/", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespOk deleteAccount(@RequestBody MemberRuleSetting memberRuleSetting) {
        boolean result = memberRuleSettingService.removeById(memberRuleSetting.getId());
        return result ? new RespOk(200, "删除成功") : new RespOk(200, "删除失败");
    }

    @PutMapping(value = "/", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespOk modifyAccount(@RequestBody MemberRuleSetting memberRuleSetting) {
        boolean result = memberRuleSettingService.updateById(memberRuleSetting);
        return result ? new RespOk(200, "修改成功") : new RespOk(200, "修改失败");
    }

    @GetMapping(value = "/", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespOk findAccount(@RequestBody MemberRuleSetting memberRuleSetting) {
        MemberRuleSetting result = memberRuleSettingService.getById(memberRuleSetting);
        return new RespOk(200, "查询成功", result);
    }

    @GetMapping(value = "/list", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "批量查询会员规则设置", notes = "批量查询会员规则设置", responseContainer = "List", response = RespOk.class)
    public RespOk findAccountList(@RequestBody MemberRuleSetting memberRuleSetting) {
        List<MemberRuleSetting> memberRuleSettingList = memberRuleSettingService.list(new QueryWrapper<>(memberRuleSetting));
        return new RespOk(200, "查询成功", memberRuleSettingList);
    }

}
