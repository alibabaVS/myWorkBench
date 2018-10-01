package com.example.helloserviceapi;

/**
 * PackageName:com.example.helloserviceapi
 * ClassName: User
 *
 * @author zha.jiangjiang
 * @Description:
 * @Date: 2018/9/27 22:08
 * @see
 * @since JDK 1.8
 */
public class User {
    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
