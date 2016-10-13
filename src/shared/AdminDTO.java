package shared;

public class AdminDTO extends UserDTO {

	private static final long serialVersionUID = 1L;

	public AdminDTO() {
		super();
	}

	public AdminDTO(int id, String mail, String password, String type) {
		super(id, mail, password, type);
	}

	@Override
	public String toString() {
		return "AdminDTO [getId()=" + getId() + ", getMail()=" + getMail() + ", getPassword()=" + getPassword()
				+ ", getType()=" + getType() + "]";
	}
}
