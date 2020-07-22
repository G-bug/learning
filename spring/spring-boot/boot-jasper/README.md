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


// poi包对应错误
/*
org.apache.poi.HHFColor.....
*/


// maven clean 重启可以了，应该是包更新不不干净
/*
Could not initialize class net.sf.jasperreports.engine.util.JRStyledTextParser
*/
```

- excel背景色设置为不是全白
    - jrxml中`<property name="net.sf.jasperreports.export.xls.white.page.background" value="false"/>`
    - 导出设置 
    ```java
        JRXlsExporter exporter.setConfiguration(new SimpleXlsxReportConfiguration(){{
                        setWhitePageBackground(false);
                    }});
    ```
  