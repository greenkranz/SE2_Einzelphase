package com.example.se2_einzelphase;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class ServerConnection extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... strings) {
        Socket socket = null;
        String answer="";
        try {
            String address = "se2-isys.aau.at";
            int port = 53212;
            socket = new Socket(address, port);
            System.out.println("Socket:" + socket);
            if (socket.isConnected()) {
                System.out.println("Connected");
            }
            String sendMessage = strings[0];
            writeMessage(socket, sendMessage);
            return readMessage(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        System.out.println(s);
        MainActivity.setAnswer(s);
        MainActivity.setBtnSendState(true);
    }

    public void writeMessage(Socket socket, String nachricht) throws IOException {
        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println(Integer.parseInt(nachricht));
        ps.flush();
    }

    public String readMessage(Socket socket) throws IOException {
        String answer ="";
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (!br.ready()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        while(br.ready()){
            answer+=br.readLine();
        }
        return answer;
    }
}