package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.util.PasswordUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    private static final int MINIMUM_PASSWORD_STRENGTH = 6;
    private String email;
    private String password;
    private PaymentInfo paymentInfo;
    private StreamingPlan streamingPlan;
    private List<Profile> profiles = new ArrayList<>();

    public Account(String email, String password) {
        if (email.equals("") || password.equals("")){
            throw new IllegalArgumentException();
        }
        else {
            this.email = email;
            setPassword(password);
            profiles.add(new Profile("Profile 1"));
            this.streamingPlan = StreamingPlan.BASIC;
        }
    }

    public void setStreamingPlan(StreamingPlan streamingPlan) {
        this.streamingPlan = streamingPlan;
    }

    public void addProfile(Profile profile) {
        if (getNumberOfProfiles() == streamingPlan.getNumberOfScreens()){
            throw new IllegalArgumentException();
        } else {
            profiles.add(profile);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (matcher.find()){
            this.email = email;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public boolean verifyPassword(String password) {
        return PasswordUtil.isValid(password, this.password);
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public void setPassword(String password) {
        if (password.length() < MINIMUM_PASSWORD_STRENGTH){
            throw new IllegalArgumentException();
        } else {
            this.password = PasswordUtil.encodePassword(password);
        }
    }

    public int getNumberOfProfiles() {
        return profiles.size();
    }

    public List<Profile> getProfiles() {
        Collections.sort(profiles);
        return profiles;
    }
}
