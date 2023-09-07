package 面向对象编程.A面向对象基础.E静态字段和方法;

/**
 * 接口的静态字段
 * 因为interface是一个纯抽象类，所以它不能定义实例字段。但是，interface是可以有静态字段的，《《并且静态字段必须为<< final >>类型》》
 */
public class Main3 {
}


interface Person4 {
    public static final int MALE = 1;
    public static final int FEMALE = 2;
    // 实际上，因为interface的字段只能是public static final类型，所以我们可以把这些修饰符都去掉
    int AGE = 2;
}

