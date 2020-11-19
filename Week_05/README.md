# 学习笔记

本周太忙，来不及写笔记，将来再补上Lifecycle部分的笔记。



# 作业

完成了如下作业：

## 第9节课作业实践

1. （选做）使Java里的动态代理，实现一个简单的AOP。

    **答案**：**homework-java-proxy** 工程。

2. （必做）写代码实现Spring Bean的装配，方式越多越好（XML、Annotation都可以）,提交到Github。

    **答案**：**homework-school-starter**  工程。共实现如下几种方式：

    - 自动装配：AutoScanApplication
    - 半自动装配：AutoScanWithXmlApplication
    - XML装配：XmlAppliation
    - Java Config装配：JavaConfigApplication

    

3. （选做）实现一个Spring XML自定义配置，配置一组Bean，例如Student/Klass/School。

    答案：**homework-school-starter**  工程。



## 第10节课作业实践

1. （选做）总结一下，单例的各种写法，比较它们的优劣。

2. （必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

    **答案**：

    - **homework-school-autoconfig** 工程实现自动配置

    - 编译、打包 **homework-school-autoconfig** 工程：`mvn clean package install`，使其进入到本地maven仓库

    - **homework-school-starter** 工程添加对**homework-school-autoconfig**有引用：

        ```xml
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>homework-school-autoconfig</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        ```

    - **homework-school-starter** 自动配置 ISchool。

3. （必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
    1）使用 JDBC 原生接口，实现数据库的增删改查操作。
    2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
    3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。

    **答案**：**homework-database-operation** 工程。