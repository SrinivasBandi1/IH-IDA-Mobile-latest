package com.intelehealth.api;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PayloadGenerator {
	private static final String GENERATED_UUID = generateUUID();
	private static final String PERSON_UUID = generateUUID();
	private static final String ENCOUNTER_ONE_UUID = generateUUID();
	private static final String ENCOUNTER_TWO_UUID = generateUUID();
	private static final String VISIT_UUID = generateUUID();
	private static String ENCOUNTER_DATE_AND_TIME = generateEncounterDatetime();
	private static final String LOCATION_UUID = "9172f0c5-2a6d-43ba-84f8-37276a2db14b";

	

	String base64CredentialsCreatePatient = "bnVyc2UxOk51cnNlMTIz";
	String base64CredentialsCreateVisit = "bnVyc2UxOk51cnNlMTIz";

	
	
	protected static Map<String, Object> createObservation(String concept, String uuid, String value) {
		Map<String, Object> observation = new HashMap<>();
		observation.put("concept", concept);
		observation.put("uuid", uuid);
		observation.put("value", value);
		return observation;
	}

	protected static Map<String, Object> createAttributesObservation(String attributeType, String value) {
		Map<String, Object> observation = new HashMap<>();
		observation.put("attributeType", attributeType);
		observation.put("value", value);
		return observation;
	}
	
	
	// API METHODS
//CREATE VISIT 
	public  static Map<String, Object> createVisitUsingRestAssured() {
		List<Object> appointments = new ArrayList<>();

		// Encounters
		List<Map<String, Object>> encounters = new ArrayList<>();

		// First Encounter for encounterDatetime
		Map<String, Object> firstEncounter = new LinkedHashMap<>();
		firstEncounter.put("encounterDatetime", ENCOUNTER_DATE_AND_TIME);

		// Encounter providers array
		List<Map<String, Object>> encounterProviders = new ArrayList<>();
		Map<String, Object> encounterProvider = new LinkedHashMap<>();
		encounterProvider.put("encounterRole", "73bbb069-9781-4afc-a9d1-54b6b2270e04");
		encounterProvider.put("provider", "612322d6-8b80-4027-af3a-c2805bd32007");

		encounterProviders.add(encounterProvider);

		firstEncounter.put("encounterProviders", encounterProviders);

		firstEncounter.put("encounterType", "67a71486-1a54-468f-ac3e-7091a9a79584");
		// firstEncounter.put("location", "eb374eaf-430e-465e-81df-fe94c2c515be");
		firstEncounter.put("location", LOCATION_UUID);
		// OBS
		List<Map<String, Object>> obs = new ArrayList<>();
		// Creating Observations
		obs.add(createObservation("5090AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", generateUUID(), "66"));
		obs.add(createObservation("5089AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", generateUUID(), "5"));
		obs.add(createObservation("5087AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", generateUUID(), "100"));
		obs.add(createObservation("5085AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", generateUUID(), "100"));
		obs.add(createObservation("5086AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", generateUUID(), "100"));
		obs.add(createObservation("5088AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", generateUUID(), "37.78"));
		obs.add(createObservation("5242AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", generateUUID(), "10"));
		obs.add(createObservation("5092AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", generateUUID(), "100"));
		firstEncounter.put("obs", obs);
		firstEncounter.put("patient", PERSON_UUID);
		firstEncounter.put("uuid", ENCOUNTER_ONE_UUID);
		firstEncounter.put("visit", VISIT_UUID);
		firstEncounter.put("voided", 0);
		encounters.add(firstEncounter);
		// Second Encounter
		Map<String, Object> secondEncounter = new LinkedHashMap<>();
		secondEncounter.put("encounterDatetime", ENCOUNTER_DATE_AND_TIME);

		List<Map<String, Object>> encounterProviders2 = new ArrayList<>();
		Map<String, Object> encounterProviderDetails = new LinkedHashMap<>();
		encounterProviderDetails.put("encounterRole", "73bbb069-9781-4afc-a9d1-54b6b2270e04");
		encounterProviderDetails.put("provider", "612322d6-8b80-4027-af3a-c2805bd32007");
		encounterProviders2.add(encounterProviderDetails);

		secondEncounter.put("encounterProviders", encounterProviders2);
		secondEncounter.put("encounterType", "8d5b27bc-c2cc-11de-8d13-0010c6dffd0f");
		secondEncounter.put("location", LOCATION_UUID);
		// for nas
		// secondEncounter.put("location", "90bde499-9548-4ab0-8849-8f288ad82109");

		List<Map<String, Object>> obs2 = new ArrayList<>();
		obs2.add(createObservation("3edb0e09-9135-481e-b8f0-07a26fa9a5ce", generateUUID(),
				"►\u003cb\u003eCold, Sneezing\u003c/b\u003e: \u003cbr/\u003e• Precipitating factors - Cold weather.\u003cbr/\u003e• Prior treatment sought - None.\u003cbr/\u003e►\u003cb\u003e Associated symptoms\u003c/b\u003e:  \u003cbr/\u003e• Patient reports -\u003cbr/\u003e Itchy throat,  Nasal congestion/Stuffy nose \u003cbr/\u003e• Patient denies -\u003cbr/\u003e Body pain\u003cbr/\u003e "));
		obs2.add(createObservation("e1761e85-9b50-48ae-8c4d-e6b7eeeba084", generateUUID(),
				"\u003cb\u003eGeneral exams: \u003c/b\u003e\u003cbr/\u003e•  In-person consultation. \u003cbr/\u003e• Eyes: Jaundice-\u003cbr/\u003e• Eyes: Pallor-\u003cbr/\u003e• Arm-Pinch skin*\u003cbr/\u003e• Nail abnormality-\u003cbr/\u003e• Nail anemia-\u003cbr/\u003e• Ankle--"));
		obs2.add(createObservation("62bff84b-795a-45ad-aae1-80e7f5163a82", generateUUID(),
				"• Current Vaccinations status - Complete.\u003cbr/\u003e• Pregnancy status - Pregnancy status not known.\u003cbr/\u003e• Medical History - df.\u003cbr/\u003e• Drug history - No recent medication.\u003cbr/\u003e• Allergies - No known allergies.\u003cbr/\u003e• Chewing tobacco status - Do not Chew/denied answer.\u003cbr/\u003e• Smoking history - Patient denied/has no h/o smoking.\u003cbr/\u003e• Alcohol use - No/Denied.\u003cbr/\u003e"));
		obs2.add(createObservation("d63ae965-47fb-40e8-8f08-1f46a8a60b2b", generateUUID(),
				"•Do you have a family history of any of the following? :  daf.\u003cbr/\u003e"));

		secondEncounter.put("obs", obs2);

		secondEncounter.put("patient", PERSON_UUID);
		secondEncounter.put("uuid", ENCOUNTER_TWO_UUID);
		secondEncounter.put("visit", VISIT_UUID);
		secondEncounter.put("voided", 0);

		encounters.add(secondEncounter);

		List<Map<String, Object>> patients = new ArrayList<>();

		Map<String, Object> identifiers = new LinkedHashMap<String, Object>();
		identifiers.put("person", "46b03fb2-12d2-4a17-8dae-e697315b1623");
		// Map<String, Object> idetifiersData= new LinkedHashMap<>();
		List<Map<String, Object>> patientsData = new ArrayList<>();
		Map<String, Object> identifiersData = new LinkedHashMap<>();
		identifiersData.put("identifierType", "05a29f94-c0ed-11e2-94be-8c13b969e334");
		identifiersData.put("location", LOCATION_UUID);
		// identifiersData.put("location", "90bde499-9548-4ab0-8849-8f288ad82109");

		identifiersData.put("preferred", true);
		patientsData.add(identifiersData);
		identifiers.put("identifiers", patientsData);
		identifiers.put("person", PERSON_UUID);
		patients.add(identifiers);

		// persons
		List<Map<String, Object>> persons = new ArrayList<>();
		Map<String, Object> personsData = new LinkedHashMap<>();

		List<Map<String, Object>> addresses = new ArrayList<>();
		Map<String, Object> addressData = new LinkedHashMap<>();
		addressData.put("address1", "Address Line 1");
		addressData.put("address2", "Address Line 2");
		addressData.put("cityVillage", "Navi Mumbai:Ghansoli");
		addressData.put("country", "India");
		addressData.put("postalCode", "999999");
		addressData.put("stateProvince", "Maharashtra");

		addresses.add(addressData);
		personsData.put("addresses", addresses);
		// persons.add(personsData);

		List<Map<String, Object>> attributes = new ArrayList<>();
		Map<String, Object> attributesData = new LinkedHashMap<>();

		attributes.add(createAttributesObservation("14d4f066-15f5-102d-96e4-000c29c2a5d7", "+917777777777"));
		attributes.add(createAttributesObservation("1b2f34f7-2bf8-4ef7-9736-f5b858afc160", "Relationship"));
		attributes.add(createAttributesObservation("ecdaadb6-14a0-4ed9-b5b7-cfed87b44b87", "Occupation"));
		attributes.add(createAttributesObservation("5a889d96-0c84-4a04-88dc-59a6e37db2d3", "General"));
		attributes.add(createAttributesObservation("1c718819-345c-4368-aad6-d69b4c267db7", "Graduation & Higher"));
		attributes.add(createAttributesObservation("f4af0ef3-579c-448a-8157-750283409122", "APL"));
		attributes.add(createAttributesObservation("ffc8ebee-f70c-4743-bc3c-2fe4ac843245", "02 March, 2023"));
		attributes.add(createAttributesObservation("84f94425-789d-4293-a0d8-9dc01dbb4f07",
				"612322d6-8b80-4027-af3a-c2805bd32007"));
		/**
		 * For new visit created in IDA 5
		 */
		// attributes.add(createAttributesObservation("f9a1a024-9fb4-4eaa-8ac9-427284acdc0d",
		// "Test123"));
		// attributes.add(createAttributesObservation("9215385f-6770-4879-a4cb-6cdc840a682c",
		// "Test456"));
		// attributes.add(createAttributesObservation("15052692-da52-42ff-a962-11ceedeef228",
		// "Test147"));
		// attributes.add(createAttributesObservation("1d609ff3-6f45-44d3-a0b6-601976571979
		// ", "Test7645"));
		// attributes.add(createAttributesObservation("5b8f2081-8093-480e-8ca2-a8d68f9e7b5a",
		// "Test@gmail.com"));

		personsData.put("attributes", attributes);
		personsData.put("birthdate", "2023-03-02");
		personsData.put("gender", "M");
		List<Map<String, Object>> names = new ArrayList<>();
		Map<String, Object> namesData = new LinkedHashMap<>();
		namesData.put("familyName", "Srinivas");

		namesData.put("givenName", "Test");
		namesData.put("middleName", "Rest Assured");
		names.add(namesData);
		personsData.put("names", names);
		personsData.put("uuid", PERSON_UUID);
		persons.add(personsData);

		List<Map<String, Object>> providers = new ArrayList<>();
		List<Map<String, Object>> visits = new ArrayList<>();
		Map<String, Object> visitsData = new LinkedHashMap<>();

		List<Map<String, Object>> visitsAttributes = new ArrayList<>();
		Map<String, Object> visitsAttributesData = new LinkedHashMap<>();
		visitsAttributesData.put("attributeType", "3f296939-c6d3-4d2e-b8ca-d7f4bfd42c2d");
		visitsAttributesData.put("uuid", generateUUID());
		visitsAttributesData.put("value", "General Physician");

		Map<String, Object> visitsAttributesData2 = new LinkedHashMap<>();
		visitsAttributesData2.put("attributeType", "64aa50c8-e913-48c6-b8ad-dfa0bccb202b");
		visitsAttributesData2.put("uuid", generateUUID());
		visitsAttributesData2.put("value", "General Physician");

		visitsAttributes.add(visitsAttributesData);
		visitsAttributes.add(visitsAttributesData2);

		visitsData.put("attributes", visitsAttributes);
		visitsData.put("location", LOCATION_UUID);
		// visitsData.put("location", "90bde499-9548-4ab0-8849-8f288ad82109");

		visitsData.put("patient", PERSON_UUID);
		visitsData.put("startDatetime", ENCOUNTER_DATE_AND_TIME);
		visitsData.put("uuid", VISIT_UUID);
		visitsData.put("visitType", "a86ac96e-2e07-47a7-8e72-8216a1a75bfd");
		visits.add(visitsData);

		// identifiers.add(idetifiersData);
		// patients.add(idetifiersData);
		// patients.add(identifiers);

		// patients.add(identifiers);

		// Final Body
		Map<String, Object> requestBody = new LinkedHashMap<>();
		requestBody.put("appointments", appointments);
		requestBody.put("encounters", encounters);
		requestBody.put("patients", patients);
		requestBody.put("persons", persons);
		requestBody.put("providers", providers);
		requestBody.put("visits", visits);

		// Print the payload
	//	Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// Print the payload in a pretty JSON format
	//	System.out.println(gson.toJson(requestBody));
		
return requestBody;
	}
	public static Map<String, Object> startVisitNote() {
		System.out.println("personUUID = " + PERSON_UUID);
		System.out.println("encounterOneUUID = " + ENCOUNTER_ONE_UUID);

		System.out.println("encounterTwoUUID = " + ENCOUNTER_TWO_UUID);

		System.out.println("visitUUID = " + VISIT_UUID);
		System.out.println("encounterDateAndTime = " + ENCOUNTER_DATE_AND_TIME);
		Map<String, Object> patient = new LinkedHashMap<>();
		patient.put("patient", PERSON_UUID);
		patient.put("encounterType", "d7151f82-c1f3-4152-a605-2f9ea7414a79");
		List<Map<String, Object>> encounterProviders = new ArrayList<>();
		Map<String, Object> enProviderDetails = new LinkedHashMap<>();
		enProviderDetails.put("provider", "f3b2f923-ba9d-4ca5-a98d-abe23a512266");
		enProviderDetails.put("encounterRole", "73bbb069-9781-4afc-a9d1-54b6b2270e03");
		encounterProviders.add(enProviderDetails);
		patient.put("encounterProviders", encounterProviders);
		patient.put("visit", VISIT_UUID);
		patient.put("encounterDatetime", ENCOUNTER_DATE_AND_TIME);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// Print the payload in a pretty JSON format
		System.out.println(gson.toJson(patient));
		return patient;
	}

	
	public static String signAndSubmit() {
		 JSONObject payload = new JSONObject();

	        payload.put("patient", PERSON_UUID);
	        payload.put("encounterType", "bd1fbfaa-f5fb-4ebd-b75c-564506fc309e");
	        payload.put("visit", VISIT_UUID);
	        payload.put("encounterDatetime", ENCOUNTER_DATE_AND_TIME);

	        JSONArray encounterProviders = new JSONArray();
	        JSONObject providerObj = new JSONObject();
	        providerObj.put("provider", "f3b2f923-ba9d-4ca5-a98d-abe23a512266");
	        providerObj.put("encounterRole", "73bbb069-9781-4afc-a9d1-54b6b2270e03");
	        encounterProviders.put(providerObj);
	        payload.put("encounterProviders", encounterProviders);

	        JSONArray obsArray = new JSONArray();
	        JSONObject obsObj = new JSONObject();
	        obsObj.put("concept", "7a9cb7bc-9ab9-4ff0-ae82-7a1bd2cca93e");

	        // Escaped JSON as string
	        String valueJsonString = new JSONObject()
	                .put("name", "ANURAG R SANGALE")
	                .put("uuid", "f3b2f923-ba9d-4ca5-a98d-abe23a512266")
	                .put("fontOfSign", "Arty")
	                .put("whatsapp", "8830308535")
	                .put("registrationNumber", "12345678")
	                .put("consultationLanguage", "English")
	                .put("typeOfProfession", "MBBS")
	                .put("workExperience", "1")
	                .put("researchExperience", "1")
	                .put("textOfSign", "Anurag R Sangale")
	                .put("specialization", "General Physician")
	                .put("phoneNumber", "7720919153")
	                .put("countryCode", "91")
	                .put("emailId", "anurag.ecorise@gmail.com")
	                .put("workExperienceDetails", "10 YRS")
	                .put("signatureType", "Generate")
	                .put("signature", "data:image/png;base64,...") // Truncated for brevity
	                .toString();

	        obsObj.put("value", valueJsonString);
	        obsArray.put(obsObj);
	        payload.put("obs", obsArray);
	        // Send the request
	    //    System.out.println(payload.toString());
	        return payload.toString();
	    }
	
	private static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	protected static String generateEncounterDatetime() {
		// Get current date-time with UTC offset
		OffsetDateTime now = OffsetDateTime.now();

		// Define the formatter for ISO 8601 format: yyyy-MM-dd'T'HH:mm:ssX
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

		// Format and return the date-time as a String
		return now.format(formatter);
	}

	// Function to generate timestamp
	private static String generateTimestamp(int secondsAgo) {
		Date currentDate = new Date();
		long timestamp = currentDate.getTime() - (secondsAgo * 1000L);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
		return dateFormat.format(new Date(timestamp));
	}

	private static String generateUtcTimestamp(int secondsAgo) {
		Date currentDate = new Date();
		long timestamp = currentDate.getTime() - (secondsAgo * 1000L);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		return dateFormat.format(new Date(timestamp));
	}
	
}