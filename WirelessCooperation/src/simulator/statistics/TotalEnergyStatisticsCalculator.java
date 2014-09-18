package simulator.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import simulator.SimulationDirector;

public class TotalEnergyStatisticsCalculator extends AbstractStatisticsCalculator<TotalSpentEnergySimulationStat> {
	
	@Override
	public String getAggregatedResult() {
		double res = stats.stream().map(TotalSpentEnergySimulationStat::getTotal_energy_spent).reduce((a,b) -> a+b).get();
		res /= stats.size();
		return String.format("%.2f", res);
	}

	@Override
	protected TotalSpentEnergySimulationStat getSimulationStatInternal() {
		return  new TotalSpentEnergySimulationStat();
	}
	
}
