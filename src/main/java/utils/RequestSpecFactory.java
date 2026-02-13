package utils;

import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
public final class RequestSpecFactory {
    private RequestSpecFactory() {}
    public static RequestSpecification defaultJsonSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("base.url"))

                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification formSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("base.url"))
                .setContentType("application/x-www-form-urlencoded; charset=UTF-8")
                .log(LogDetail.ALL)
                .build();
    }
}