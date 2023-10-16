package com.liuli.b_e_proxy.cglib_proxy;

import com.liuli.b_e_proxy.static_proxy.SupplierDrug;
import com.liuli.b_e_proxy.static_proxy.YunNanBaiYaoSupplierDrug;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author pcc
 */
public class TestCGLIBDynamicProxy implements MethodInterceptor {

    // 注意final类型的参数，要么在静态代码块初始化，要么在构造函数初始化，要么就是直接初始化，其他不支持了
    Object object;

    public TestCGLIBDynamicProxy(Object object) {
        this.object = object;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        if("checkDrug".equals(method.getName())){
            System.out.println("增强前");
            Object invoke = method.invoke(object, objects);
            System.out.println("增强后");
            return invoke;
        }

        return method.invoke(object, objects);
    }

	// 下面是测试代码
    public static void main(String[] args) {
        // 增强器
        Enhancer enhancer = new Enhancer();
        // 设置代理类的父类，这里可以是被代理类，也可以是他的父类或者接口，这里
        enhancer.setSuperclass(YunNanBaiYaoSupplierDrug.class);
        // 设置MethodInterceptor的实现类，MethodInterceptor的intercept方法会在调用代理类的方法时调用
        enhancer.setCallback(new TestCGLIBDynamicProxy(new YunNanBaiYaoSupplierDrug()));
        // 创建代理类
        SupplierDrug supplierDrug = (SupplierDrug) enhancer.create();
        // 调用代理类的方法
        supplierDrug.produceDrug();
        supplierDrug.checkDrug();
    }
}