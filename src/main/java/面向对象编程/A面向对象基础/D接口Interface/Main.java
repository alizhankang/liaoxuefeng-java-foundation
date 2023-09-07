package 面向对象编程.A面向对象基础.D接口Interface;

/**
 * 在抽象类中，抽象方法本质上是定义接口规范：即规定高层类的接口，从而保证所有子类都有相同的接口实现，这样，多态就能发挥出威力。
 * 如果一个抽象类没有字段，所有方法全部都是抽象方法,就可以把该抽象类改写为接口：interface。
 * 所谓interface，就是比抽象类还要抽象的纯抽象接口，因为它连字段都不能有。
 * 因为接口定义的所有方法默认都是public abstract的，所以这两个修饰符不需要写出来（写不写效果都一样）。
 *
 * default方法:实现类可以不必覆写default方法。default方法的目的是，当我们需要给接口新增一个方法时，会涉及到修改全部子类。如果新增的是default方法，那么子类就不必全部修改，只需要在需要覆写的地方去覆写新增方法。
 * default方法和抽象类的普通方法是有所不同的。因为interface没有字段，default方法无法访问字段，而抽象类的普通方法可以访问实例字段。
 */
public class Main {
}

interface Person {
    void run();
    String getName();

    //default方法:实现类可以不必覆写default方法。
    // default方法的目的是：当我们需要给接口新增一个方法时，会涉及到修改全部子类。如果新增的是default方法，那么子类就不必全部修改，只需要在需要覆写的地方去覆写新增方法。
    default void printName() {
        System.out.println(getName());
    }
}
