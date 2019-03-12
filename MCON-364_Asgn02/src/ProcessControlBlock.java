
public class ProcessControlBlock {

	private IProcess process;
	private int instruction;
	private int reg1;
	private int reg2;
	private int reg3;
	private int reg4;
	
	public ProcessControlBlock(IProcess process) {
		this.process = process;
	}
	public IProcess getProcess() {
		return process;
	}
	public void setInsruction(int i) {
		instruction = i;
	}
	public int getInstruction() {
		return instruction;
	}
	public void setReg1(int i) {
		reg1 = i;
	}
	public int getReg1() {
		return reg1;
	}
	public void setReg2(int i) {
		reg2 = i;
	}
	public int getReg2() {
		return reg2;
	}
	public void setReg3(int i) {
		reg3 = i;
	}
	public int getReg3() {
		return reg3;
	}
	public void setReg4(int i) {
		reg4 = i;
	}
	public int getReg4() {
		return reg4;
	}
}
