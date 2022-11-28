import java.util.*;
import java.lang.*;

public class Factory {
	private int factoryID;
	private ArrayList<Integer> factoryTiles;

	
	public Factory(int factoryID) {
		this.factoryID = factoryID;
		factoryTiles = new ArrayList<Integer>();
	}
	
	public int getFactoryID() {
		return factoryID;
	}
	public boolean isEmpty() {
		return factoryTiles.size() == 0;
	}
	public ArrayList<Integer> getFactoryTiles() {
		return factoryTiles;
	}
  
}
