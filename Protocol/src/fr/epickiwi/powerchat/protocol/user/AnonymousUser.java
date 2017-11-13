package fr.epickiwi.powerchat.protocol.user;

public class AnonymousUser extends User{

    private static final String BASE_USERNAME = "Anonymous#";

    private final int discriminant;

    public AnonymousUser(int discriminant) {
        super(BASE_USERNAME+discriminant);
        this.discriminant = discriminant;
    }

    public int getDiscriminant() {
        return discriminant;
    }
}
