
public class SimProcessor implements IProcessor {
	
	IRandomValueGenerator rand;
	IProcess process;
	private int[] regs = new int[4];
	private int currInstruction = 0;
	
	public SimProcessor(IProcess process, IRandomValueGenerator rand) {
		this.process = process;
		this.rand = rand;
	}

	@Override
	public IProcess getProcess() {
		return process;
	}
	@Override
	public void setProcess(IProcess process) {
		this.process = process;
	}

	@Override
	public ProcessState executeNextInstruction() {
		return process.execute(currInstruction++);
	}

	@Override
	public void setRegisterValue(int i, int val) {
		regs[i] = val;
	}

	@Override
	public int getRegisterValue(int i) {
		return rand.getNextInt();
	}
	public void setCurrInstruction(int i) {
		currInstruction = i;
	}
	public int getCurrInstruction() {
		return currInstruction;
	}
}
