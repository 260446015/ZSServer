package com.huishu.ait.echart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.huishu.ait.echart.series.Radar;
import com.huishu.ait.echart.series.Serie;

/**
 * Created by yuwei on 2016/12/26
 */
public class Option {
	
	private Title title;
	
	private Legend legend;
	
//	private Grid grid;
	
	private XAxis xAxis;
	
	private YAxis yAxis;
	
	private Tooltip tooltip;
	
	private TextStyle textStyle;
	
	private List<Serie<?>> series;
	
	private VisualMap visualMap;
	
	//供雷达图使用
	private Collection<Radar<?>> radars;
	private RadarIndicator radar;
	
	public Option() {
//		this.grid = new Grid();
		this.tooltip = new Tooltip();
		this.textStyle = new TextStyle();
	}

	public Title getTitle() {
		return title;
	}

	public Option setTitle(Title title) {
		this.title = title;
		return this;
	}

	public Legend getLegend() {
		return legend;
	}

	public Option setLegend(Legend legend) {
		this.legend = legend;
		return this;
	}

//	public Grid getGrid() {
//		return grid;
//	}
//
//	public Option setGrid(Grid grid) {
//		this.grid = grid;
//		return this;
//	}

	public XAxis getxAxis() {
		return xAxis;
	}

	public Option setxAxis(XAxis xAxis) {
		this.xAxis = xAxis;
		return this;
	}

	public YAxis getyAxis() {
		return yAxis;
	}

	public Option setyAxis(YAxis yAxis) {
		this.yAxis = yAxis;
		return this;
	}

	public List<Serie<?>> getSeries() {
		return series;
	}

	public Option addSeries(Serie<?> serie) {
		if (series == null) {
			series = new ArrayList<>();
		}
		series.add(serie);
		return this;
	}
	
	public Option setSeries(List<Serie<?>> series) {
		this.series = series;
		return this;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public Option setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
		return this;
	}

	public Collection<Radar<?>> getRadars() {
		return radars;
	}

	public void setRadars(Collection<Radar<?>> radars) {
		this.radars = radars;
	}

	public RadarIndicator getRadar() {
		return radar;
	}

	public void setRadar(RadarIndicator radar) {
		this.radar = radar;
	}

	public TextStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(TextStyle textStyle) {
		this.textStyle = textStyle;
	}

	public VisualMap getVisualMap() {
		return visualMap;
	}

	public void setVisualMap(VisualMap visualMap) {
		this.visualMap = visualMap;
	}
	
}
