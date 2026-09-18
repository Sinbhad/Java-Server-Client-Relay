import java.util.Scanner;
public class ChooseType {
    void chooseType(){
        int westleyKirk = 0;

        Scanner westleyNey = new Scanner(System.in);
        System.out.println("Would you like to join as the server or a client?");
        System.out.print("""
                1) Server
                2) Client
                0) Exit
                
                
                Option : """);

        westleyKirk = westleyNey.nextInt();
        if(westleyKirk == 1){
            TCPServer server = new TCPServer();
            server.tcpServer();
        }else if(westleyKirk == 2){
            TCPClient client = new TCPClient();
            client.tcpClient();
        }else{
            System.out.println("Bruh");
        }
    }

    boolean correctOption(int choice){
        boolean correct = false;
        int[] choices = {0,1,2};

        for (int j : choices)
            correct = choice == j;
        return correct;
    }
}
