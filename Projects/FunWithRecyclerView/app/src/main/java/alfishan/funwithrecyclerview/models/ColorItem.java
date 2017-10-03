package alfishan.funwithrecyclerview.models;

/**
 * Created by root on 2/10/17.
 */

public class ColorItem implements SingleChoiceItem {
    private int id;
    private int color;

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected = false;

    public ColorItem(int id, int color) {
        this.id = id;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }
}
