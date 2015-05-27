package pl.edu.pw.fizyka.pojava.atombomb;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Simulation1{
	/*do wyboru przez uzytkownika: energia pojedynczego rozpadu, przekroj czynny,
	 *  prawdopodobienstwo naturalnego rozpadu, masa probki, odleglosc miedzy atomami */
	double energy;
	double totalEnergy;
	double crossSection;
	double likelyhoodOfDecay;
	double mass;
	int numberOfAtoms;
	int numberOfFissioned;
	double distanceBetweenAtoms;
	int length;
	ArrayList<Atom> atoms;
	LinkedList<Neutron> neutrons;
	ArrayList<Neutron> neutrons1;
	double t;
	double dt;
	int x,y,z;
	int Index;
	
	public Simulation1(double en, double cS, double lOD, double m, double dBA){
		energy=en;
		totalEnergy=0;
		crossSection=cS;
		likelyhoodOfDecay=lOD;
		mass=1;
		numberOfFissioned=0;
		numberOfAtoms=(int) m;
		distanceBetweenAtoms=dBA;
		length=(int)Math.ceil(Math.pow((double)numberOfAtoms,(double)1/3));
		atoms=new ArrayList<Atom>();
		neutrons=new LinkedList<Neutron>();
		neutrons1=new ArrayList<Neutron>();
		t=0;
		dt=0.001;
	}
	
	public void refresh(double en, double cS, double lOD, double m, double dBA){
		energy=en;
		totalEnergy=0;
		crossSection=cS;
		likelyhoodOfDecay=lOD;
		mass=1;
		numberOfFissioned=0;
		numberOfAtoms=(int) m;
		distanceBetweenAtoms=dBA;
		length=(int)Math.ceil(Math.pow((double)numberOfAtoms,(double)1/3));
		atoms.clear();
		neutrons.clear();
		neutrons1.clear();
		t=0;
		dt=0.001;
	}
	
	public void placeAtoms(){
		for(int x=0; x<length; x++){
			for(int y=0; y<length; y++){
				for(int z=0; z<length; z++){
					if(numberOfAtoms>atoms.size()){
						atoms.add(new Atom((double)x*distanceBetweenAtoms,(double) y*distanceBetweenAtoms,(double) z*distanceBetweenAtoms,false));
					}						
					else{
						atoms.add(new Atom((double)x*distanceBetweenAtoms,(double) y*distanceBetweenAtoms,(double) z*distanceBetweenAtoms,true));
						numberOfFissioned++;
					}
				}
			}
		}
	}
	
	public void checkAtoms(){			
		for(Atom a : atoms){
			if(!a.fissioned && Math.random()<=likelyhoodOfDecay){
				a.fissioned=true;
				neutrons.add(new Neutron(a.x,a.y,a.z,10));
				neutrons.add(new Neutron(a.x,a.y,a.z,10));
				totalEnergy+=energy;
				numberOfFissioned++;
			}
		}
	}
	
	
	public void checkNeutrons(){
		//Iterator<Neutron> neutronsIterator = neutrons.iterator();
		for (Iterator<Neutron> neutronsIterator = neutrons.iterator(); neutronsIterator.hasNext();){
			Neutron n = neutronsIterator.next();
			n.move(dt);
			if(n.isOut((length-1)*distanceBetweenAtoms))
				neutronsIterator.remove();
			else{
				if((n.x/distanceBetweenAtoms-Math.floor(n.x/distanceBetweenAtoms))<0.5)
					x=(int)Math.floor(n.x/distanceBetweenAtoms);
				else
					x=(int)Math.ceil(n.x/distanceBetweenAtoms);
				if((n.y/distanceBetweenAtoms-Math.floor(n.y/distanceBetweenAtoms))<0.5)
					y=(int)Math.floor(n.y/distanceBetweenAtoms);
				else
					y=(int)Math.ceil(n.y/distanceBetweenAtoms);
				if((n.z/distanceBetweenAtoms-Math.floor(n.z/distanceBetweenAtoms))<0.5)
					z=(int)Math.floor(n.z/distanceBetweenAtoms);
				else
					z=(int)Math.ceil(n.z/distanceBetweenAtoms);
				
				Index=calculateIndex(x,y,z,length);	
				
				if(!atoms.get(Index).fissioned){
					
					if(n.distance(atoms.get(Index))<crossSection){
						atoms.get(Index).fissioned=true;
						neutrons1.add(new Neutron(atoms.get(Index).x,atoms.get(Index).y,atoms.get(Index).z,10));
						neutrons1.add(new Neutron(atoms.get(Index).x,atoms.get(Index).y,atoms.get(Index).z,10));
						totalEnergy+=energy;
						numberOfFissioned++;
						
					}
				}
			}
			
		}
	}
	
	public static int calculateIndex (int x, int y, int z, int d){
		int i;
		if(x==0 && y==0)
			i=z;
		else if(x==0)
			i=y*d+z;
		else
			i=x*d*d+y*d+z;
		return i;
	}
	
	

}
