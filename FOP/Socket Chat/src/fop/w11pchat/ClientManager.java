package fop.w11pchat;


import javax.print.attribute.standard.PrinterMessageFromOperator;
import java.io.*;
import java.net.Socket;
import java.nio.BufferOverflowException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class ClientManager implements Runnable{

    public static ArrayList<ClientManager> ClientManagers=new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader; //receive data
    private BufferedWriter bufferedWriter; //send date
    private String clientUsername;
    private LocalDateTime localDate;

    public ClientManager(Socket socket){
        if (ClientManagers.size()<=50) {
            try {
                this.socket = socket;
                this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.clientUsername = bufferedReader.readLine();
                ClientManagers.add(this);
                broadCastMessage("Server: " + clientUsername + " has entered the chat!");
                this.localDate = LocalDateTime.now();

            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void DM(String username, String messageToDm) throws IOException {
        boolean wasDMed=false;
        try {

            for (ClientManager ClientManager : ClientManagers) {

                if (Objects.equals(ClientManager.clientUsername, username)) {
                    ClientManager.bufferedWriter.write(this.clientUsername+": "+messageToDm);
                    ClientManager.bufferedWriter.newLine();
                    ClientManager.bufferedWriter.flush();
                    wasDMed=true;
                    break;
                }
            }

            if (!wasDMed){
                    this.bufferedWriter.write(("Could not find username"));
                    this.bufferedWriter.newLine();
                    this.bufferedWriter.flush();

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void connectedUser() throws IOException {

        StringBuilder list = new StringBuilder("List of Users:\n");
        for (ClientManager ClientManager: ClientManagers){
            if (!Objects.equals(ClientManager.clientUsername, this.clientUsername))
                list.append(ClientManager.clientUsername).append(":joined at ")
                        .append(ClientManager.localDate).append("\n");
        }
        this.bufferedWriter.write(String.valueOf(list));
        this.bufferedWriter.newLine();
        this.bufferedWriter.flush();

    }
    public boolean isFunctionCalled(String instruction) throws IOException, InterruptedException {
        if (instruction.contains("@")){
            String username=instruction.substring(instruction.indexOf("@")+1, instruction.indexOf("<"));
            String messageToDm=instruction.substring(instruction.indexOf(">")+1);
            DM(username, messageToDm);
            return true;
        }
        else if (instruction.contains("WHOIS")){
            connectedUser();
            return true;

        }
        else if (instruction.contains("LOGOUT")){
            this.bufferedWriter.write("You left");
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
            this.bufferedReader=null;
            this.bufferedWriter=null;
            ClientManagers.remove(this);
            broadCastMessage("Server: "+ clientUsername+" has left the chat!");
            return true;

        }
        else if (instruction.contains("PINGU")){
            broadCastMessageToAll("Penguins should be extinct :)");
            return true;

        }

        //@username<blank>message
        //If the client sends WHOIS, (s)he and only (s)he, receives a list of all currently connected clients and since when they are connected.
        //If a client sends LOGOUT, the connection of this client is closed and all streams and of both sides are also closed.
        //If a client sends PINGU, all currently connected clients receive an important fact about penguins (what ever that might be :)).
        return false;
    }


    @Override
    public void run() {
        String messageFromClient;

        while(socket.isConnected()){
            try{

                messageFromClient=bufferedReader.readLine(); //reading from others
                if (!isFunctionCalled(messageFromClient)) {
                    broadCastMessage(messageFromClient);
                }
            }catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void broadCastMessage(String messageToSend){
        for(ClientManager ClientManager: ClientManagers){
            try{
                if (!ClientManager.clientUsername.equals(clientUsername)){
                    ClientManager.bufferedWriter.write(messageToSend);
                    ClientManager.bufferedWriter.newLine(); //send data
                    ClientManager.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }
    public void broadCastMessageToAll(String messageToSend) {
        for(ClientManager ClientManager: ClientManagers){
            try{
                    ClientManager.bufferedWriter.write(messageToSend);
                    ClientManager.bufferedWriter.newLine(); //send data
                    ClientManager.bufferedWriter.flush();
            }catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }



    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        ClientManagers.remove(this);
        broadCastMessage("Server: "+ clientUsername+" has left the chat!");
        try{
            if (bufferedWriter!=null){
                bufferedWriter.close();
            }
            if (bufferedReader!=null){
                bufferedReader.close();
            }
            if (socket!=null){
                socket.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}

