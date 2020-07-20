### exception
```java
// 类似的是因为style 不支持，应查看style中是否有不支持的字体的情况等
/*
net.sf.jasperreports.engine.JRRuntimeException: Could not resolve style(s): Column header. .....
*/
// <fieldDescription> 属性错误, 此处与 Bean属性对应
/*
net.sf.jasperreports.engine.JRException: Error retrieving field value from bean: .
Caused by: java.lang.NoSuchMethodException: Unknown property '' on class 'class com.learn.jasper.entity.User'
*/

// jrxml 中的language="groovy" 删掉，或改为 java
/*
    org.codehaus.groovy.control.CompilationFailedException
*/
```