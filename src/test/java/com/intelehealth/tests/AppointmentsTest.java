package com.intelehealth.tests;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.AppointmentsPage;
import com.intelehealth.pages.FindPatientPage;
import com.intelehealth.utils.TestUtils;

public class AppointmentsTest extends BaseTest{
//	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	FindPatientPage findPatientPage;
	AppointmentsPage appointmentsPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws Throwable {
		TestUtils.log().info("\n" + "****** starting test : " + m.getName() + "******" + "\n");
		resetApp();
		launchApp();
		appSetupPage = new AppSetupPage();
		findPatientPage = new FindPatientPage();
		appointmentsPage= new AppointmentsPage(driver);
		InputStream datais = null;
		try {
			String dataFileName = "data/appData.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			appData = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				datais.close();
			}
		}
		//grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
        appSetupPage.completeSetup();
		appSetupPage.refreshUIAndWait();

}
	@Test(priority = 1, description = "Verify clicking on visit under completed section", enabled = true)
	public void IDA4_2397_verifyAppointmentDetailsScreenIsDisplayed() throws Throwable {
	    // Verify that the appointment details screen is displayed when clicking on 'Visit' under the completed section
	    appointmentsPage.verifyAppointmentDetailsScreenIsDisplayed();
	}

	@Test(priority = 2, description = "Verify the overall All tab section", enabled = true)
	public void IDA4_2404_verifyOverAllTabSection() throws Throwable {
	    // Verify the overall All tab section on the appointments page
	    appointmentsPage.verifyAllTab();
	}

	@Test(priority = 3, description = "Verify when user selects specific radio button for appointments filter", enabled = true)
	public void IDA4_2406_verifySpecificRadioButtonForAppointments() throws Throwable  {
	    // Verify that specific radio buttons for appointments filter work as expected
	    appointmentsPage.verifyAppointmentsRadioButton();
	}

	@Test(priority = 4, description = "Verify user can select the FROM and TO dates from calendar filter in All tab section", enabled = true)
	public void IDA4_2408_verifyAllTabCalenderFilter() throws Throwable {
	    // Verify that the user can select FROM and TO dates from the calendar filter in the All tab section
	    appointmentsPage.verifyUserCanSelectFromAndToDate();
	}
	@Test(priority = 5, description = "Verify the count of cancelled tab", enabled = true)
	public void IDA4_2374_verifyCountOfCancelledTab() throws Throwable {
	   appointmentsPage.verifyCountOfCancelledTab();
	}
	@Test(priority = 6, description = "Verify the functionality of 'Cancelled' tab", enabled = true)
	public void IDA4_2373_verifyCancelledTab() throws Throwable {
	   
	   appointmentsPage.verifyCancelledTabFunctionality();
	}
	@Test(priority = 7, description = "Verify when user clicks on cancel appointment", enabled = true)
	public void IDA4_2380_verifyCancelAppointment() throws Throwable {
	   
	   appointmentsPage.verifyCancelButton();
	}
	@Test(priority = 8, description = "Verify the Yes button functionality in cancel appointment popup", enabled = true)
	public void IDA4_2382_verifyYesButtonFunctionality() throws Throwable {
	   
	   appointmentsPage.verifyYesButton();
	}
	
	@Test(priority = 9, description = "Verify the functionality of 'Completed' tab", enabled = true)
	public void IDA4_2391_verifyCompletedTabFunctionality() throws Throwable {
	   
	   appointmentsPage.verifyCompletedTab();
	}
	@Test(priority = 10, description = "Verify user can cancel the appointment by clicking save", enabled = true)
	public void IDA4_2385_verifycancelAppointmentSaveButton() throws Throwable {
	   
	   appointmentsPage.verifyAppointmentSaveButton();
	}
	
	@Test(priority = 11, description = "Verify clicking on Schedule appointment CTA for a cancelled appointment", enabled = true)
	public void IDA4_2388_verifyScheduleAppointmentScreenIsDisplayed() throws Throwable {
	   
	   appointmentsPage.verifySchedulaAppointmentPageIsDisplayed();
	}
	@Test(priority = 12, description = "Verify the Cancelled appointment under Cancelled section and its tab", enabled = true)
	public void IDA4_2386_verifyCancelledAppointment() throws Throwable {
	   
	   appointmentsPage.verifyCancelledTab();
	}
	@Test(priority = 13, description = "Verify the cancelled section under cancelled tab", enabled = true)
	public void IDA4_2375_verifyCancelledSection() throws Throwable {
		appointmentsPage.verifyCancelledSection();
	   
}
	@Test(priority = 14, description = "Verify if only Today's date appointments are showing under Today's tab", enabled = true)
	public void IDA4_2341_verifyIfOnlyTodaysDateAppointmentsAreShowingUnderTodaysTab() throws Throwable {
		appointmentsPage.verifyIfOnlyTodaysDateAppointmentsAreShowingUnderTodaysTab();
	}

	@Test(priority = 15, description = "Verify the functionality of upcoming tab", enabled = true)
	public void IDA4_2343_verifyTheFunctionalityOfUpcomingTab() throws Throwable {
		appointmentsPage.verifyTheFunctionalityOfUpcomingTab();
	}

	@Test(priority = 16, description = "Verify Cancelled section under Upcoming tab", enabled = true)
	public void IDA4_2344_verifyCancelledSectionUnderUpcomingTab() throws Throwable {
		appointmentsPage.verifyCancelledSectionUnderUpcomingTab();
	}

	@Test(priority = 17, description = "Verify Completed section under Upcoming tab", enabled = true)
	public void IDA4_2345_verifyCompletedSectionUnderUpcomingTab() throws Throwable {
		appointmentsPage.verifyCompletedSectionUnderUpcomingTab();
	}

	@Test(priority = 18, description = "Verify the count of Upcoming tab", enabled = true)
	public void IDA4_2346_verifyTheCountOfUpcomingTab() throws Throwable {
		appointmentsPage.verifyTheCountOfUpcomingTab();
	}

	@Test(priority = 19, description = "Verify the patient visit details in Upcoming section", enabled = true)
	public void IDA4_2352_verifyThePatientVisitDetailsInUpcomingSection() throws Throwable {
		appointmentsPage.verifyThePatientVisitDetailsInUpcomingSection();
	}

	@Test(priority = 20, description = "Verify clicking on call and whatsapp icon on appointment details page when number is provided", enabled = true)
	public void IDA4_2356_verifyClickingOnCallAndWhatsappIconAnAppointmentDetailsPageWhenNumberIsProvided() throws Throwable {
		appointmentsPage.verifyClickingOnCallAndWhatsappIconAnAppointmentDetailsPageWhenNumberIsProvided();
	}
	
	@Test(priority = 21, description = "Verify user clicking on arrow next to visit summary on appointment details page", enabled = true)
	public void IDA4_2357_verifyUserClickingOnArrowNextToVisitSummaryOnAppointmentDetailsPage() throws Throwable {
		appointmentsPage.verifyUserClickingOnArrowNextToVisitSummaryOnAppointmentDetailsPage();
	}
	
	@Test(priority = 22, description = "Verify the functionality of Reschedule in Appointment details page", enabled = true)
	public void IDA4_2359_verifyTheFunctionalityOfRescheduleInAppointmentDetailsPage() throws Throwable {
		appointmentsPage.verifyTheFunctionalityOfRescheduleInAppointmentDetailsPage();
	}
	
	@Test(priority = 23, description = "Verify user gets the select Reschedule reason popup by clicking yes", enabled = true)
	public void IDA4_2361_verifyUserGetsTheSelectRescheduleReasonPopupByClickingYes() throws Throwable {
		appointmentsPage.verifyUserGetsTheSelectRescheduleReasonPopupByClickingYes();
	}
	
	@Test(priority = 24, description = "Verify clicking on Save button by selecting reason in Reschedule reason popup", enabled = true)
	public void IDA4_2364_verifyClickingOnSaveButtonBySelectingReasonInRescheduleReasonPopup() throws Throwable {
		appointmentsPage.verifyClickingOnSaveButtonBySelectingReasonInRescheduleReasonPopup();
	}
	
	@Test(priority = 25, description = "Verify selecting any time slots & clicking on book appointment", enabled = true)
	public void IDA4_2367_verifySelectingAnyTimeSlotsAndClickingOnBookAppointment() throws Throwable {
		appointmentsPage.verifySelectingAnyTimeSlotsAndClickingOnBookAppointment();
	}
	
	@Test(priority = 26, description = "Verify the confirm appointment popup", enabled = true)
	public void IDA4_2368_verifyTheConfirmAppointmentPopup() throws Throwable {
		appointmentsPage.verifyTheConfirmAppointmentPopup();
	}
	
	@Test(priority = 27, description = "Verify when user clicks Yes in confirm appointment popup", enabled = true)
	public void IDA4_2370_VerifyWhenUserClicksYesInConfirmAppointmentPopup() throws Throwable {
		appointmentsPage.verifyTheConfirmAppointmentPopup();
		appointmentsPage.verifyWhenUserClicksYesInConfirmAppointmentPopup();
	}

	@Test(priority = 28, description = "Verify Rescheduled appointment displays under Today's tab of upcoming section", enabled = true)
	public void IDA4_2371_verifyRescheduledAppointmentDisplaysUnderTodaysTabOfUpcomingSection() throws Throwable {
		appointmentsPage.verifyRescheduledAppointmentDisplaysUnderTodaysTabOfUpcomingSection();
	}
	
	@Test(priority = 29, description = "Verify user can schedule the Appointment successfully", enabled = true)
	public void IDA4_2389_verifyUserCanScheduleTheAppointmentSuccessfully() throws Throwable {
		appointmentsPage.verifyUserCanScheduleTheAppointmentSuccessfully();
	}
	
}
