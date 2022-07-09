package resources;

public enum APIResources {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	
	private String resources;

	APIResources(String resources) {
		// TODO Auto-generated constructor stub
		
		this.resources = resources;
	}
	
	public String getResources() {
		
		return resources;
	}

}
