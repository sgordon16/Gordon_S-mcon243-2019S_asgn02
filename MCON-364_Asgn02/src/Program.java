import java.util.LinkedList;

public class Program {

	public static void main(String[] args) {
		
		RandomValueGenerator rand = new RandomValueGenerator();
		SimProcessor processor;
		final int QUANTUM = 5;
		int instructCnt = 1;
		LinkedList<ProcessControlBlock> readyProcesses = new LinkedList<ProcessControlBlock>();
		LinkedList<ProcessControlBlock> blockedProcesses = new LinkedList<ProcessControlBlock>();
		ProcessControlBlock currPCB;
		
		//set up 10 PCB's
		int instructions = 50;
		for(int i = 0; i < 10; i++) {
			readyProcesses.add(new ProcessControlBlock(new SimProcess(i+1, "Proc" + Integer.toString(i+1), instructions += 35, rand)));
		}
        
		currPCB = readyProcesses.remove();
		processor = new SimProcessor(currPCB.getProcess(), rand);
        
        for(int i = 0; i < 3000; i++) {
        	
        	//if no processes left break
        	if(readyProcesses.isEmpty() && blockedProcesses.isEmpty()) {
        		System.out.println("\nJOB DONE!");
        		break;
        	}
        		
        	
        	ProcessState processState = processor.executeNextInstruction();
        	
        	if(processState == ProcessState.BLOCKED | processState == ProcessState.FINISHED | instructCnt == QUANTUM) {
        		
        		if(processState == ProcessState.BLOCKED) {
        			System.out.println(" *** PROCESS BLOCKED *** ");
        			updatePCB(currPCB, processor);
        			blockedProcesses.add(currPCB);
        		}
        		
        		else if(processState == ProcessState.FINISHED) {
        			System.out.println(" *** PROCESS FINISHED *** ");
        		}
        			
        		else if(instructCnt == QUANTUM) {
        			System.out.println(" *** QUANTUM EXPIRED *** ");
        			updatePCB(currPCB, processor);
        			readyProcesses.add(currPCB);	
        		}
   
        		while(readyProcesses.isEmpty()) {
        			System.out.println(" *** Processor idling, waiting for ready process *** ");
        			for(int x = 0; x < blockedProcesses.size(); x++) {
        				if(rand.getTrueWithProbability(.3))
        					readyProcesses.add(blockedProcesses.remove(x));
        			}
        		}
        		currPCB = readyProcesses.remove();
        		updateProcesser(processor, currPCB);     		
        		instructCnt = 1;
        	}
        	else {
        		instructCnt++;
        	}
        	for(int x = 0; x < blockedProcesses.size(); x++) {
				if(rand.getTrueWithProbability(.3))
					readyProcesses.add(blockedProcesses.remove(x));
			}
        }
	}
	//Update meta data in PCB from processor
	public static void updatePCB(ProcessControlBlock pcb, SimProcessor processor) {
		System.out.println("Context switch: Saving process: " + processor.getProcess().getPid());
		System.out.println("\tInstruction: " + processor.getCurrInstruction() + " R1: " +
				processor.getRegisterValue(0) + " R2: " + processor.getRegisterValue(1) + " R3: " +
				processor.getRegisterValue(2) + " R4: " + processor.getRegisterValue(3));
		pcb.setReg1(processor.getRegisterValue(0));
		pcb.setReg2(processor.getRegisterValue(1));
		pcb.setReg3(processor.getRegisterValue(2));
		pcb.setReg4(processor.getRegisterValue(3));
		pcb.setInsruction(processor.getCurrInstruction());
	}
	//Update meta data in processor from PCB
	public static void updateProcesser(SimProcessor processor, ProcessControlBlock readyProcess) {
		System.out.println("Restoring process: " + readyProcess.getProcess().getPid());
		System.out.println("\tInstruction: " + readyProcess.getInstruction() + " R1: " +
				readyProcess.getReg1() + " R2: " + readyProcess.getReg2() + " R3: " +
				readyProcess.getReg3() + " R4: " + readyProcess.getReg4());
		processor.setProcess(readyProcess.getProcess());
		processor.setRegisterValue(0, readyProcess.getReg1());
		processor.setRegisterValue(1, readyProcess.getReg2());
		processor.setRegisterValue(2, readyProcess.getReg3());
		processor.setRegisterValue(3, readyProcess.getReg4());
		processor.setCurrInstruction(readyProcess.getInstruction());
	
	}

}
