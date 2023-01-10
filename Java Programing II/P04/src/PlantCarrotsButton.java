public class PlantCarrotsButton extends Button{
    public PlantCarrotsButton(float x, float y) {
        super("Plant Carrots", x, y);
    }

    @Override
    public void mousePressed() {
        if (isMouseOver()) {
            System.out.println("Plant Carrots Button Pressed");
        }
    }


}
