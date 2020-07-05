# ibatis-crypt-helper

一种 iBATIS 下简便的数据库字段加解密方案，利用 TypeHandler 实现。

这是一个可以使用的模板项目，实际使用中可以根据需求修改加解密逻辑。

## 环境要求

* iBATIS 2.x
* JDK 1.8 (可以修改加密方式降低版本要求)

## iBATIS 2.x 使用方法

适用于使用 ibatis 的老项目

### Maven
添加 Maven 依赖

```xml
<dependency>
    <groupId>com.huiyadan</groupId>
    <artifactId>ibatis-crypt-helper</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### 修改 sqlmap 进行加密
写入时加密（比如新增/修改），在入参中指定 TypeHandler

例子：
```xml
<insert id="insertUser" parameterClass="User">
    insert into user (name, age, phone)
    values
    (
        #name,handler=com.huiyadan.crypt.ibatis.CryptTypeHandler#,
        #age#,
        #phone,handler=com.huiyadan.crypt.ibatis.CryptTypeHandler#
    )
</insert>
```

查询时解密，在 resultMap 中指定 TypeHandler，注意返回必须是 resultMap 才能指定 TypeHandler。

例子：
```xml
<resultMap id="BaseResultMap" class="com.lianpay.User" >
    <result column="name" property="name" typeHandler="com.huiyadan.crypt.ibatis.CryptTypeHandler" />
    <result column="age" property="age" />
    <result column="phone" property="phone" typeHandler="com.huiyadan.crypt.ibatis.CryptTypeHandler" />
</resultMap>
```

注意：
在 `typeHandler` 可以结合 `javaType` 使用减少代码量（指定自定义 `javaType` 后会自动匹配对应的 `typeHandler` )，但实际使用中仅仅指定 `javaType` 只能在 `<insert>` 标签下起效，所以此处例子中放弃使用 `javaType` ，直接指定对应的 `typeHandler`。
