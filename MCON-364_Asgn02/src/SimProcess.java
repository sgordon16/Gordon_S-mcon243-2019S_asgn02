
public class SimProcess implements IProcess {
	
	private IRandomValueGenerator rand;
	private int pid;
	private String procName;
	private int totalInstructions;
	
	public SimProcess(int pid, String procName, int totalInstructions, IRandomValueGenerator rand) {
		this.pid = pid;
		this.procName = procName;
		this.totalInstructions = totalInstructions;
		this.rand = rand;
	}

	@Override
	public int getPid() {
		return pid;
	}

	@Override
	public String getProcName() {
		return procName;
	}

	@Override
	public ProcessState execute(int i) {
		System.out.println("PID: " + pid + " Name: " + procName + " Instruction Number: " + i);
		if(i >= totalInstructions)
			return ProcessState.FINISHED;
		else if(rand.getTrueWithProbability(.15))
			return ProcessState.BLOCKED;
		else
			return ProcessState.READY;
	}

}
