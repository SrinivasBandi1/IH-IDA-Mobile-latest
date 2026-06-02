package com.intelehealth.api;

import static org.hamcrest.Matchers.notNullValue;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIServices {
	private static final String VISIT_PUSH_ENDPOINT = "https://pathqa.intelehealth.org/EMR-Middleware/webapi/push/pushdata";
	private static final String START_VISIT_ENDPOINT = "https://pathqa.intelehealth.org/openmrs/ws/rest/v1/encounter";
	private static final String SIGN_AND_SUBMIT_ENDPOINT = "https://pathqa.intelehealth.org/openmrs/ws/rest/v1/encounter";
	private static final String SEARCH_PATIENT_ENDPOINT = "https://pathqa.intelehealth.org/openmrs/ws/rest/v1/patient?q=%s&v=custom:(uuid,identifiers:(identifierType:(name),identifier),person)";

	public static String createVisitUsingRestAssured(RequestSpecification request) {
		Response response = request.body(PayloadGenerator.createVisitUsingRestAssured()).post(VISIT_PUSH_ENDPOINT);
		System.out.println("======================================================================================================"+response.jsonPath().getString("data.patientlist[0].openmrs_id"));
		return response.jsonPath().getString("data.patientlist[0].openmrs_id");

	}

	public static void startVisitUsingRestAssured(RequestSpecification request) {
		request.body(PayloadGenerator.startVisitNote()).post(START_VISIT_ENDPOINT);
	}

	public static void signAndSubmitUsingRestAssured(RequestSpecification request) {
		request.body(PayloadGenerator.signAndSubmit()).post(SIGN_AND_SUBMIT_ENDPOINT);
	}

	public static void searchPatient(RequestSpecification request, String openMRSID) {
		request.get(String.format(SEARCH_PATIENT_ENDPOINT, openMRSID)).then().assertThat().body("results[0].uuid",
				notNullValue());
		;
	}
}
