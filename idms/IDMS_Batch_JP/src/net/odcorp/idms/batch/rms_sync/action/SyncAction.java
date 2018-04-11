package net.odcorp.idms.batch.rms_sync.action;

public interface SyncAction {
	
	public void execute();
	
	public SyncAction nextAction();

}
