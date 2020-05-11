# mybatis基础使用

## 什么是MyBatis

MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

## MyBatis入门

1.创建maven项目引入mybatis项目

https://mvnrepository.com/artifact/org.mybatis/mybatis/3.5.4

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.4</version>
</dependency>
```

还有一个就是mysql连接的maven也要放入

```xml
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.20</version>
</dependency>
```

2.编写mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/family_service_platform?serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="********"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <!--这里是影射对应的dao接口文件-->
        <!--这里对应的是dao文件的绑定xml文件-->
        <mapper resource=""/>
    </mappers>
</configuration>
```

3.建设一个dao的接口

```java
package com.joy.dao;

import com.joy.dao.bean.Emp;

/**
 * @author joy
 * @version 1.0
 * @date 2020/5/11 19:26
 */
public interface EmpDao {
    public Emp findByEmpno(Integer empno);
}

```

4.创建一个dao对应的xml文件进行绑定EmpDao.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.joy.dao.IEmpDao">
    <select id="selectEmpByEmpno" resultType="com.joy.dao.bean.Emp">
        select * from tbl_color where id = #{id}
    </select>
</mapper>
```

5.编写对应的返回类

```java
package com.joy.dao.bean;

/**
 * @author joy
 * @version 1.0
 * @date 2020/5/11 19:27
 */
public class Emp {
    private int id;
    private String color;

    public Emp(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", color='" + color + '\'' +
                '}';
    }
}
```

6.写一个测试类

```java
import com.joy.dao.IEmpDao;
import com.joy.dao.bean.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author joy
 * @version 1.0
 * @date 2020/5/11 18:25
 */
public class Test {
    public static void main(String[] args) {
        //加载对应的配置文件
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = null;
            try {
                session = sqlSessionFactory.openSession();
                IEmpDao mapper = session.getMapper(IEmpDao.class);
                Emp blog = mapper.selectEmpByEmpno(1);
                System.out.println(blog);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                session.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
```

总结：发现一个小问题，就是mysql连接的版本最好是匹配到自己的版本不容易出现错误。