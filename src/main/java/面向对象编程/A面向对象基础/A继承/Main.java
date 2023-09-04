package 面向对象编程.A面向对象基础.A继承;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        Student student = new Student();

//        instanceOf(): 判断 该实例 是否是 该类型或该类型的子类
        System.out.println(person instanceof Person);
        System.out.println(person instanceof Student);

        System.out.println(student instanceof Person);
        System.out.println(student instanceof Student);

        // 如果 该实例 是某一个类或者该类的子类 的类型；那么该实例就可以被 该类类型的变量所接收
        Person person1 = student;

    }
}
