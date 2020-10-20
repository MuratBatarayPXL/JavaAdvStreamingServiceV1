package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.InvalidDateException;

import java.time.LocalDate;

public class PaymentInfo {
    private CreditCardNumber cardNumber;
    private CreditCardType type;
    private String firstName;
    private String lastName;
    private LocalDate expirationDate;
    private int securityCode;

    public CreditCardNumber getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(CreditCardNumber cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CreditCardType getType() {
        return type;
    }

    public void setType(CreditCardType type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate.isBefore(LocalDate.now().plusMonths(1))){
            throw new InvalidDateException(expirationDate, "expiration date", "Expiration date expires within a month");
        } else {
            this.expirationDate = expirationDate;
        }
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
}
