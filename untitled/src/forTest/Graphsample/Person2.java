package forTest.Graphsample;

/**
 * @author T.Whiter
 * @Date 2020/2/24 9:37
 * @Version 1.0
 */
public class Person2 {
    private String firstName;
    private double ID;


    public double getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setID(double ID) {
        this.ID = ID;
    }

    public Person2(){

    }

    public Person2(String firstName,double ID) {
        this.firstName = firstName;
        this.ID = ID;
    }
}
