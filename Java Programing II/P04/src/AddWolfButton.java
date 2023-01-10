public class AddWolfButton extends Button{
    public AddWolfButton(float x, float y) {
        super("Add Wolf", x, y);
    }

    @Override
    public void mousePressed() {
        if (isMouseOver()) {
            System.out.println("Add Wolf Button Pressed");
        }
    }
}
