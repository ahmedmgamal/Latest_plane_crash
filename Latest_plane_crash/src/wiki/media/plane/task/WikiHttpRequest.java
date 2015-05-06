package wiki.media.plane.task;

public class WikiHttpRequest {
	private String method;
	private String location;
	private String id;
	private String body;
	
	public WikiHttpRequest(String requestString) {
		if(requestString.startsWith("GET")){
			this.setMethod("GET"); //to make enum later
		} else {
			
			this.setMethod("PUT"); //to make enum later
		}
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
