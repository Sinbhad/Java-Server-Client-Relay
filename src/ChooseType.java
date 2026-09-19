import java.util.Scanner;
public class ChooseType {
    void chooseType(){
        int typeSelection = 0;

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Would you like to join as the server or a client?");
        System.out.print("""
                1) Server
                2) Client
                0) Exit
               \s
               \s
                Option :\s""");

        typeSelection = keyboard.nextInt();
        if(typeSelection == 1){
            TCPServer server = new TCPServer();
            server.tcpServer();
        }else if(typeSelection == 2){
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
