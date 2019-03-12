
public interface IProcessor {

	IProcess getProcess();
	void setProcess(IProcess process);
	int getCurrInstruction();
	void setCurrInstruction(int i);
	ProcessState executeNextInstruction();
	void setRegisterValue(int i, int val);
	int getRegisterValue(int i);
}
