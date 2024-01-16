package com.restful.booker.crudtest;

import com.restful.booker.model.BookingDatesPojo;
import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CRUDTest extends TestBase {
    static ValidatableResponse response;
    static String Firstname = TestUtils.getRandomValue() + "Jin";
        static String Lastname = TestUtils.getRandomValue() + "Jin";
        static int Totalprice = 111 ;
        static  boolean Depositpaid = true;
    //BookingDatesPojo bookingDates = new BookingDatesPojo();
        static String Checkin = "2018-01-01";
        static String Checkout= "2019-01-01";
        static String Bookingdates;
        static String Additionalneeds = "Breakfast";

       @Test
    public void test03_CreateBooking() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(TestUtils.getRandomValue() + "Jin");
        bookingPojo.setLastname(TestUtils.getRandomValue() + "Jin");
        bookingPojo.setTotalprice(111);
        bookingPojo.setDepositpaid(true);
        BookingDatesPojo bookingDates = new BookingDatesPojo();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        bookingPojo.setBookingdates(String.valueOf(bookingDates));
        bookingPojo.setAdditionalneeds("Breakfast");

        Response response =
                given()
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .header("Content-Type","application/json")
                        .header("cookie" , "token=cfc999198079c38" )
                        .body(bookingPojo)
                        .when()
                        .post("/booking");
        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void test01_GetBookingIds() {
        response = given()
                .when()
                .get()
                .then().log().all().statusCode(200);
    }

    @Test
    public void test02_GetBooking() {
        response = given()
                .when()
                .get("/50")
                .then().log().all().statusCode(200);
    }

    @Test
    public void test003() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(TestUtils.getRandomValue() + "Sachin");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(bookingPojo)
                .put("/booking/50");
        response.then().log().all().statusCode(404);
    }

    @Test
    public void test004_PartialUpdateBooking() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(TestUtils.getRandomValue() + "James");
        bookingPojo.setLastname(TestUtils.getRandomValue() + "Brown");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(bookingPojo)
                .patch("/booking/50");
        response.then().log().all().statusCode(404);
    }

    @Test
    public void deleteBooking() {
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete("/booking/50");
        response.then().statusCode(201);
        response.prettyPrint();
    }

}
