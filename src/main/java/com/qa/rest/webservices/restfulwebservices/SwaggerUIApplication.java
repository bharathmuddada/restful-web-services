//package com.qa.rest.webservices.restfulwebservices;
//
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import io.swagger.parser.OpenAPIParser;
//import io.swagger.v3.parser.core.models.SwaggerParseResult;
//import io.swagger.v3.oas.models.OpenAPI;
//
//@SpringBootApplication
//public class SwaggerUIApplication {
//
//    public static void main(String[] args) {
//
//        // ... your code
//
//        // parse a swagger description from the petstore and get the result
//        SwaggerParseResult result = new OpenAPIParser().readLocation("https://petstore3.swagger.io/api/v3/openapi.json", null, null);
//
//        // or from a file
//        //   SwaggerParseResult result = new OpenAPIParser().readLocation("./path/to/openapi.yaml", null, null);
//
//        // the parsed POJO
//        OpenAPI openAPI = result.getOpenAPI();
//
//        if (result.getMessages() != null) result.getMessages().forEach(System.err::println); // validation errors and warnings
//
//        if (openAPI != null) {
//        }
//    }
//
//}
