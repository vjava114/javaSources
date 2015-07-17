package com.rocomo.mybatis.plugins;


import java.util.List;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.PrimitiveTypeWrapper;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;




/**
 * This plugin adds rowsPerPage and startIndex clause to the example class and
 * to the sqlMap selectByExample map.
 *
 * @author Marco Musu
 *
 */
public class AddMssqlTopPlugin extends PluginAdapter {

	public boolean validate(List<String> warnings) {
		return true;
	}

	
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		PrimitiveTypeWrapper integerWrapper = FullyQualifiedJavaType.getIntInstance().getPrimitiveTypeWrapper();

		Field rowsPerPage = new Field();
		rowsPerPage.setName("rowsPerPage");
		rowsPerPage.setVisibility(JavaVisibility.PRIVATE);
		rowsPerPage.setType(integerWrapper);
		topLevelClass.addField(rowsPerPage);

		Method rowsPerPageSet = new Method();
		rowsPerPageSet.setVisibility(JavaVisibility.PUBLIC);
		rowsPerPageSet.setName("setRowsPerPage");
		rowsPerPageSet.addParameter(new Parameter(integerWrapper, "rowsPerPage"));
		rowsPerPageSet.addBodyLine("this.rowsPerPage = rowsPerPage;");
		topLevelClass.addMethod(rowsPerPageSet);

		Method rowsPerPageGet = new Method();
		rowsPerPageGet.setVisibility(JavaVisibility.PUBLIC);
		rowsPerPageGet.setReturnType(integerWrapper);
		rowsPerPageGet.setName("getRowsPerPage");
		rowsPerPageGet.addBodyLine("return rowsPerPage;");
		topLevelClass.addMethod(rowsPerPageGet);

		Field startIndex = new Field();
		startIndex.setName("startIndex");
		startIndex.setVisibility(JavaVisibility.PRIVATE);
		startIndex.setType(integerWrapper);
		topLevelClass.addField(startIndex);

		Method startIndexSet = new Method();
		startIndexSet.setVisibility(JavaVisibility.PUBLIC);
		startIndexSet.setName("setStartIndex");
		startIndexSet.addParameter(new Parameter(integerWrapper, "startIndex"));
		startIndexSet.addBodyLine("this.startIndex = startIndex;");
		topLevelClass.addMethod(startIndexSet);

		Method startIndexGet = new Method();
		startIndexGet.setVisibility(JavaVisibility.PUBLIC);
		startIndexGet.setReturnType(integerWrapper);
		startIndexGet.setName("getStartIndex");
		startIndexGet.addBodyLine("return startIndex;");
		topLevelClass.addMethod(startIndexGet);

		return true;
	}
//
//	@Override
//	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
//		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
//
//		//XmlElement lastElement = (XmlElement)element.getElements().get(element.getElements().size());
//
//		XmlElement isNotNullElement = new XmlElement("if");
//		isNotNullElement.addAttribute(new Attribute("test", "rowsPerPage != null"));
//		isNotNullElement.addElement(new TextElement("TOP ${rowsPerPage}"));
//		element.getElements().add(isNotNullElement);
//
//
//		isNotNullElement = new XmlElement("if");
//		isNotNullElement.addAttribute(new Attribute("test", "startIndex != null"));
//		isNotNullElement.addElement(new TextElement("TOP2 ${startIndex}"));
//		element.getElements().add(isNotNullElement);
//		return true;
//	}
//	
//	
}
