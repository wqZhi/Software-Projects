public class RestartButton extends Button{
    public RestartButton(float x, float y) {
        super("Restart", x, y);
    }

    @Override
    public void mousePressed() {
        if (isMouseOver()) {
            System.out.println("Restart Button Pressed");
        }
    }

}
