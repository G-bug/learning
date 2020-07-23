package com.learn.jasper.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class DynamicReport {

	public void buildOriginalReport() {
		try {
			JasperDesign design = JRXmlLoader
					.load("report/templates/GenericLine.jrxml");

			JRDataSource datasource = prepareData();
			
			HashMap<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("datasource", datasource);

			// compile the report
			JasperReport report = JasperCompileManager.compileReport(design);

			// fill it with our data
			JasperPrint print = JasperFillManager.fillReport(report, paramsMap,
					new JREmptyDataSource());

			// view the report with the built-in viewer
			JasperViewer.viewReport(print);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public void buildReport() {

		try {
			JasperDesign design = JRXmlLoader
					.load("report/templates/GenericLine.jrxml");

			design.setPageHeight(250);
			design.setColumnWidth(390);
			design.setLeftMargin(0);
			design.setRightMargin(0);
			design.setTopMargin(0);
			design.setBottomMargin(0);
			design.setPageWidth(400);

			// create a style element and add it to the report
			JRDesignStyle chartStyle = new JRDesignStyle();
			chartStyle.setName("Title");
			chartStyle.setDefault(false);
			chartStyle.setFontName("DejaVu Sans");
			chartStyle.setFontSize(10);
			chartStyle.setPdfFontName("Helvetica");
			chartStyle.setPdfEncoding("Cp1252");
			chartStyle.setPdfEmbedded(false);
			chartStyle.setBold(true);
			design.addStyle(chartStyle);

			// Extract the summary band
			JRDesignBand summary = (JRDesignBand) design.getSummary();
			JRElement[] elements = summary.getElements();

			for (JRElement element : elements) { // loop through all its
													// elements
				if (element instanceof JRDesignChart) {

					// Get the line chart
					JRDesignChart chart = (JRDesignChart) element;

					// we first remove the existing chart element from the
					// summary band
					summary.removeElement(chart);

					// change the customizer class
					chart.setCustomizerClass("org.dynamicreport.linechart.DynamicLineCustomizer");

					// get the chart's existing title and subtitle expression
					// and modify its text
					JRDesignExpression titleExpression1 = (JRDesignExpression) chart
							.getTitleExpression();
					titleExpression1.setText("\"Dynamic title\"");
					chart.setTitleExpression(titleExpression1);
					JRFont titleFont = chart.getTitleFont();
					titleFont.setBold(true);
					titleFont.setFontSize(12);

					titleExpression1 = (JRDesignExpression) chart
							.getSubtitleExpression();
					titleExpression1.setText("\"Dynamic sub title\"");
					chart.setSubtitleExpression(titleExpression1);
					titleFont = chart.getSubtitleFont();
					titleFont.setBold(true);
					titleFont.setFontSize(11);
					chart.setSubtitleFont(titleFont);

					// change the chart's dimensions
					chart.setHeight(200);
					chart.setWidth(350);

					// add the chart style
					chart.setStyle(chartStyle);

					// add the chart back to the summary band
					summary.addElement(chart);

					// change the summary band's size
					summary.setHeight(240);

					// add the summary band back to the chart
					design.setSummary(summary);
				}
			}


			JRDataSource datasource = prepareData();
			
			HashMap<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("datasource", datasource);

			// compile the report
			JasperReport report = JasperCompileManager.compileReport(design);

			// fill it with our data
			JasperPrint print = JasperFillManager.fillReport(report, paramsMap,
					new JREmptyDataSource());

			// view the report with the built-in viewer
			JasperViewer.viewReport(print);

		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private JRMapCollectionDataSource prepareData() {
		// Chart Data
		String[] categoryTitles = { "Category 1", "Category 2",
				"Category 3", "Category 4", "Category 5" };
		Double[] dataSeries1 = { 2.09, 5.79, 1.73, 2.23, 2.09 };
		Double[] dataSeries2 = { 2.48, 2.16, 2.21, 2.01, 2.66 };

		HashMap<String, Object> dataMap = null;

		Collection<Map<String, ?>> lineChartData = new ArrayList<Map<String, ?>>();

		for (int i = 0; i < 5; i++) {
			dataMap = new HashMap<String, Object>();
			dataMap.put("series-titles", "Year 1");
			dataMap.put("category-titles", categoryTitles[i]);
			dataMap.put("data", dataSeries1[i]);
			lineChartData.add(dataMap);
		}

		for (int i = 0; i < 5; i++) {
			dataMap = new HashMap<String, Object>();
			dataMap.put("series-titles", "Year 2");
			dataMap.put("category-titles", categoryTitles[i]);
			dataMap.put("data", dataSeries2[i]);
			lineChartData.add(dataMap);
		}

		JRMapCollectionDataSource datasource = new JRMapCollectionDataSource(
				lineChartData);
		
		return datasource;
	}
	
	public static void main(String[] args) {
		DynamicReport dr = new DynamicReport();
		dr.buildOriginalReport();
		dr.buildReport();
	}
}
