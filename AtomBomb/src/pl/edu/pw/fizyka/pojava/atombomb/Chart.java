package pl.edu.pw.fizyka.pojava.atombomb;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart extends JPanel implements Runnable {
	
	public Thread thread;
	public Simulation sym;
	XYSeries energy;
	XYSeries totalEnergy;
	JFreeChart chartTotalEnergy;
	JFreeChart chartEnergy;
	public Chart(Simulation sym1) {
		totalEnergy=new XYSeries("Calkowita energia");
		energy=new XYSeries("Energia");
		sym=sym1;
		XYSeriesCollection serieTotalEnergy = new XYSeriesCollection(totalEnergy);
		XYSeriesCollection serieEnergy = new XYSeriesCollection(energy);
		chartTotalEnergy = ChartFactory.createXYLineChart
                ("E(t)",  // Title
                  "Czas [mikros]",           // X-Axis label
                  "E",           // Y-Axis label
                  serieTotalEnergy,          // Dataset
                  PlotOrientation.VERTICAL,        //Plot orientation
                  true,                //show legend
                  true,                // Show tooltips
                  false               //url show
                 );
		chartEnergy = ChartFactory.createXYLineChart
                ("E(t)",  // Title
                  "Czas [mikros]",           // X-Axis label
                  "E",           // Y-Axis label
                  serieEnergy,          // Dataset
                  PlotOrientation.VERTICAL,        //Plot orientation
                  true,                //show legend
                  true,                // Show tooltips
                  false               //url show
                 );
		 ChartPanel chartPanelTotalEnergy = new ChartPanel(chartTotalEnergy);
		 ChartPanel chartPanelEnergy = new ChartPanel(chartEnergy);
		 setLayout(new BorderLayout());
		 
		 JTabbedPane tabbedPane = new JTabbedPane();
		 tabbedPane.addTab("Total Energy(t)", null, chartPanelTotalEnergy, null);
		 tabbedPane.addTab("Energy(t)", null, chartPanelEnergy, null);
		 this.add(tabbedPane, BorderLayout.CENTER);
	}
	
	public void clear(){
		totalEnergy.clear();
		energy.clear();
	}
	
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
			
		}
	}
	
	public void stop() {
	    if (thread != null) {
	    	thread = null;
	    }
	}

	@Override
	public void run() {
		sym.placeAtoms();
		while(thread!=null){	
			sym.energy=0;
			sym.iterateAtoms();
			sym.iterateNeutrons();
			sym.neutrons.addAll(sym.neutrons1);
			sym.neutrons1.clear();
			sym.t+=sym.dt;
			totalEnergy.add(sym.t*1E12, sym.totalEnergy);
			energy.add(sym.t*1E12, sym.energy);
			if(sym.numberOfFissioned>=0.7*sym.atoms.size())
				stop();
			try {
				Thread.sleep(0);
			} 
				catch(InterruptedException e){}
		 }
		
	}

}
