package gui;

import java.util.Map;

public interface DoubleArrayInputAction {

	/**
	 * Performs an action to the window by using the input provided.
	 * 
	 * @param window
	 * @param input
	 */
	public void performAction(Window window, Map<String, Double> inputs);
}
