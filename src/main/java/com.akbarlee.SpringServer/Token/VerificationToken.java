package com.akbarlee.SpringServer.Token;

import com.akbarlee.SpringServer.User.User;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "verification_token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private long token_id;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "expiry_date")
    private Timestamp expiryDate;


    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public VerificationToken(User user) {
        this.user = user;
        confirmationToken = UUID.randomUUID().toString();
         }

   public VerificationToken(String token, User user) {
        this.confirmationToken = token;
        this.user = user;
   }

    public VerificationToken() {

    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public long getToken_id() {
        return token_id;
    }

    public void setToken_id(long token_id) {
        this.token_id = token_id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getToken() {
        return confirmationToken;
    }

    public void setToken(String token) {
        this.confirmationToken = token;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
