import javax.swing.*;
import java.awt.Graphics;
import java.awt.AWTEvent;
import java.awt.event.*;
import java.awt.Color;

public class JFrameTask {
    public static void main(String[] args) {
        JFrame frame = new JFrame("lab 4");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.add(new Game());
    }
}


class Game extends JPanel {
    int lastX = -5;
    int lastY = -5;
    int newX = -5;
    int newY = -5;
    int stock = 5;
    Color color = Color.BLACK;
    Game(){
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
        enableEvents(AWTEvent.MOUSE_WHEEL_EVENT_MASK);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(color);
        // draw the point and the line between two point to make it like one point
        g.fillRect(newX, newY, stock, stock);
        g.drawLine(lastX, lastY, newX, newY);
        for (int i = 0; i < stock+1; i++) {
            g.drawLine(lastX, lastY + i, newX, newY + i);
            g.drawLine(lastX + i, lastY, newX + i, newY);
        }
        
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED){
            // change the start of the new draw when the user click on any button 
            this.newX = e.getX();
            this.newY = e.getY();
            this.lastX = e.getX();
            this.lastY = e.getY();
            repaint();
        } else if(e.getButton() == MouseEvent.BUTTON3 || e.getButton() == MouseEvent.BUTTON2) {
            // change the color when click on left button and the center button
            this.color = new Color(
                (int)(Math.random() * 255),
                (int)(Math.random() * 255),
                (int)(Math.random() * 255)
            );
        }
    }

    // this function is repaint when the mouse is clicked and moved
    @Override
    protected void processMouseMotionEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
            lastX = newX;
            lastY = newY;
            newX = e.getX();
            newY = e.getY();
            repaint();
        }
    }

    @Override
    protected void processMouseWheelEvent(MouseWheelEvent e)
    {
        if (e.getWheelRotation() < 0) {
            stock++;
        } else {
            if (stock > 2)
                stock--;
        }
    }

}
