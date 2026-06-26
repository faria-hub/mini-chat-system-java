import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 import java.time.LocalTime;
  import java.time.format.DateTimeFormatter;
public class minichatsystemfinal11 {
    static Scanner sc = new Scanner(System.in);
    static String[] messages = new String[100];
    static int count = 0;

    public static void main(String[] args) {

        runMenu(sc);
        
    }
    
    //=========================
    //Run menu
    //=========================
    public static void runMenu(Scanner sc){
        
            System.out.println("\n===== MINI CHAT SYSTEM =====");
            System.out.println("1. Send Message");
            System.out.println("2. View Chat History");
            System.out.println("3. Search Message");
            System.out.println("4. Delete Message");
            System.out.println("5. Total Messages");
            System.out.println("6. Clear Chat");
            System.out.println("7.Save data in file");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

          int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    sendMessage(sc);
                    break;

                case 2:
                    viewChat();
                    break;

                case 3:
                    searchMessage(sc);
                    break;

                case 4:
                    deleteMessage(sc);
                    break;

                case 5:
                    totalMessages();
                    break;

                case 6:
                    clearChat();
                    break;
                    
                case 7:
                    saveData();
                    break;

                case 8:
                    System.out.println("Exiting Chat...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        if (choice!=8){
            runMenu(sc);
        }
        } 

        
    

    // =========================
    // METHOD 1: SEND MESSAGE
    // =========================
    public static void sendMessage(Scanner sc) {

        if (count < messages.length) {

            System.out.print("Enter your message: ");
            String msg = sc.nextLine();

            LocalTime time = LocalTime.now();
              DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");
              String currentTime = time.format(format);

            messages[count] = "["+ currentTime +"] You: " + msg;
            count++;

            // Auto Reply Feature
            autoReply(msg);

            System.out.println("Message sent!");

        } else {
            System.out.println("Chat is full!");
        }
    }

