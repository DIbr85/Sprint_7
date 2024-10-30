package models;

public class CourierCreds {
    private String login;
    private String password;

    public CourierCreds(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public static CourierCreds getCourierCreds(Courier courier) {
        return new CourierCreds(courier.getLogin(), courier.getPassword());
    }
    public static CourierCreds getCourierCredsWithNonexistentLogin(Courier courier) {
        return new CourierCreds("NonexistentLogin", courier.getPassword());
    }
    public static CourierCreds getCourierCredsWithNonexistentPassword(Courier courier) {
        return new CourierCreds(courier.getLogin(), "NonexistentPassword");
    }
}