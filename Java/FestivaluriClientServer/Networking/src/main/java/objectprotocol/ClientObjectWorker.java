package objectprotocol;

import Model.Angajat;
import Model.Bilet;
import Model.DataArtistLocatie;
import Model.Spectacol;
import Utils.IServer;
import Utils.MyException;
import Utils.Observer;
import dto.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientObjectWorker implements Runnable, Observer {

    private IServer server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public ClientObjectWorker(IServer server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (connected) {
            try {
                Object request = input.readObject();
                Object response = handleRequest((Request) request);
                if (response != null) {
                    sendResponse((Response) response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    private void sendResponse(Response response) throws IOException {
        System.out.println("sending response " + response);
        output.writeObject(response);
        output.flush();
    }

    private Response handleRequest(Request request) {
        Response response = null;
        if (request instanceof LoginRequest) {
            System.out.println("Login request ...");
            LoginRequest logReq = (LoginRequest) request;
            AngajatDTO udto = logReq.getUser();
            Angajat user = DTOUtils.getFromDTO(udto);

            try {
                server.login(user, this);
                return new OkResponse();
            } catch (Exception e) {
                connected = false;
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof LogoutRequest) {
            System.out.println("Logout request");
            LogoutRequest logReq = (LogoutRequest) request;
            AngajatDTO udto = logReq.getUser();
            Angajat user = DTOUtils.getFromDTO(udto);
            try {
                server.logout(user, this);
                connected = false;
                return new OkResponse();

            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof GetArtistiRequest) {
            System.out.println("GetArtistiRequest ...");
            GetArtistiRequest getReq = (GetArtistiRequest) request;
            try {
                Spectacol[] friends = server.getArtisti();

                SpectacolDTO[] frDTO = DTOUtils.getDTO(friends);
                return new GetArtistiResponse(frDTO);
            } catch (MyException e) {
                return new ErrorResponse(e.getMessage());
            }
        }

        if (request instanceof GetByDataRequest) {
            System.out.println("Get By data request");
            GetByDataRequest getReq = (GetByDataRequest) request;
            DataArtistLocatieDTO pdto = getReq.getTryout();
            DataArtistLocatie p = DTOUtils.getFromDTO(pdto);
            try {
                Spectacol rez = server.findByDataArtistLocatie(p.getData(),p.getArtist(),p.getLocatie());
                SpectacolDTO rezdto = DTOUtils.getDTO(rez);
                return new GetByDataResponse(rezdto);
            } catch (MyException e) {
                return new ErrorResponse(e.getMessage());
            }
        }

        if (request instanceof RezervareRequest) {
            System.out.println("Rezervare request");
            RezervareRequest getReq = (RezervareRequest) request;
            BiletDTO pdto = getReq.getBilet();
            Bilet b = DTOUtils.getFromDTO(pdto);
            try {
                Bilet rez = server.rezervaLoc(b);
                BiletDTO rezdto = DTOUtils.getDTO(rez);
                return new RezervareResponse(rezdto);
            } catch (MyException e) {
                return new ErrorResponse(e.getMessage());
            }
        }

        if (request instanceof GetDateRequest) {
            System.out.println("GetDateRequest ...");
            GetDateRequest getReq = (GetDateRequest) request;
            String data = getReq.getTryout();
            try {
                Spectacol[] friends = server.findByDate(data);
                SpectacolDTO[] frDTO = DTOUtils.getDTO(friends);
                return new GetDateResponse(frDTO);
            } catch (MyException e) {
                return new ErrorResponse(e.getMessage());
            }
        }

        return response;
    }


    public void update(Integer[] tryouts) {
        try {
            sendResponse(new SaveResponse(tryouts));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}