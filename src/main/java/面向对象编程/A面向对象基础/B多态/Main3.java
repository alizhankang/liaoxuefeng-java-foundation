package 面向对象编程.A面向对象基础.B多态;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 覆写Object方法：
 * 因为所有的class最终都继承自Object，而Object定义了几个重要的方法：
 *      toString()：把instance输出为String；
 *      equals()：判断两个instance是否逻辑相等；
 *      hashCode()：计算一个instance的哈希值。
 */
public class Main3 {
    public static void main(String[] args) {
        Person2 person2 = new Person2();
        Student2 student2 = new Student2();

        person2.setName("zhangSan");
        student2.setName("zhangSan");

        System.out.println(person2.equals(student2));

        // 创建一个HashMap，可以存入任意类型的key和value
        Map<Object, Object> map = new HashMap<>();

        // 添加键值对
        map.put("stringKey", "This is a string value");
        map.put(123, "This is an integer key");
        map.put(3.14, "This is a double key");
//        map.put(new CustomObject(), "This is a custom object key");

        // 获取值
        Object value = map.get("stringKey");
        System.out.println("Value for stringKey: " + value);

        // 遍历Map
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + val);
        }


        JSONObject jsonBody = new JSONObject();
        jsonBody.put("name", "zhangsan");
        jsonBody.put("age", 12);
    }
}

class Person2 {
    protected String name;

    public String hello() {
        return "Hello, " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 显示更有意义的字符串:
    @Override
    public String toString() {
        return "Person2:name=" + name;
    }

    // 比较是否相等:
    @Override
    public boolean equals(Object o) {
        // 当且仅当o为Person2类型:
        if (o instanceof Person2) {
            Person2 p = (Person2) o;
            // 并且name字段相同时，返回true:
            return this.name.equals(p.name);
        }
        return false;
    }

    // 计算hash:
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}

// 在子类的覆写方法中，如果要调用父类的被覆写的方法，可以通过super来调用。例如：
class Student2 extends Person2 {
    @Override
    public String hello() {
        // 调用父类的hello()方法:
        return super.hello() + "!";
    }
}
/**
 * final:
 *      1.继承可以允许子类覆写父类的方法。如果一个父类不允许子类对它的某个方法进行覆写，可以把该方法标记为final。用final修饰的方法不能被Override
 *      2.如果一个类不希望任何其他类继承自它，那么可以把这个类本身标记为final。用final修饰的类不能被继承
 *      3.对于一个类的实例字段，同样可以用final修饰。用final修饰的字段在初始化后不能被修改,对final字段重新赋值会报错(但是可以在构造方法中初始化final字段)
 */
