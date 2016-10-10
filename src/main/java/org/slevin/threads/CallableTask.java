package org.slevin.threads;


public class CallableTask{
	 
//	Vector<byte[]> afidList = new Vector<byte[]>();
//	FaceID sdk;
//	byte[] query;
//	String sdkPath;
//	
//	
//public CallableTask(FaceID sdk,String sdkPath, byte[] query,List<byte[]> afidList) {
//		super();
//		this.afidList.addAll(afidList);
//		//this.sdk = sdk;
//		this.query=query;
//		this.sdkPath = sdkPath;
//		//this.sdk = new FaceID(sdkPath);
//		this.sdk =sdk;
//	}
//
//
//
//@Override
//public ResultDto call() throws Exception {
//	try {
////		System.out.println(sdkPath);
////
////		System.out.println(sdkPath + "olustu");
//		
//		float[] scores = new float[afidList.size()];
//		int[] indexes = new int[afidList.size()];
//		
//		Date startDate = new Date();
//		sdk.MatchAfids(query, afidList, scores, indexes);
//
//		Date endDate = new Date();
//		long s = endDate.getTime()-startDate.getTime();
//		System.out.println(Thread.currentThread()+" "+s+" hepsi tamamlandi.size="+afidList.size()+" startDate:"+startDate.getTime());
//		
//		int index = sort(scores);
//		float score  = scores[index];
//		byte[] afid = afidList.get(index);
//		
//		ResultDto dto = new ResultDto();
//		dto.setAfid(afid);
//		dto.setScore(score);
//		
//		
//		
//		return dto;
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	return null;
//}
//
//public static int sort(float[] scores){
//	
//	float value = Float.MIN_VALUE;
//	int index = Integer.MIN_VALUE;
//	
//	for(int i =0;i<scores.length;i++) {
//	
//	            if(scores[i] > value) {
//	            	value = scores[i];
//
//	            	index = i;
//
//	            }
//	
//	        }
//
//	return index;
//}


}
