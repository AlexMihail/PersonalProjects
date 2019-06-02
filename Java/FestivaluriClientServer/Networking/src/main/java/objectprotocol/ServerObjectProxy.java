package objectprotocol;

import Model.*;
import Utils.IServer;
import Utils.MyException;
import Utils.Observer;
import dto.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerObjectProxy implements IServer {
    private String host;
    private int port;

    private Observer client;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;

    public ServerObjectProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses = new LinkedBlockingQueue<Response>();
    }
    private void sendRequest(Request request) {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            throw new MyException("Error sending object " + e);
        }

    }
    public Spectacol[] getArtisti() {
        sendRequest(new GetArtistiRequest());
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new MyException(err.getMessage());
        }
        GetArtistiResponse resp = (GetArtistiResponse) response;
        SpectacolDTO[] tryoutsDTO = resp.getArtisti();
        return DTOUtils.getFromDTO(tryoutsDTO);

    }


    public Spectacol findByDataArtistLocatie(String data, String Artist, String locatie) {
        DataArtistLocatieDTO tdto = DTOUtils.getDTO(new DataArtistLocatie(data, locatie,Artist));
        sendRequest(new GetByDataRequest(tdto));
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new MyException(err.getMessage());
        }
        GetByDataResponse resp = (GetByDataResponse) response;
        SpectacolDTO pDTO = resp.getTryout();
        return DTOUtils.getFromDTO(pDTO);
    }

    public Bilet rezervaLoc(Bilet b) {
        BiletDTO tdto = DTOUtils.getDTO(b);

        sendRequest(new RezervareRequest(tdto));
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new MyException(err.getMessage());
        }
        RezervareResponse resp = (RezervareResponse) response;
        BiletDTO pDTO = resp.getBilet();
        return DTOUtils.getFromDTO(pDTO);
    }

    public Spectacol[] findByDate(String data) {
        sendRequest(new GetDateRequest(data));
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new MyException(err.getMessage());
        }
        GetDateResponse resp = (GetDateResponse) response;
        SpectacolDTO[] tryoutsDTO = resp.getArtisti();
        return DTOUtils.getFromDTO(tryoutsDTO);
    }

    private Response readResponse() {
        Response response = null;
        try {
            response = qresponses.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
    private void closeConnection() {
        finished = true;
        try {
            input.close();
            output.close();
            connection.close();
            client = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void login(Angajat user, Observer client) {
        initializeConnection();
        AngajatDTO udto = DTOUtils.getDTO(user);
        sendRequest(new LoginRequest(udto));
        Response response = readResponse();
        if (response instanceof OkResponse) {
            this.client = client;
            return;
        }
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            closeConnection();
            throw new MyException(err.getMessage());
        }
    }

    private void initializeConnection() {
        try {
            connection = new Socket(host, port);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReader() {
        Thread tw = new Thread(new ReaderThread());
        tw.start();
    }
    private void handleUpdate(UpdateResponse update) {
        SaveResponse resp=(SaveResponse)update;
        Integer[] tryoutsdto = resp.getTryouts();
        try {
            client.update(tryoutsdto);
        }
        catch (MyException e) {
            e.printStackTrace();
        }
    }
    private class ReaderThread implements Runnable {
        public void run() {
            while (!finished) {
                try {
                    Object response = input.readObject();
                    System.out.println("response received " + response);
                    if (response instanceof SaveResponse) {
                        handleUpdate((UpdateResponse) response);
                    } else {
                        try {
                            qresponses.put((Response) response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error " + e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error " + e);
                }
            }
        }
    }

    public void logout(Angajat user, Observer client) {
        AngajatDTO udto = DTOUtils.getDTO(user);
        sendRequest(new LogoutRequest(udto));
        Response response = readResponse();
        closeConnection();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new MyException(err.getMessage());
        }
    }

}