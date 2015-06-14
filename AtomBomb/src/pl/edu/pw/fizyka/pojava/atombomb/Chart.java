package pl.edu.pw.fizyka.pojava.atombomb;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart extends JPanel implements Runnable {
	
	public Thread thread;
	public Simulation sym;
	XYSeries totalEnergy;
	JFreeChart chart;
	public Chart(Simulation sym1) {
		totalEnergy=new XYSeries("Calkowita energia");
		sym=sym1;
		XYSeriesCollection series = new XYSeriesCollection(totalEnergy);
		chart = ChartFactory.createXYLineChart
                ("E(t)",  // Title
                  "Czas [mikros]",           // X-Axis label
                  "E [J]",           // Y-Axis label
                  series,          // Dataset
                  PlotOrientation.VERTICAL,        //Plot orientation
                  false,                //show legend
                  true,                // Show tooltips
                  false               //url show
                 );
		 ChartPanel chartPanel = new ChartPanel(chart);
		 setLayout(new BorderLayout());
		 this.add(chartPanel, BorderLayout.CENTER);
	}
	
	public void clear(){
		totalEnergy.clear();
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
			sym.iterateAtoms();
			sym.iterateNeutrons();
			sym.neutrons.addAll(sym.neutrons1);
			sym.neutrons1.clear();
			sym.t+=sym.dt;
			totalEnergy.add(sym.t*1E6, sym.totalEnergy);
			if(sym.numberOfFissioned>=0.7*sym.atoms.size())
				stop();
			try {
				Thread.sleep(0);
			} 
				catch(InterruptedException e){}
		 }
		
	}

}
