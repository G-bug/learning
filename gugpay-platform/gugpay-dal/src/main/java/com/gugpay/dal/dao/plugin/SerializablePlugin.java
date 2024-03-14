/**
 * @author G-bug 2019/12/19
 */
package com.gugpay.dal.dao.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;
import java.util.Properties;

/**
* @author G-bug
* @Description Example 类和 model类实现序列化插件
* @Date 2019/12/20 9:27
*/
public class SerializablePlugin extends PluginAdapter {

    private FullyQualifiedJavaType serializable = new FullyQualifiedJavaType("java.io.Serializable");
    private FullyQualifiedJavaType gwtSerializable = new FullyQualifiedJavaType("com.google.gwt.user.client.rpc.IsSerializable");
    private boolean addGWTInterface;
    private boolean suppressJavaInterface;

    public SerializablePlugin() {
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.addGWTInterface = Boolean.valueOf(properties.getProperty("addGWTInterface"));
        this.suppressJavaInterface = Boolean.valueOf(properties.getProperty("suppressJavaInterface"));
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    protected void makeSerializable(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (this.addGWTInterface) {
            topLevelClass.addImportedType(this.gwtSerializable);
            topLevelClass.addSuperInterface(this.gwtSerializable);
        }

        if (!this.suppressJavaInterface) {
            topLevelClass.addImportedType(this.serializable);
            topLevelClass.addSuperInterface(this.serializable);

            Field field = new Field();
            field.setFinal(true);
            field.setInitializationString("1L");
            field.setName("serialVersionUID");
            field.setStatic(true);
            field.setType(new FullyQualifiedJavaType("long"));
            field.setVisibility(JavaVisibility.PRIVATE);

            this.context.getCommentGenerator().addFieldComment(field, introspectedTable);
            topLevelClass.addField(field);
        }
    }

    /**
     * example类序列化方法
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);

        for (InnerClass innerClass : topLevelClass.getInnerClasses()) {
            if ("GeneratedCriteria".equals(innerClass.getType().getShortName())) {
                innerClass.addSuperInterface(serializable);
            }
            if ("Criteria".equals(innerClass.getType().getShortName())) {
                innerClass.addSuperInterface(serializable);
            }
            if ("Criteria".equals(innerClass.getType().getShortName())) {
                innerClass.addSuperInterface(serializable);
            }
        }
        return true;
    }

}
