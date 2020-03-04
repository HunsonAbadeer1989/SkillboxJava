import javax.persistence.*;
import javax.xml.crypto.Data;

@Entity
@Table(name = "Subscriptions")
public class Subscriptions
{
    @EmbeddedId
    private int studentId;
    @EmbeddedId
    private int teacherId;

    @Column(name="subscription_date")
    private Data subscriptionDate;

    public Data getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Data subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
