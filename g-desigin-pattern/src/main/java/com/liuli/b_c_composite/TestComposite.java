package com.liuli.b_c_composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author pcc
 */
public class TestComposite {
    public static void main(String[] args) {
        Organization organization = new Country();
        organization.setName("中国");
        organization.setCode("001");

        Organization province1 = new Province();
        Organization province2 = new Province();
        province1.setName("台湾省");
        province1.setCode("001001");
        province2.setName("日本省");
        province2.setCode("001002");

        Organization city1 = new City();
        Organization city2 = new City();
        Organization city3 = new City();
        Organization city4 = new City();
        city1.setName("高雄市");
        city1.setCode("001001001");
        city2.setName("台北市");
        city2.setCode("001001002");
        city3.setName("东京市");
        city3.setCode("001002001");
        city4.setName("大阪市");
        city4.setCode("001002002");


        // 组合属性结构
        List<Organization> listProvince = new ArrayList<>();
        listProvince.add(province1);
        listProvince.add(province2);
        // 台湾省
         List<Organization> listCity = new ArrayList<>();
         listCity.add(city1);
         listCity.add(city2);
         // 日本省
         List<Organization> listCity2 = new ArrayList<>();
         listCity2.add(city3);
         listCity2.add(city4);

        organization.setChildren(listProvince);
        province1.setChildren(listCity);
        province2.setChildren(listCity2);


        // 假设我们的业务场景是获取每个组织的名称和编码
        outPut(organization);

    }


    // 递归遍历
    public static void outPut(Organization organization){
        Optional.ofNullable(organization).ifPresent(org -> {
            System.out.println(org.getName() + " " + org.getCode());
            // 存在子节点
            Optional.ofNullable(org.getChildren()).ifPresent(children -> {
                children.forEach(TestComposite::outPut);
            });
        });
    }
}