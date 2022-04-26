package com.wl.uniformresponseformat.pojo;

import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/11 15:35
 */
@Data
public class MenuDemo {

    private Integer id;//菜单id

    @NonNull//@NonNull可以标注在方法、字段、参数之上，表示对应的值不可以为空
    private String menuName;//菜单名称

    @Nullable//@Nullable注解可以标注在方法、字段、参数之上，表示对应的值可以为空
    private Integer parentId;//父级菜单id
    //以上两个注解在程序运行的过程中不会起任何作用，只会在IDE、编译器、FindBugs检查、生成文档的时候有做提示；
    private List<MenuDemo> childMenuList;//子菜单列表集合

    public MenuDemo(Integer id, String menuName, Integer parentId) {
        this.id = id;
        this.menuName = menuName;
        this.parentId = parentId;
    }

    public MenuDemo(Integer id, String menuName, Integer parentId, List<MenuDemo> childMenuList) {
        this.id = id;
        this.menuName = menuName;
        this.parentId = parentId;
        this.childMenuList = childMenuList;
    }

}
