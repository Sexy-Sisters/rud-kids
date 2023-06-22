package com.rudkids.core.delivery.domain;

import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.exception.DifferentUserException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_delivery")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "delivery_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String receiverName;

    private String receiverPhone;

    @Embedded
    private Address address;

    private String message;

    private boolean isBasic = false;

    @Builder
    public Delivery(String receiverName, String receiverPhone, Address address, String message) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.address = address;
        this.message = message;
    }

    public void registerUser(User user) {
        this.user = user;
        user.addDeliveryAddress(this);
    }

    public void changeBasic() {
        isBasic = true;
    }

    public String getZipCode() {
        return address.getZipCode();
    }

    public String getAddress1() {
        return address.getAddress1();
    }

    public String getAddress2() {
        return address.getAddress2();
    }

    public void validateHasSameUser(User user) {
        if(!this.user.equals(user)) {
            throw new DifferentUserException();
        }
    }

    public void update(String receiverName, String receiverPhone, Address address, String message) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.address = address;
        this.message = message;
    }
}