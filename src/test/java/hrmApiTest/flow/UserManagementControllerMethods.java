package hrmApiTest.flow;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.*;

import static hrmApiTest.Util.URL;
import static hrmApiTest.Util.getSuperadminToken;
import static io.restassured.RestAssured.given;
public class UserManagementControllerMethods {

    private static final String[] FIRST_NAMES = {
        "John", "Emma", "Michael", "Sophia", "William", "Olivia", "James", "Ava", "Robert", "Isabella",
        // Add more names as needed
    };

    private static final String[] LAST_NAMES = {
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
        // Add more names as needed
    };

  

      // Method to generate email from first name and last name
      private static String generateEmail(String firstName, String lastName) {
        // Remove any spaces and convert to lowercase
        String cleanedFirstName = firstName.toLowerCase().trim().replaceAll("\\s", "");
        String cleanedLastName = lastName.toLowerCase().trim().replaceAll("\\s", "");
        
        // Concatenate first name, last name, and domain
        return cleanedFirstName + "." + cleanedLastName ;
    }
    public String createBritishCitizen(String businessOwnerToken,Integer times) throws JsonProcessingException {
        Random random = new Random();

        // Generate random first name
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];

        // Generate random last name
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        // Generate email from name
        String email = generateEmail(firstName, lastName);
        
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();

        
        requestBody.put("NI_number", "12121212");
        requestBody.put("first_Name", firstName);
        requestBody.put("last_Name", lastName);
        requestBody.put("email", (email + times + "_" + (Math.random() * 1000) + "@gmail.com"));
        requestBody.put("date_of_birth", "31-05-2010");
        requestBody.put("password", "12345678#We");
        requestBody.put("password_confirmation", "12345678#We");
        requestBody.put("phone", "01777777777");
        requestBody.put("address_line_1", "Dhaka, Bangladesh");
        requestBody.put("country", "Bangladesh");
        requestBody.put("city", "Dhaka");
        requestBody.put("lat", "23.804093");
        requestBody.put("long", "90.4152376");
        requestBody.put("role", "business_employee#1");
        requestBody.put("gender", "male");
        requestBody.put("user_id", "FAT-0002");
        requestBody.put("postcode", "1212");
        requestBody.put("work_shift_id", 69);
        requestBody.put("is_in_employee", 1);
        requestBody.put("designation_id", 2);
        requestBody.put("employment_status_id", 3);
        requestBody.put("salary_per_annum", "5000");
        requestBody.put("work_location_id", 1);
        requestBody.put("weekly_contractual_hours", "50");
        requestBody.put("minimum_working_days_per_week", "5");
        requestBody.put("overtime_rate", "5");
        requestBody.put("recruitment_processes", Arrays.asList(
            new HashMap<String, Object>() {{
                put("recruitment_process_id", 57);
                put("description", "jobsss");
                put("attachments", new ArrayList<>());
            }},
            new HashMap<String, Object>() {{
                put("recruitment_process_id", 56);
                put("description", "");
                put("attachments", new ArrayList<>());
            }}
            // Add more processes here...
        ));
        requestBody.put("joining_date", "31-05-2024");
        requestBody.put("address_line_2", "");
        requestBody.put("departments", Arrays.asList(1));
        requestBody.put("emergency_contact_details", Arrays.asList(
            new HashMap<String, Object>() {{
                put("relationship_of_above_to_you", "Father");
                put("first_Name", "Sheikh");
                put("last_Name", "muzibur Rahman");
                put("address_line_1", "Dhaka, Bangladesh");
                put("city", "Dhaka");
                put("country", "Bangladesh");
                put("postcode", "1212");
                put("lat", "23.804093");
                put("long", "90.4152376");
                put("mobile_tel_number", "01777777777");
            }},
            new HashMap<>() // Add more emergency contacts here...
        ));
        requestBody.put("immigration_status", "british_citizen");
        requestBody.put("sponsorship_details", new HashMap<String, Object>() {{
            put("date_assigned", "");
            put("expiry_date", "");
            put("status", "");
            put("note", "");
            put("certificate_number", "");
            put("current_certificate_status", "");
            put("is_sponsorship_withdrawn", 0);
        }});
        requestBody.put("is_active_visa_details", 0);
        requestBody.put("is_active_right_to_works", 0);
        requestBody.put("visa_details", new HashMap<String, Object>() {{
            put("BRP_number", "");
            put("visa_issue_date", "");
            put("visa_expiry_date", "");
            put("place_of_issue", "");
            put("visa_docs", new ArrayList<>());
        }});
        requestBody.put("right_to_works", new HashMap<String, Object>() {{
            put("right_to_work_code", "");
            put("right_to_work_check_date", "");
            put("right_to_work_expiry_date", "");
            put("right_to_work_docs", new ArrayList<>());
        }});
        requestBody.put("passport_details", new HashMap<String, Object>() {{
            put("passport_number", "");
            put("passport_issue_date", "");
            put("passport_expiry_date", "");
            put("place_of_issue", "");
        }});

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken) // Replace with your method to get the bearer token
                .body(requestBody)
                .when()
                .post(URL +"/api/v2.0/users") // Adjust the URL if needed
                .then()
                .extract()
                .response()
                .asString();

        System.out.println(response);
        
        return response;
    }





























    @Test
    public String testRegisterUserWithBusinessAPI(String superAdminToken,String businessOwnerEmail,String businessOwnerPassword)  {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user", new HashMap<>() {{
            put("first_Name", "Rifat");
            put("last_Name", "Al-Ashwad");
            put("email", businessOwnerEmail);
            put("password", businessOwnerPassword);
            put("password_confirmation", businessOwnerPassword);
            put("phone", "01771034383");
            put("image", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        }});

        requestBody.put("business", new HashMap<>() {{
            put("name", "ABCD business");
            put("about", "Best business in Dhaka");
            put("web_page", "https://www.facebook.com/");
            put("phone", "01771034383");
            put("email", businessOwnerEmail);
            put("phone", "01771034383");
            put("additional_information", "No Additional Information");
            put("address_line_1", "Dhaka");
            put("address_line_2", "Dinajpur");
            put("lat", "23.704263332849386");
            put("long", "90.44707059805279");
            put("country", "Bangladesh");
            put("city", "Dhaka");
            put("currency", "BDT");
            put("postcode", "Dinajpur");
            put("invoice_title", "invoice_title");
            put("footer_text", "footer_text");
            put("is_reference_manual", "1");
            put("receipt_footer", "t srt stgh st h");
            put("account_name", "thdht rth s");
            put("account_number", "fdghdgh");
            put("sort_code", "sort_coderthdrfth");
            put("pin", "1234");
//            put("type", "other");
            put("type", "property_dealer");
            put("logo", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        }});


        List<Map<String, Object>> billItems = new ArrayList<>();
        Map<String, Object> billItem1 = new HashMap<>();
        billItem1.put("bill_item_id", 12);
        billItems.add(billItem1);
        requestBody.put("bill_items", billItems);





        List<Map<String, Object>> saleItems = new ArrayList<>();
        Map<String, Object> saleItem1 = new HashMap<>();
        saleItem1.put("sale_id", "");
        saleItem1.put("item", "item");
        saleItem1.put("description", "description");
        saleItem1.put("amount", 10.1);
        Map<String, Object> saleItem2 = new HashMap<>();
        saleItem2.put("sale_id", "");
        saleItem2.put("item", "item");
        saleItem2.put("description", "description");
        saleItem2.put("amount", 10.1);
        saleItems.add(saleItem1);
        saleItems.add(saleItem2);
        requestBody.put("sale_items", saleItems);




        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + superAdminToken)
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/auth/register-with-business")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;

    }


    @Test
    public void testUpdateUserWithBusinessAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> userRequestBody = new HashMap<>();
        userRequestBody.put("id", 2);
        userRequestBody.put("first_Name", "Rifat");
        userRequestBody.put("last_Name", "Al-Ashwad");
        userRequestBody.put("email", ("rifatalashwad") + Math.random() +("@gmail.com"));
        userRequestBody.put("password", "12345678");
        userRequestBody.put("password_confirmation", "12345678");
        userRequestBody.put("phone", "01771034383");
        userRequestBody.put("image", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");

        Map<String, Object> businessRequestBody = new HashMap<>();
        businessRequestBody.put("id", 1);
        businessRequestBody.put("name", "ABCD business");
        businessRequestBody.put("about", "Best business in Dhaka");
        businessRequestBody.put("web_page", "https://www.facebook.com/");
        businessRequestBody.put("phone", "01771034383");
        businessRequestBody.put("email", ("rifatalashwad") + Math.random() +("@gmail.com"));
        businessRequestBody.put("additional_information", "No Additional Information");
        businessRequestBody.put("address_line_1", "Dhaka");
        businessRequestBody.put("address_line_2", "Dinajpur");
        businessRequestBody.put("lat", "23.704263332849386");
        businessRequestBody.put("long", "90.44707059805279");
        businessRequestBody.put("country", "Bangladesh");
        businessRequestBody.put("city", "Dhaka");
        businessRequestBody.put("postcode", "Dinajpur");
        businessRequestBody.put("invoice_title", "invoice_title");
        businessRequestBody.put("footer_text", "footer_text");
        businessRequestBody.put("is_reference_manual", "1");
        businessRequestBody.put("receipt_footer", "receipt_footer");
        businessRequestBody.put("account_name", "thdht rth s");
        businessRequestBody.put("account_number", "fdghdgh");
        businessRequestBody.put("sort_code", "sort_coderthdrfth");
        businessRequestBody.put("pin", "1234");
        businessRequestBody.put("type", "other");
        businessRequestBody.put("logo", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        businessRequestBody.put("image", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user", userRequestBody);
        requestBody.put("business", businessRequestBody);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/auth/update-user-with-business") // Assuming you have the correct URL
                .then()
                .statusCode(201) // Assuming that a successful response should return a status code of 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testUserToggleActiveAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 2);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/users/toggle-active")
                .then()
                .statusCode(200) // Ensure a successful response code
                .extract()
                .response().asString();



        System.out.println(response);
    }

    @Test
    public void testDeleteUserByIdAPI(String superAdminToken,Integer id) throws JsonProcessingException {
        // Prepare the request


        // Make the request
        String  response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + superAdminToken)
                .header("password", "12345678We")
                .when()
                .delete(URL + "/api/v1.0/users/" + id)
                .then()
                .statusCode(200) // Ensure a successful response code
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }

}
