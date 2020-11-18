# 作业

完成了如下作业：

## 第9节课作业实践

1. （选做）使Java里的动态代理，实现一个简单的AOP。

2. （必做）写代码实现Spring Bean的装配，方式越多越好（XML、Annotation都可以）,提交到Github。

    **答案**：共实现如下几种方式：

    - XML 设置 component-scan，class中添加注解 **@Component**：

        ```xml
        <context:component-scan base-package="org.example.bean.wiring.annotation"/>
        ```

        **Code**:  homework-spring-bean-wiring/src/main/java/org/example/bean/wiring/annotation

        

    - XML装配bean

    

3. （选做）实现一个Spring XML自定义配置，配置一组Bean，例如Student/Klass/School。



## 第10节课作业实践

1. （选做）总结一下，单例的各种写法，比较它们的优劣。
2. （选做）maven/spring 的 profile 机制，都有什么用法？
3. （必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。
4. （必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
    1）使用 JDBC 原生接口，实现数据库的增删改查操作。
    2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
    3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。