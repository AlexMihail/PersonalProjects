package objectprotocol;

import dto.AngajatDTO;

public class LogoutRequest implements Request {
    private AngajatDTO user;

    public LogoutRequest(AngajatDTO user) {
        this.user = user;
    }

    public AngajatDTO getUser() {
        return user;
    }
}
