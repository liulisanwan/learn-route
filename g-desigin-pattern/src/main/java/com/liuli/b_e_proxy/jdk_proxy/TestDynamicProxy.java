package com.liuli.b_e_proxy.jdk_proxy;

import com.liuli.b_e_proxy.static_proxy.SupplierDrug;
import com.liuli.b_e_proxy.static_proxy.YunNanBaiYaoSupplierDrug;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author pcc
 */
public class TestDynamicProxy implements InvocationHandler {

    private Object target;

    public TestDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //只代理生产药品的方法
        if("produceDrug".equals(method.getName())){
            System.out.println(proxy.getClass().getName() + " invoke method " + method.getName() + " begin...");

            System.out.println("动态代理前置操作");
            Object result = method.invoke(target, args);
            System.out.println("动态代理后置操作");

            System.out.println("返回参数:"+result);
            return result;
        }
        return method.invoke(target, args);

    }

    public static void main(String[] args) {
        SupplierDrug yunNanBaiYaoSupplierDrug = new YunNanBaiYaoSupplierDrug();
        SupplierDrug supplierDrug= (SupplierDrug)Proxy.newProxyInstance(
                // 传入类加载器
                yunNanBaiYaoSupplierDrug.getClass().getClassLoader(),
                // 传入类接口列表
                yunNanBaiYaoSupplierDrug.getClass().getInterfaces(),
                // 传入handler实现类
                new TestDynamicProxy(yunNanBaiYaoSupplierDrug)
        );
        supplierDrug.produceDrug();
        supplierDrug.checkDrug();
    }
}