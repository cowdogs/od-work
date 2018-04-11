package net.odcorp.idms.batch.rms_sync.action;

import net.odcorp.idms.rms.RmsBatchFile;
import net.odcorp.idms.step.StepBgp;

public class DoNothingAction implements SyncAction {
	
	/**
	 * 
	 */
	private DoNothingAction() {
		super();
	}

	public static SyncAction getInstance(StepBgp stepBgp, RmsBatchFile rmsFile) {
		return new DoNothingAction();
	}

	@Override
	public void execute() {
		// do nothing
	}

	@Override
	public SyncAction nextAction() {
		return null;
	}

}
