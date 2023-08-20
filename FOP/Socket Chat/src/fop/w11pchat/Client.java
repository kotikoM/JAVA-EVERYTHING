package fop.w11pchat;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;


    public class Client {

        private Socket socket;
        private BufferedReader bufferedReader;
        private BufferedWriter bufferedWriter;
        private String userName;

        public Client(Socket socket, String userName){
            try{
                this.socket=socket;
                this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.userName=userName;
            }catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }


        public void sendMessage(){
            try{
                bufferedWriter.write(userName);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                Scanner scanner=new Scanner(System.in);

                while (socket.isConnected()){
                    String messageTosend=scanner.nextLine();
                        bufferedWriter.write(userName + ": " + messageTosend);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }

        public void listen(){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String msgFromGroup;

                    while(socket.isConnected()){
                        try{
                                msgFromGroup = bufferedReader.readLine();
                                System.out.println(msgFromGroup);
                        } catch (IOException e){
                            closeEverything(socket, bufferedReader, bufferedWriter);
                        }
                    }
                }
            }).start();
        }

        public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws IOException {

                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter your username for the group chat: ");
                String username = scanner.nextLine();
                Socket socket1 = new Socket("localhost", 3000);
                Client client = new Client(socket1, username);
                client.listen();
                client.sendMessage();

        }



    }

