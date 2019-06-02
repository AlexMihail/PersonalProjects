package Service;


import Model.Angajat;
import Repository.IAngajatRepository;


public class LoginServices {
    private IAngajatRepository repo;


    public LoginServices(IAngajatRepository repo) {
        this.repo = repo;
    }

    public Angajat findByUsername(String name)
    {
        return repo.findByUsername(name);
    }

    public boolean e_valid(String username,String password)
    {
        Angajat a = repo.findByUsername(username);
        if(a == null)
            return false;

        if(a.getPassword().equals(password))
             return true;
        return false;
    }
}
