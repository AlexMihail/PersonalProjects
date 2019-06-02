import Repository.AngajatDBRepository;
import Repository.ArtistDBRepository;
import Repository.BiletDBRepository;
import Repository.SpectacolDBRepository;
import Service.ServerImpl;
import Utils.IServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.io.IOException;

import java.util.Properties;

public class StartObjectServer {
    private static int defaultPort=55557;
    public static void main(String[] args) {

        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");
    }
}
