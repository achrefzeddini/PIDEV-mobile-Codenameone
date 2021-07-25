/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
/**
 *
 * @author Dorsaf
 */
public class ChartForm extends Form{
    
    
    
    private Resources theme;
    Form current7 = new Form(BoxLayout.y());
    
    public ChartForm(Form previous, Resources theme) {
        this.theme=theme;
                current7=this;
        setTitle("chart page");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
        
        
        // Generate the values
    double[] values = new double[]{7, 2};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(50);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Products", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    
    this.add(c);
    this.add(new SpanLabel("Our platform HuntKingdom allows you to explore products on and off promotion! as you can see mostly we have products on promotion."));
    
    Button btn = new Button("read about hunting equipments");
    btn.addActionListener(
            e -> {
            BrowserComponent browser = new BrowserComponent();
            browser.setURL("https://www.gomuddy.com/tree-stand-safety-important-piece-hunting-equipment/");
             this.add(browser);
            }
        );
    this.add(btn);
    
     
     
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e ->previous.showBack());

    }
    
    
    
    /**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(30);
    renderer.setLegendTextSize(30);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
   
        series.add("on promotion" ,values[0]);
        series.add("off promotion" ,values[1]);
    
    return series;
}


    
}
