package simulator;

import java.util.List;

class Pair<T> {
	public T a,b;
	public Pair(T aa,T bb) {
		a = aa; b = bb;
	}
}

public class TrafficManager {
	
	
	public TrafficManager() {
		
	}
	
	public Pair<Node> chooseConnectionpair(List<Node> nodes) throws Exception {
		if ( nodes == null || nodes.size() <= 1 ) throw new Exception("Traffic manager can't choose a connection pair because it doesn't know enough nodes");
		int sender = (int)(Math.random()*nodes.size());
		int recv = sender;
		while ( recv == sender ) 
			recv = (int)(Math.random()*nodes.size());
		return new Pair<Node>(nodes.get(sender),nodes.get(recv));
	}

}
