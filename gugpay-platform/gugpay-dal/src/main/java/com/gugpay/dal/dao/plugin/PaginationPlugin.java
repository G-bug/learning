/**
 * @author G-bug 2019/12/19
 */
package com.gugpay.dal.dao.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * @author G-bug
 * @Description generator 所需的分页插件
 * @Date 2019/12/19 17:17
 */
public class PaginationPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 为每个example类添加 limit 和 offset
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        PrimitiveTypeWrapper integerWrapper = FullyQualifiedJavaType.getIntInstance().getPrimitiveTypeWrapper();

        Field limit = new Field();
        limit.setName("limit");
        limit.setVisibility(JavaVisibility.PRIVATE);
        limit.setType(integerWrapper);
        topLevelClass.addField(limit);

        Method getLimit = new Method();
        getLimit.setVisibility(JavaVisibility.PUBLIC);
        getLimit.setReturnType(integerWrapper);
        getLimit.setName("getLimit");
        getLimit.addBodyLine("return limit;");
        topLevelClass.addMethod(getLimit);

        Method setLimit = new Method();
        setLimit.setVisibility(JavaVisibility.PUBLIC);
        setLimit.setName("setLimit");
        setLimit.addParameter(new Parameter(integerWrapper, "limit"));
        setLimit.addBodyLine("this.limit = limit;");
        topLevelClass.addMethod(setLimit);

        Field offset = new Field();
        offset.setVisibility(JavaVisibility.PRIVATE);
        offset.setName("offset");
        offset.setType(integerWrapper);
        topLevelClass.addField(offset);

        Method getOffset = new Method();
        getOffset.setVisibility(JavaVisibility.PUBLIC);
        getOffset.setName("getOffset");
        getOffset.addParameter(new Parameter(integerWrapper, "offset"));
        getOffset.addBodyLine("this.offset = offset;");
        topLevelClass.addMethod(getOffset);

        Method setOffset = new Method();
        setOffset.setVisibility(JavaVisibility.PUBLIC);
        setOffset.setName("setOffset");
        setOffset.addParameter(new Parameter(integerWrapper, "offset"));
        setOffset.addBodyLine("this.offset = offset;");
        topLevelClass.addMethod(setOffset);

        return true;
    }

    /**
     * 为mapper.xml的selectByExample添加 limit 和 offset
     */
    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement ifLimitNotNullElement = new XmlElement("if");
        ifLimitNotNullElement.addAttribute(new Attribute("test", "limit != null"));

        XmlElement ifOffsetNotNullElement = new XmlElement("if");
        ifOffsetNotNullElement.addAttribute(new Attribute("test", "offset != null"));
        ifOffsetNotNullElement.addElement(new TextElement("limit ${offset}, ${limit}"));
        ifLimitNotNullElement.addElement(ifOffsetNotNullElement);

        XmlElement ifOffsetNullElement = new XmlElement("if");
        ifOffsetNullElement.addAttribute(new Attribute("test", "offset != null"));
        ifOffsetNullElement.addElement(new TextElement("limit ${limit}"));
        ifLimitNotNullElement.addElement(ifOffsetNullElement);

        element.addElement(ifLimitNotNullElement);
        return true;
    }

}
