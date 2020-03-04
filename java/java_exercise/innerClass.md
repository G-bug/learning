### 内部类

#### 为什么要有内部类?
- 内部类可以访问外围类的数据域, 包括私有数据
	>内部类对象总有一个指向创建它的外部类对象的引用    
	>编译器通过`Innerclass`的所有构造器添加了`outerObject`
- 内部类可以是**私有类**
	>非内部类必须是protected/public修饰
- 匿名内部类可以起到**回调函数**的作用
	>如`lambda`表达式
#### 特殊语法
- 内部类 访问 外部类对象数据域(正规语法)`OuterClass.this.outerFiled`, 通常`this`省略
- 创建内部类对象
	``` Java
	// 外部类 作用域外 引用公有内部类
	OuterClass.InnerClass innerObject = outer.new InnerClass(...)
	// 外部类作用域内
	InnerClass innerObject = new InnerClass(...)
	```
##### 注意:
1. 内部类中声明的所有 静态域必须是`final`
    >因为静态域应只有一个实例, 而每个外部类都有一个单独内部类对象.
2. 内部类不能有`static`方法(除静态内部类)
    >该规范没有解释, 也可以有静态方法但只能访问外部类的静态域和方法.

#### 内部类必要性,安全性
- **内部类是编译器现象**, 与虚拟机无关
    >编译器将内部类翻译为, $分隔外部类名与内部类名的**常规类文件**
- 如果内部类访问了私有域, 就可能通过附加在外围类同包的其他类访问它们(但是难度较大)
    >使用`access$000`
```Java
// TalkingClock$Timprinter 反射信息
public class com.test.inner.TalkingClock$TimePrinter
{
    public com.test.inner.TalkingClock$TimePrinter(com.test.inner.TalkingClock);
    ...
    // 编译器添加的实例域 this$0
    // 用于内部类引用外围类
    final com.test.inner.TalkingClock this$0;
}

// TalkingClock 反射信息(使用 javap -private ClassName 命令)
// 使用反射类没有得到 access$000 方法
class com.test.inner.TalkingClock {
  private int interval;
  private boolean beep;
  public com.test.inner.TalkingClock(int, boolean);
  public void start();
  // 编译器添加
  // 该方法返回外部类对象域beep, 内部类使用beep时将调用该方法
  static boolean access$000(com.test.inner.TalkingClock);
}
```
```!
私有内部类使用 合成构造器 创建对象
```
```Java
    private OuterClass$InnerClass(OuterClass);
    // 编译器生成 包可见 构造器
    OuterClass$InnerClass(OuterClass, OuterClass$1)
    // 将外部类中的调用翻译为
    new OuterClass$InnerClass(this, null)
```
---
#### 局部内部类
只在外部类中使用了一次的内部类, 可定义为 局部内部类
- 不能 public/private 修饰, 作用域被限定在局部块中, 对外界完全隐藏
- 局部类可以访问 外围类 和 局部变量(必须是`final`)     
为了能够正常访问局部变量, 编译器在局部变量释放前对其备份.
    ```java
    public void start(int interval, boolean beep) {
        class TimePrinter implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (beep) ...      
            }
        }
        AcionListener listener = new TimePrinter();
        Timer t = new Timer(interval, beep);
        t.start();
    }
    // 方法结束, 定时器开始计时, 参数变量beep释放, 如何保证beep的使用 ?
    // 再次反射查看该内部类
    /* 1. 创建对象时编译器检测 对局部变量的访问
    *  2. 创建局部变量的数据域(val$beep), 拷贝局部变量到构造器中(多的 boolean)
    *  3. 数据域初始化(即:局部变量的副本)
    */
    class TalkingClock$TimePrinter {
        TalkingClock$TimePrinter(TalkingClock, boolean)
        ...
        final boolean val$beep;
    }
        
    ```
#### 匿名内部类(`lambda表达式`替换)
是局部内部类的深入, 只创建该类的一个对象就 不用命名
```Java
new SuperType(coustruction parameters) {
    // inner class methods and data
}
```
基于`supType`, 是超类的子类或接口的实现类    
因为构造器必须与类名一致, 因此不能有构造器, 若有构造参数, 要传递给超类构造器.
##### 扩展
- 双括号初始化 `new ArrayList(){{ add(...); ... }}`      
    外层括号建立了`ArrayList`的匿名子类, 内层括号是一个对象构造块
- 匿名子类做 `if (getClass() != other.getClass()) return false;` 得到 `false`
- 对于静态方法无法使用`this.getClass()`, 可以借助以下表达式
    ```Java
    // new Object(){} 创建Object的匿名子类的匿名对象(Object是最终类)
    // getEnclosingClass() 得到其外围类, 即包含这个静态方法的类
    new Object(){}.getClass().getEnclosingClass()
    ```
#### 静态内部类
只为了把某个类隐藏在另一个类内, 不需要引用外围类对象, 将内部类声明为`static`, 以取消产生的引用
- 不需要引用任何其他对象
- 除没有对外围类对象的引用特权外, 没有特殊之处
- 可以有静态域和方法
- 接口中的静态内部类自动成为`static`和`final`类

参考:  《Java核心技术》