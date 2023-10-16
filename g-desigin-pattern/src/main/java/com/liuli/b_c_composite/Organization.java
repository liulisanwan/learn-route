package com.liuli.b_c_composite;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author pcc
 * 这是将行政区划抽象出来的一个抽象的组织类
 * 假设只抽离除了名称、编码、子节点三个属性
 */
@Data
public abstract class Organization {
    private String name;
    private String code;
    private List<Organization> children;
}

/**
 * 国家类
 */
@Accessors(chain = true)
class Country extends Organization {
    public void supplierRMB(){
        System.out.println("我是国家，我可以印钱");
    }
}

/**
 * 省类
 */
@Accessors(chain = true)
class Province extends Organization {
    public void manageCity(){
        System.out.println("我是省，我可以管理市");
    }
}

/**
 * 市类
 */
@Accessors(chain = true)
class City extends Organization{
    public void cry(){
        System.out.println("我是底层人民，只能哭泣");
    }
}