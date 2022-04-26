package com.wl.uniformresponseformat.util;

import com.wl.uniformresponseformat.pojo.MenuDemo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/11 15:34
 */
public class IteratorMenu {

    //模拟菜单数据
    private static List<MenuDemo> menuDemoList = Arrays.asList(
            new MenuDemo(1,"根节点1",0),
            new MenuDemo(2,"子节点1",1),
            new MenuDemo(3,"子节点2",1),
            new MenuDemo(4,"子节点3",7),
            new MenuDemo(5,"子节点4",8),
            new MenuDemo(6,"子节点5",9),
            new MenuDemo(7,"根节点2",0),
            new MenuDemo(8,"根节点3",0),
            new MenuDemo(9,"根节点4",0),
            new MenuDemo(10,"子节点6",7),
            new MenuDemo(11,"子节点7",8),
            new MenuDemo(12,"子节点8",9)
    );

    public static void main(String[] args) {
        iteratorParentMenu(menuDemoList);
    }

    public static void iteratorParentMenu(List<MenuDemo> menuDemoList){
        //循环父级
        List<MenuDemo> menus = menuDemoList.stream()
                .filter(menuDemo -> menuDemo.getParentId() == 0)//过滤条件
        .map(menuDemo -> {
            menuDemo.setChildMenuList(iteratorChildMenu(menuDemoList,menuDemo));//初始化子级菜单
            return menuDemo;//返回
        }).collect(Collectors.toList());
        System.out.println("menus : " + menus);
    }

    /**
     *     循环子级
     * @param menuDemoList 子级菜单列表
     * @param parent 父级菜单
     */
    private static List<MenuDemo> iteratorChildMenu(List<MenuDemo> menuDemoList,MenuDemo parent){
        List<MenuDemo> menuList = menuDemoList.stream()
                .filter(menu -> Objects.equals(parent.getId(),menu.getParentId()))//过滤父子关系，即parent.getId()与菜单列表id是否相等
                .map(menu -> {
                    //利用递归,封装List,并返回一个实体menu
                    menu.setChildMenuList(iteratorChildMenu(menuDemoList,menu));
                    return menu;
                }).collect(Collectors.toList());
        return menuList;
    }

}
