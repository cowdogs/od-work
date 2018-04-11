/**
 * 
 */
package net.odcorp.idms.batch.rms_sync;

import net.odcorp.idms.rms.RmsFileHelper;
import net.odcorp.idms.batch.rms_sync.action.DoNothingAction;
import net.odcorp.idms.batch.rms_sync.action.SyncAction;
import net.odcorp.idms.rms.BatchFileState;
import net.odcorp.idms.rms.RmsBatchFile;
import net.odcorp.idms.step.BgpState;
import net.odcorp.idms.step.StepBgp;
import net.odcorp.idms.step.StepBgpHelper;

/**
 * @author cowdogs
 *
 */
public class _RunRms2StepSync {
	private static int JOB_SUCCESS = 0;

	/**
	 * 
	 */
	public _RunRms2StepSync() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		if (execute() != 0) {
			throw new Exception("Job Failed");
		} else {
			System.exit(0);
		}
	}

	private static int execute() throws Exception {
		StepBgpHelper sph = StepBgpHelper.getInstance();
		RmsFileHelper rfh = RmsFileHelper.getInstance();
		//
		StepBgp stepBgp = sph.getLatestProcess();
		RmsBatchFile rmsFile = rfh.getLatestFile();
		//
		SyncAction nextAction = getNextAction(stepBgp, rmsFile);
		while (nextAction != null) {
			nextAction.execute();
			nextAction = nextAction.nextAction();
		}
		//
		return JOB_SUCCESS;
	}

	private static SyncAction getNextAction(StepBgp stepBgp, RmsBatchFile rmsFile) throws Exception {
		BatchFileState rmsState = rmsFile.getState();
		BgpState stepState = stepBgp.getState();
		//
		// SUCCEEDED, WAITING, IN_PROGRESS, SUCCEEDED_ERRORS,SUSPENDED, NO_BGP_EXISTS
		// SUCCEEDED, BGP_SUBMITTED, IN_PROGRESS, SUCCEEDED_ERRORS, NO_ROW_EXISTS
		switch (stepState) {
		case SUCCEEDED:
			switch (rmsState) {
			case SUCCEEDED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case BGP_SUBMITTED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case IN_PROGRESS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case SUCCEEDED_ERRORS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case NO_ROW_EXISTS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			default:
				throw new Exception("Invalid State: " + rmsState);
			}
		case WAITING:
			switch (rmsState) {
			case SUCCEEDED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case BGP_SUBMITTED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case IN_PROGRESS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case SUCCEEDED_ERRORS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case NO_ROW_EXISTS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			default:
				throw new Exception("Invalid State: " + rmsState);
			}
		case IN_PROGRESS:
			switch (rmsState) {
			case SUCCEEDED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case BGP_SUBMITTED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case IN_PROGRESS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case SUCCEEDED_ERRORS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case NO_ROW_EXISTS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			default:
				throw new Exception("Invalid State: " + rmsState);
			}
		case SUCCEEDED_ERRORS:
			switch (rmsState) {
			case SUCCEEDED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case BGP_SUBMITTED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case IN_PROGRESS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case SUCCEEDED_ERRORS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case NO_ROW_EXISTS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			default:
				throw new Exception("Invalid State: " + rmsState);
			}
		case SUSPENDED:
			switch (rmsState) {
			case SUCCEEDED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case BGP_SUBMITTED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case IN_PROGRESS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case SUCCEEDED_ERRORS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case NO_ROW_EXISTS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			default:
				throw new Exception("Invalid State: " + rmsState);
			}
		case NO_BGP_EXISTS:
			switch (rmsState) {
			case SUCCEEDED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case BGP_SUBMITTED:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case IN_PROGRESS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case SUCCEEDED_ERRORS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			case NO_ROW_EXISTS:
				return DoNothingAction.getInstance(stepBgp, rmsFile);
			default:
				throw new Exception("Invalid State: " + rmsState);
			}
		default:
			throw new Exception("Invalid State: " + stepState);
		}
	}
}
