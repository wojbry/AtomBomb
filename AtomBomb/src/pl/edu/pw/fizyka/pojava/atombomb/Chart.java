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
	public Simulation1 sym;
	XYSeries seria1;
	JFreeChart wykres;
	public Chart(Simulation1 sym1) {
		seria1=new XYSeries("Seria 1");
		sym=sym1;
		XYSeriesCollection serie = new XYSeriesCollection(seria1);
		wykres = ChartFactory.createXYLineChart
                ("E(t)",  // Title
                  "Czas [s]",           // X-Axis label
                  "E [J]",           // Y-Axis label
                  serie,          // Dataset
                  PlotOrientation.VERTICAL,        //Plot orientation
                  false,                //show legend
                  true,                // Show tooltips
                  false               //url show
                 );
		 ChartPanel chartPanel = new ChartPanel(wykres);
		 setLayout(new BorderLayout());
		 this.add(chartPanel, BorderLayout.CENTER);
	}
	
	public void clear(){
		seria1.clear();
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
			sym.checkAtoms();
			sym.checkNeutrons();
			sym.neutrons.addAll(sym.neutrons1);
			sym.neutrons1.clear();
			sym.t+=sym.dt;
			seria1.add(sym.t, sym.totalEnergy);
			if(sym.numberOfFissioned>=sym.atoms.size())
				stop();
			try {
				Thread.sleep(0);
			} 
				catch(InterruptedException e){}
		 }
		
	}

}
