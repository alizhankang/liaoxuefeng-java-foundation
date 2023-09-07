package 面向对象编程.A面向对象基础.C抽象类;

/**
 * 抽象类:
 *      如果一个class定义了方法，但没有具体执行代码，这个方法就是抽象方法，抽象方法用abstract修饰。
 *          因为无法执行抽象方法，因此这个类也必须申明为抽象类（abstract class）。
 *      无法实例化的抽象类有什么用？
 *          因为抽象类本身被设计成只能用于被继承，因此，抽象类可以强迫子类实现其定义的抽象方法，否则编译会报错。因此，抽象方法实际上相当于定义了“规范”
 *
 * 抽象方法:
 *      如果父类的方法本身不需要实现任何功能，仅仅是为了定义方法签名，目的是让子类去覆写它，那么，可以把父类的方法声明为抽象方法
 *
 * 面向对象编程:
 *      这种尽量引用高层类型，避免引用实际子类型的方式，称之为面向抽象编程。
 *      面向抽象编程的本质就是：
 *          上层代码只定义规范（例如：abstract class Person）；
 *          不需要子类就可以实现业务逻辑（正常编译）；
 *          具体的业务逻辑由不同的子类实现，调用者并不关心。
 */
public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        Teacher teacher = new Teacher();
        // 调用 复写的抽象方法
        student.run();
        // 调用 父类的非抽象方法
        student.run2();

        teacher.run();
        teacher.run2();
    }
}


abstract class Person {
    /**
     * 父类
     * 把一个方法声明为abstract，表示它是一个抽象方法，本身没有实现任何方法语句。因为这个抽象方法本身是无法执行的，所以，Person类也无法被实例化。
     * 编译器会告诉我们，无法编译Person类，因为它包含抽象方法,必须把Person类本身也声明为abstract，才能正确编译它：
     */

    // 抽象方法
    public abstract void run();

    // 非抽象方法
    public void run2(){
        System.out.println("父类Person的非抽象方法调用...");
    };
}

class Student extends Person {
    /**
     * 子类:
     *      父类的非抽象方法，子类无需必须复写
     */
    @Override
    public void run() {
        System.out.println("在 Student 中重写父类中的抽象run()方法，在Student中调用...");
    }
}

class Teacher extends Person {
    /**
     * 子类：
     *      父类的非抽象方法，子类无需必须复写
     */
    @Override
    public void run() {
        System.out.println("在 Teacher中重写父类中的抽象run()方法，在Teacher中调用...");
    }

    @Override
    public void run2() {
        System.out.println("在 Teacher中重写父类中的非抽象run2()方法，在Teacher中调用...");
    }
}