    // =========================
    // METHOD 2: VIEW CHAT
    // =========================
    public static void viewChat() {

        if (count == 0) {
            System.out.println("No messages yet.");
            return;
        }

        System.out.println("\n--- Chat History ---");

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + messages[i]);
        }
    }

    // =========================
    // METHOD 3: SEARCH MESSAGE
    // =========================
    public static void searchMessage(Scanner sc) {

        if (count == 0) {
            System.out.println("No messages available.");
            return;
        }

        System.out.print("Enter keyword to search: ");
        String keyword = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (messages[i].toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("Found: " + messages[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching messages found.");
        }
    }

    // =========================
    // METHOD 4: DELETE MESSAGE
    // =========================
    public static void deleteMessage(Scanner sc) {

    if (count == 0) {
        System.out.println("No messages to delete.");
        return;
    }

    viewChat();

    System.out.print("Enter message number to delete: ");

    try {
        int index = sc.nextInt();
        sc.nextLine(); // clear buffer

        if (index > 0 && index <= count) {

            for (int i = index - 1; i < count - 1; i++) {
                messages[i] = messages[i + 1];
            }
            count--;

            System.out.println("Message deleted!");

        } else {
            System.out.println("Invalid number!");
        }

    } catch (InputMismatchException e) {
        System.out.println("Please enter a valid number.");
        sc.nextLine(); // remove invalid input
    }
}

    // =========================
    // METHOD 5: TOTAL MESSAGES
    // =========================
    public static void totalMessages() {
        System.out.println("Total messages: " + count);
    }

    // =========================
    // METHOD 6: CLEAR CHAT
    // =========================
    public static void clearChat() {

        count = 0;
        System.out.println("Chat cleared!");
    }

    // =========================
    // METHOD 7: AUTO REPLY
    // =========================
    public static void autoReply(String msg) {

        String reply;
        String msgLower = msg.toLowerCase().trim();
        
        if (msg.equalsIgnoreCase("hi") || msg.equalsIgnoreCase("hello") 
        || msg.equalsIgnoreCase("hey")) {
        reply = "Bot: Hello! How are you?";

    } else if (msg.equalsIgnoreCase("how are you") 
               || msg.equalsIgnoreCase("how r u")) {
        reply = "Bot: I'm fine, thank you for asking! How about you?";

    } else if (msg.equalsIgnoreCase("bye") || msg.equalsIgnoreCase("goodbye") 
               || msg.equalsIgnoreCase("see you")) {
        reply = "Bot: Goodbye! Have a wonderful day!";

    } else if (msg.equalsIgnoreCase("what am i supposed to do with my life")) {
        reply = "Bot: You will have to figure it out yourself!";

    // Greetings & Feelings 
    } else if (msgLower.contains("good morning")) {
        reply = "Bot: Good morning! Hope you have a great day!";

    } else if (msgLower.contains("good night")) {
        reply = "Bot: Good night! Sweet dreams!";

    } else if (msgLower.contains("good evening")) {
        reply = "Bot: Good evening! How was your day?";

    } else if (msgLower.contains("i'm good") || msgLower.contains("i am good")
               || msgLower.contains("i'm fine") || msgLower.contains("im fine")) {
        reply = "Bot: That's great to hear!";

    } else if (msgLower.contains("i'm sad") || msgLower.contains("i am sad")
               || msgLower.contains("i feel sad")) {
        reply = "Bot: I'm sorry to hear that. I hope things get better soon!";

    } else if (msgLower.contains("i'm happy") || msgLower.contains("i am happy")) {
        reply = "Bot: That's awesome! Keep smiling!";

    } else if (msgLower.contains("i'm tired") || msgLower.contains("i am tired")) {
        reply = "Bot: You should get some rest. Take care of yourself!";

    } else if (msgLower.contains("i'm bored") || msgLower.contains("i am bored")) {
        reply = "Bot: Why not try learning something new today?";

    } else if (msgLower.contains("i'm angry") || msgLower.contains("i am angry")) {
        reply = "Bot: Take a deep breath. Things will get better!";

    //  About the Bot 
    } else if (msgLower.contains("what is your name") || msgLower.contains("who are you")
               || msgLower.contains("what's your name")) {
        reply = "Bot: I'm ChatBot, your virtual assistant!";

    } else if (msgLower.contains("how old are you") || msgLower.contains("what is your age")) {
        reply = "Bot: I'm ageless — I'm a bot!";

    } else if (msgLower.contains("are you a bot") || msgLower.contains("are you human")
               || msgLower.contains("are you real")) {
        reply = "Bot: I'm a bot, but I'm here to help!";

    } else if (msgLower.contains("what can you do") || msgLower.contains("help")) {
        reply = "Bot: I can chat with you, answer simple questions, and keep you company!";

    // Small Talk 
    } else if (msgLower.contains("thank you") || msgLower.contains("thanks")
               || msgLower.contains("thx")) {
        reply = "Bot: You're welcome! Happy to help!";

    } else if (msgLower.contains("sorry") || msgLower.contains("my bad")) {
        reply = "Bot: No worries at all!";

    } else if (msgLower.contains("okay") || msgLower.contains("ok")
               || msgLower.contains("alright")) {
        reply = "Bot: Great! Let me know if you need anything.";

    } else if (msgLower.contains("yes") || msgLower.equals("yep")
               || msgLower.equals("yup")) {
        reply = "Bot: Awesome!";

    } else if (msgLower.equals("no") || msgLower.contains("nope")) {
        reply = "Bot: Alright, no problem!";

    // Fun & Misc 
    } else if (msgLower.contains("tell me a joke") || msgLower.contains("joke")) {
        reply = "Bot: Why don't scientists trust atoms? Because they make up everything!";

    } else if (msgLower.contains("favorite color")) {
        reply = "Bot: I love the color blue — like the sky!";

    } else if (msgLower.contains("do you like music")) {
        reply = "Bot: I love all kinds of music! What's your favorite genre?";

    } else if (msgLower.contains("what time is it") || msgLower.contains("current time")) {
        reply = "Bot: Check the timestamp on my messages for the current time!";

    } else if (msgLower.contains("what is today") || msgLower.contains("what day is it")) {
        reply = "Bot: Today is " + java.time.LocalDate.now().getDayOfWeek() + "!";

    } else if (msgLower.contains("flip a coin")) {
        reply = "Bot: " + (Math.random() < 0.5 ? "Heads!" : "Tails!");

    } else if (msgLower.contains("random number")) {
        reply = "Bot: Your random number is " + (int)(Math.random() * 100 + 1) + "!";

    } else {
        reply = "Bot: I didn't understand that. Try asking something else!";
    }

              LocalTime time = LocalTime.now();
              DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");
              String currentTime = time.format(format);

        if (count < messages.length) {
            messages[count] ="["+ currentTime +"] "+ reply;
            count++;
        }
    }
    
    // =========================
    // METHOD 8: File Handling
    // =========================
    
    public static void saveData(){
        try{
            File f = new File ("MyFILE.txt");
            FileWriter writer = new FileWriter(f,true);
            for (int i = 0;i<count;i++){
                writer.write(messages[i]+"\n");
            }
            writer.close();
            System.out.println("Data saved");
        }
        catch(IOException e){
            System.out.println("Error saving file");
        }
    
    }

}