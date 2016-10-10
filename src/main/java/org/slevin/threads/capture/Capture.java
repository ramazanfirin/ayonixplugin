package org.slevin.threads.capture;

public interface Capture {
	void intitialize();
	byte[] screenshot() throws Exception;
	void faceIdentify() throws Exception;
	public void requestStop();
	void stopCamera();
}
