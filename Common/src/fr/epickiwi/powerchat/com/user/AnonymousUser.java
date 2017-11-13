package fr.epickiwi.powerchat.com.user;

public class AnonymousUser extends User {

    public AnonymousUser(int id) {
        super("Anonymous#"+id, id);
    }

    @Override
    public String toString() {
        return "AnonymousUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
