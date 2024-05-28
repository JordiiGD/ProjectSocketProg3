package co.edu.uptc.model;

import co.edu.uptc.pojos.Ball;
import co.edu.uptc.utils.MathUtils;

import java.awt.*;

public class ManagerGame {

    private Ball ball;

    public ManagerGame() {
        this.ball = new Ball();
        ball.setHeight(20);
        ball.setWidth(20);
        ball.setX(0);
        ball.setY(0);
        ball.setAngle(0);
    }

    public void start(){
        Thread thread = new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(1);
                    moveBall();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    public void moveBall(){
        Point point = MathUtils.calculatePoint(ball.getX(), ball.getY(), ball.getAngle(), 10);
        ball.setX(point.x);
        ball.setY(point.y);
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
