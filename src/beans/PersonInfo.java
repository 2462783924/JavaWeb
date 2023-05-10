package beans;

import java.io.Serializable;

public class PersonInfo implements Serializable {

    private String account;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PersonInfo)) {
            return false;
        }
        return account.equals(((PersonInfo) obj).getAccount());
    }
}
