package top.chippy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loser.common.base.controller.BaseController;
import com.loser.common.util.Stringer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import top.chippy.blog.annotation.IgnoreAuth;
import top.chippy.blog.entity.Menu;
import top.chippy.blog.service.MenuService;
import top.chippy.blog.vo.MenuTree;
import top.chippy.blog.vo.Params;

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

    @IgnoreAuth
    @GetMapping("/list")
    public Object list() {
        List<Menu> menus = menuService.list();
        return success(menus);
    }

    @IgnoreAuth
    @GetMapping("/manager")
    public Object managerMenus() {
        List<MenuTree> list = menuService.managerMenus();
        return success(list);
    }

    @GetMapping("/all/list")
    public Object allMenus(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getLimit());
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        if (!Stringer.isNullOrEmpty(params.getSearch())) {
            criteria.andLike("title", "%" + params.getSearch() + "%");
        }
        List<Menu> list = menuService.selectByExample(example);
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(list);
        return success(pageInfo);
    }
}
