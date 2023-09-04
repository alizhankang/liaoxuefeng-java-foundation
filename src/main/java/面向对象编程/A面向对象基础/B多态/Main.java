package 面向对象编程.A面向对象基础.B多态;

public class Main {
    public static void main(String[] args) {
        /**
         * 一个实际类型为Student，引用类型为Person的变量，调用其run()方法，调用的是Person还是Student的run()方法
         */
        Person p = new Student();
        p.run();
    }
}

class Person {
    public void run() {
        System.out.println("Person.run");
    }
}

/**
 * 在继承关系中，子类如果定义了一个与父类方法签名完全相同的方法，被称为覆写（Override）。或者叫 重写
 * Override和Overload不同的是:
 *      如果方法签名不同，就是Overload，Overload方法是一个新方法； -- 方法重载的返回值类型通常都是相同的
 *      如果方法签名相同，并且返回值也相同，就是Override。
 *   注意：方法名相同，方法参数相同，但方法返回值不同，也是不同的方法。在Java程序中，出现这种情况，编译器会报错。
 *   总结：在java中 方法名相同 无论是 重载 还是 重写 ，该方法的返回值也一定要相同，如果返回类型不相同，编译就会报错，不通过
 */
class Student extends Person {

    //加上@Override可以让编译器帮助检查是否进行了正确的覆写。希望进行覆写，但是不小心写错了方法签名，编译器会报错。
    // 也就是说加上 override之后，该方法必须是重写的方法，必须遵守重写的格式
    @Override
    public void run() {
        System.out.println("Student.run");
    }
    // overload
    public void run(String name) {
        System.out.println("Student.run");
    }

//    // 编译报错: 因为方法名相同了，只要是方法明相同，无论是overload 还是 override ，方法的返回类型一定要相同
//    public String run() {
//        System.out.println("Student.run");
//    }

}

