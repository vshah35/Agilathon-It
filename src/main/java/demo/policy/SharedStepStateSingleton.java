package demo.policy;

public class SharedStepStateSingleton {

	private static SharedStepState instance = null;

	public SharedStepStateSingleton() {
	}

	public static SharedStepState getInstance() {
		if (instance == null) {
			instance = new SharedStepState();
		}
		return instance;
	}

	public static void reset() {
		instance = new SharedStepState();
	}

}
