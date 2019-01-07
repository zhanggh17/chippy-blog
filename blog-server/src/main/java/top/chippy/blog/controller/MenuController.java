package top.chippy.blog.controller;

import com.loser.common.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import top.chippy.blog.entity.Menu;
import top.chippy.blog.service.MenuService;

import java.util.List;

/**
 * @Date: 2019/1/4 15:26
 * @program: chippy-blog
 * @Author: chippy
 * @Description:
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Object list() {
        Example example = new Example(Menu.class);
        example.orderBy("sort").asc();
        List<Menu> menus = menuService.selectByExample(example);
        return success(menus);
    }
}
