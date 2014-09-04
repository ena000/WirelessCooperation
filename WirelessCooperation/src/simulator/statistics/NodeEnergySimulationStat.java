package simulator.statistics;

import java.io.PrintWriter;
import java.util.ArrayList;

import simulator.Node;
import simulator.SimulationDirector;
import utils.Utils;

public class NodeEnergySimulationStat extends AbstractSimulationStat {
	
	StringBuilder sb;
	
	ArrayList<ArrayList<Integer>> counts;

	public NodeEnergySimulationStat(PrintWriter out, String additional_info) {
		super(out, additional_info);
		sb = new StringBuilder();
	}

	@Override
	public void update(SimulationDirector simulationDirector) {
		if ( counts == null ) {
			counts = new ArrayList<>();
			for ( Node n : simulationDirector.getWirelessNodeMap().getNodes() ) 
				counts.add(new ArrayList<Integer>());
		}
		for ( Node n : simulationDirector.getWirelessNodeMap().getNodes() ) 
			counts.get(n.getIdx()).add(n.isCooperator()?1:0);
	}
	
	@Override
	public void simulationFinished(SimulationDirector simulationDirector) {
		double a = simulationDirector.getWirelessNodeMap().geta();
		for ( Node n : simulationDirector.getWirelessNodeMap().getNodes() ) {
			double percent_coop = 0;
			for ( Integer i : counts.get(n.getIdx()) ) {
				percent_coop += i;
			}
			percent_coop /= counts.get(n.getIdx()).size();
			sb.append("Radius:"+Utils.dist(n.getX(), n.getY(),a/2,a/2)+" Energy_total:"+n.getTotal_spent_energy()+" PercentCoop:"+percent_coop+" EnergyCoop:"+n.getTotal_energy_spent_cooperator()+" CountIntermediate:"+n.count_intermediate+" CountCoop:"+n.count_real_cooperation+"\n");
		}
		super.simulationFinished(simulationDirector);
	}
	
	@Override
	public String messageToLog() {
		sb.insert(0,super.messageToLog());
		//sb.insert(0,"Energy spent by nodes distance:");
		return sb.toString();
	}
	
	

}