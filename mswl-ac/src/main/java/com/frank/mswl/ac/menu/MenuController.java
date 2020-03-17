package com.frank.mswl.ac.menu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端菜单信息操作controller.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张明亮 创建.
 */
@Slf4j
@RequestMapping("/web/front")
@RestController
@CrossOrigin(origins = "*")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 菜单查询.
     *
     * @return String字符串
     */
    @RequestMapping("/menuquery")
    public String menuquery(@RequestBody String requestData) {

        WebRequest<MenuRequest> menuRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<MenuRequest>>() {
        });
        log.info("web菜单查询---------->传入的参数为：{}", requestData);
        return menuService.menuQuery(menuRequest);
    }

    /**
     * 菜单添加.
     *
     * @return String字符串
     */
    @RequestMapping("/menuadd")
    public String menuadd(@RequestBody String requestData) {

        WebRequest<MenuRequest> menuRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<MenuRequest>>() {
        });
        log.info("web菜单添加---------->传入的参数为：{}", requestData);
        return menuService.menuAdd(menuRequest);
    }

    /**
     * 菜单修改.
     *
     * @return String字符串
     */
    @RequestMapping("/menuedit")
    public String menuedit(@RequestBody String requestData) {

        WebRequest<MenuRequest> menuRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<MenuRequest>>() {
        });
        log.info("web菜单修改---------->传入的参数为：{}", requestData);
        return menuService.menuUpdate(menuRequest);
    }

    /**
     * 菜单删除.
     *
     * @return String字符串
     */
    @RequestMapping("/menudelete")
    public String menudelete(@RequestBody String requestData) {

        WebRequest<MenuRequest> menuRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<MenuRequest>>() {
        });
        log.info("web菜单删除---------->传入的参数为：{}", requestData);
        return menuService.menuDelete(menuRequest);
    }


}
