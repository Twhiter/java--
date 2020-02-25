package refactor;

import graph.GenerifiedEdge;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author T.Whiter
 * @Date 2020/2/24 10:27
 * @Version 1.0
 */
public class UIEdge extends GenerifiedEdge<SimpleStringProperty> {
    private SimpleDoubleProperty weight;

    public UIEdge() {
        super(new SimpleStringProperty(),new SimpleStringProperty(),0);
        weight = new SimpleDoubleProperty(0);
    }

    public UIEdge(SimpleStringProperty from, SimpleStringProperty to, double weight) {
        super(from, to, weight);
        this.weight = new SimpleDoubleProperty(weight);
    }

    public UIEdge(String from,String to,double weight) {
        super(new SimpleStringProperty(from),new SimpleStringProperty(to),weight);
        this.weight = new SimpleDoubleProperty(weight);
    }

    public void setWeight(double weight) {
        super.setWeight(weight);
        this.weight.set(weight);
    }

    public SimpleDoubleProperty weightProperty() {
        return weight;
    }

    public GenerifiedEdge<String> toGenerifiedEdge() {
        return new GenerifiedEdge<>(getFrom().getValue(),getTo().getValue(),weight.doubleValue());
    }

}
