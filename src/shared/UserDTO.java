package shared;

import java.io.Serializable;
 
public abstract class UserDTO implements Serializable {

	private int id;
    private String mail, password, type;

	private static final long serialVersionUID = 1L;
    
    public UserDTO(int id, String mail, String password, String type) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.type = type;
    }
    
    //Must be defined
    public UserDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
