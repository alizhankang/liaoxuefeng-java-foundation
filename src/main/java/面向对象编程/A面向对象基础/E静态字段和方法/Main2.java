package 面向对象编程.A面向对象基础.E静态字段和方法;

/**
 * 静态方法
 * 有静态字段，就有静态方法。用static修饰的方法称为静态方法。
 * 调用实例方法必须通过一个实例变量，而调用静态方法则不需要实例变量，通过类名就可以调用。静态方法类似其它编程语言的函数。
 *
 * 因为静态方法属于class而不属于实例，因此，静态方法内部，无法访问this变量，也无法访问实例字段， 《《《它(静态方法)只能访问静态字段》》。
 *
 * 通过实例变量也可以调用静态方法，但这只是编译器自动帮我们把实例改写成类名而已。
 * 通常情况下，通过实例变量访问静态字段和静态方法，会得到一个编译警告。
 *
 */
public class Main2 {
    public static void main(String[] args) {
        Person2.setNumber(99);
        System.out.println(Person.number);
    }
}

class Person2 {
    public static int number;

    public static void setNumber(int value) {
        number = value;
    }
}
