package model.user;

import model.enums.UserRole;

public class Admin {
    Credential credential;


    public Admin(Credential credential) {
        this.credential = credential;
        this.credential.setRole(UserRole.ADMIN);
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
}
