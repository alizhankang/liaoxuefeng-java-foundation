package 面向对象编程.A面向对象基础.A继承;

/**
 * 继承 应该遵循 is关系:
 *  比如 Student 与 Person 的关系就是：Student is Person，那么 Student 就可以从 Person进行继承
 *  再比如 Student与 Book 的关系：Student 不是 is Book。那么 Student就不应该从Book继承，虽说愈发觉得上没有问题，但是不应该这样做；他们可以是组合的关系(has关系)
 */
public class Student extends Person{
    // 不要重复name和age字段/方法,
    // 只需要定义新增score字段/方法:
    // 继承有个特点，就是子类无法访问父类的private字段或者private方法
    private int score;

    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public String hello() {
        // 继承有个特点，就是子类无法访问父类的private字段或者private方法
        return "Hello, " + this.name; // 编译错误：无法访问name字段  这里使用super.name，或者this.name，或者name，效果都是一样的。编译器会自动定位到父类的name字段。
        // 这使得继承的作用被削弱了。为了让子类可以访问父类的字段，我们需要把private改为protected。用protected修饰的字段可以被子类访问：
    }
}
