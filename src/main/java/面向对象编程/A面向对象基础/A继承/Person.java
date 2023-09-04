package 面向对象编程.A面向对象基础.A继承;

public class Person {
    // private修饰词: 作用域:只能在本类中被访问；就算是子类也无法访问父类的private字段或者private方法
//    private String name;
//    private int age;

    // 这使得继承的作用被削弱了。为了让子类可以访问父类的字段，我们需要把private改为protected。用protected修饰的字段可以被子类访问：
    protected String name;
    protected int age;

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
