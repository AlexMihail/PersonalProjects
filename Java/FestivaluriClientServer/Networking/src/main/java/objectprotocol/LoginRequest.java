package objectprotocol;

import dto.AngajatDTO;

public class LoginRequest implements Request {
    private AngajatDTO user;

    public LoginRequest(AngajatDTO user) {
        this.user = user;
    }

    public AngajatDTO getUser() {
        return user;
    }
}