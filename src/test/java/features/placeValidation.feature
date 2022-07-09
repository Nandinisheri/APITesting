Feature: Validating Place API'S


@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlace API
	Given Addplace payload with "<name>" "<language>" "<address>"
	When User calls "addPlaceAPI" using "post" http request
	Then the API call got success with status code 200
	And "status" in reponse body is "OK"
	And "scope" in reponse body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
	
Examples:
	| name | language | address |
	| Aahouse | English | Street Road France |
	#| Bahouse | Spanish | Sea cross center 

@DeletePlace @Regression
Scenario: Verify the Delete Place API is working
	Given DeletePlace PayLoad
	When User calls "deletePlaceAPI" using "post" http request
	Then the API call got success with status code 200
	And "status" in reponse body is "OK"