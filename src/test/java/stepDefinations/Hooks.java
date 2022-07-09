package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {

		StepDefination sd = new StepDefination();

		if (StepDefination.placeId == null) {
			sd.addplace_payload_with_something_something_something("Shetty", "English", "Palace Road France");
			sd.user_calls_something_using_something_http_request("addPlaceAPI", "post");
			sd.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
		}
	}
}
