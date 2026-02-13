package utils;

import api.models.User;

public class TestDataFactory {

    private static long counter = System.currentTimeMillis() % 1_000_000;

    public static String uniqueEmail(){
        return "testUser-" + (counter++) + "@example.com";
    }


    public static String uniqueName(){
        return "testUserName-" + (counter++);
    }

    public static User createDefaultUser() {
        return new User(
                uniqueName(),
                uniqueEmail(),
                "Password123!",
                "Mr",
                "15",
                "6",
                "1995",
                "John",
                "Doe",
                "TestCorp Inc",
                "123 Main Street",
                "Apt 4B",
                "United States",
                "10001",
                "New York",
                "New York",
                "1234567890"
        );
    }

    public static User createUpdatedUser(User original) {
        return new User(
                original.getName(),
                original.getEmail(),
                original.getPassword(),
                "Mr",
                "20",
                "12",
                "1990",
                "John",
                "Smith",
                "UpdatedCorp LLC",
                "123 Updated Avenue",
                "Suite 100",
                "Canada",
                "M5V3L9",
                "Ontario",
                "Toronto",
                "9876543210"
        );
    }
}
