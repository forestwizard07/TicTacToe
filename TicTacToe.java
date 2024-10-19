import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JFrame{
    static JButton[][] buttons = new JButton[3][3];
    static JFrame frame = new JFrame();
    static int move;
    static int[] occupied = new int[9];
    static int[] taken = new int[9];
    static int[] master = new int[9];
    static int count=0,playercount=0,compcount=0;
    static boolean center;
    static boolean played;
    static boolean result;
    static boolean difficulty;
    public TicTacToe(){
        Object[] options = {"Easy mode", "Hard Mode"};

        
        int choice = JOptionPane.showOptionDialog(
            null,
            "Choose a difficulty",
            "Welcome to TicTacToe",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        if(choice==JOptionPane.NO_OPTION)
            difficulty=true;
        frame.setSize(400,400);
        frame.setTitle("Tic Tac Toe");
        frame.setLayout(new GridLayout(3,3));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initialise();
        frame.setVisible(true);
    }
    public static void initialise(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        JButton clickedButton = (JButton) e.getSource();
                        if (!clickedButton.getText().equals("")) {
                            return;
                        }
                        clickedButton.setText("X");
                        for (int i=0;i<3;i++) {
                            for(int j=0;j<3;j++){
                                if(clickedButton==buttons[i][j])
                                    performMove(i, j);
                            }
                            
                        }
                    }
                });
                frame.add(buttons[i][j]);

            }
        }
    }
    public static void performMove(int r, int c){
       result = false;

       played=false;
       
       move = 3*r+c;
       master[count] = move+1;
       playercount++;
       count++;
       getResult();
       if(result==false){ 
        gamelogic();
       }
    }
    public static void gamelogic(){
        occupied[playercount-1] = move;
        played = false;
        if(move==4){
            center = true;
            if(difficulty==true)
                place(2,2);
        }
            

        if(center==false){
           place(1, 1);
            center = true;
            played=true;
        }

        else if(played==false){
            for(int i=0;i<compcount;i++){
                for(int j=0;j<compcount;j++){
                    int a1 = taken[i]/3;
                    int a2= taken[i]%3;
                    int b1 = taken[j]/3;
                    int b2= taken[j]%3;
                    if(taken[i]!=taken[j]){ 
                        if(a1==b1){
                            place(a1,3-a2-b2);
                            if(played==false){
                                continue;
                            }
                            else
                                break;
                        }
                        else if(a2==b2){
                            place(3-a1-b1,a2); 
                            if(played==false){
                                continue;
                            }
                            else
                                break;
                        }
                        else if(a1+a2==2&&b1+b2==2){
                            place(3-a1-b1,3-a2-b2);
                            if(played==false){
                                continue;
                            }
                            else
                                break;
                        }
                        else if(a1==a2&&b1==b2){
                            place(3-a1-b1,3-a2-b2);
                            if(played==false){
                                continue;
                            }
                            else
                                break;
                        }
                    }   

                }
                if(played==true)
                break;
            }           
            
            if(played==false){
                for(int i=0;i<playercount;i++){
                    for(int j=0;j<playercount;j++){
                        int a1 = occupied[i]/3;
                        int a2= occupied[i]%3;
                        int b1 = occupied[j]/3;
                        int b2= occupied[j]%3;
                        if(occupied[i]!=occupied[j]){ 
                            if(a1==b1){
                                place(a1,3-a2-b2);
                                if(played==false){
                                    continue;
                                }
                                else
                                    break;
                            }
                            else if(a2==b2){
                                place(3-a1-b1,a2); 
                                if(played==false){
                                    continue;
                                }
                                else
                                    break;
                            }
                            else if(a1+a2==2&&b1+b2==2){
                                place(3-a1-b1,3-a2-b2);
                                if(played==false){
                                    continue;
                                }
                                else
                                    break;
                            }
                            else if(a1==a2&&b1==b2){
                                place(3-a1-b1,3-a2-b2);
                                if(played==false){
                                    continue;
                                }
                                else
                                    break;
                            }

                        }     
                    }
                    if(played==true)
                    break;
                }   
            }
            int i=0;
            while(played==false){      
                    if(occupied[i]/3-1<0){
                        place(occupied[i]/3+1,occupied[i]%3);
                        if(played==true)
                            break;
                    }
                    else if(occupied[i]/3+1>2){
                        place(occupied[i]/3-1,occupied[i]%3);
                        if(played==true)
                            break;
                    }
                    else{
                        place(occupied[i]/3+1,occupied[i]%3);
                        if(played==true)
                            break;
                        place(occupied[i]/3-1,occupied[i]%3);
                        if(played==true)
                            break;
                    }
                i++;
            }
        }      
    }
    public static void place(int r, int c){
        if(!buttons[r][c].getText().equals("X")&&!buttons[r][c].getText().equals("O")){
            buttons[r][c].setText("O");
            taken[compcount] = (r*3)+c;
            master[count] = (r*3)+c+1;
            count++;
            compcount++;
            played=true;
            getResult();
        }
    }
    public static void getResult(){
        for(int i=0;i<3;i++){
            if((buttons[i][0].getText().equals(buttons[i][1].getText())&&buttons[i][1].getText().equals(buttons[i][2].getText()))&&((buttons[i][1].getText().equals("X"))||buttons[i][1].getText().equals("O"))){
                String winner  = buttons[i][1].getText()+" wins!";
                JOptionPane.showMessageDialog(frame, winner);
                result = true;
                resetBoard();
            }
        }
        for(int i=0;i<3;i++){
            if((buttons[0][i].getText().equals(buttons[1][i].getText())&&buttons[1][i].getText().equals(buttons[2][i].getText()))&&((buttons[0][i].getText().equals("X"))||buttons[0][i].getText().equals("O"))){
                String winner  = buttons[0][i].getText()+" wins!";
                JOptionPane.showMessageDialog(frame, winner);
                result = true;
                resetBoard();
            }
        }
        if(result==false){
            if((buttons[0][0].getText().equals(buttons[1][1].getText())&&buttons[1][1].getText().equals(buttons[2][2].getText()))&&((buttons[1][1].getText().equals("X"))||buttons[1][1].getText().equals("O"))){
                String winner  = buttons[0][0].getText()+" wins!";
                JOptionPane.showMessageDialog(frame, winner);
                result = true;
                resetBoard();
            }
            else if((buttons[0][2].getText().equals(buttons[1][1].getText())&&buttons[1][1].getText().equals(buttons[2][0].getText()))&&((buttons[1][1].getText().equals("X"))||buttons[1][1].getText().equals("O"))){
                String winner  = buttons[1][1].getText()+" wins!";
                JOptionPane.showMessageDialog(frame, winner);
                result = true;
                resetBoard();
            }
        }
        if(result==false&&count==9){
           JOptionPane.showMessageDialog(frame, "Draw!");
            result = true;
            resetBoard();
        }
    }
    public static void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                occupied[3*i+j] = 0;
                master[3*i+j] = 0;
                taken[3*i+j] = 0;
                
                
            }
        }
        count=0;
        playercount=0;
        compcount=0;
       
        center=false;
        move=0;
       
    }
   
    public static void main(String[] args) {
        new TicTacToe();
    }
}