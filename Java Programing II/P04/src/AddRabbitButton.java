public class AddRabbitButton extends Button{
    public AddRabbitButton(float x, float y) {
        super("Add Rabbit", x, y);
    }

    @Override
    public void mousePressed() {
        if (isMouseOver()) {
            System.out.println("Add Rabbit Button Pressed");
        }
    }
}
