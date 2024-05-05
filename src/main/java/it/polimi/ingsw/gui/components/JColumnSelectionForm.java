package it.polimi.ingsw.gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * A graphical component that allows the user to select a column.
 *
 * @author Marcelo S. Hernandez
 * @author Francesco Guarnello
 */
public class JColumnSelectionForm extends JPanel {
    JColumnSelectionButton[] buttons;
    private int selectedColumn = -1;

    /**
     * Constructs a JColumnSelectionForm component.
     */
    public JColumnSelectionForm() {
        setLayout(null);
        setOpaque(false);
        setPreferredSize(new Dimension(20 * 5 + 21 * 4, 20));

        buttons = new JColumnSelectionButton[5];

        for (int i = 0; i < 5; i++) {
            buttons[i] = new JColumnSelectionButton();
            int finalI = i;

            buttons[i].addActionListener(e -> {
                if (selectedColumn != -1) {
                    buttons[selectedColumn].setSelected(false);
                }

                buttons[finalI].setSelected(true);
                selectedColumn = finalI;
            });

            buttons[i].setBounds(41 * i, 0, buttons[i].getPreferredSize().width, buttons[i].getPreferredSize().height);
            add(buttons[i]);
        }
    }

    /**
     * Returns the index of the selected column.
     *
     * @return the index of the selected column, or -1 if no column is selected
     */
    public int getSelectedColumn() {
        return selectedColumn;
    }

    /**
     * Clears the current selection of the column.
     */
    public void clearSelection() {
        for (JColumnSelectionButton button : buttons) {
            button.setSelected(false);
        }

        selectedColumn = -1;
    }

    /**
     * A custom button component used by JColumnSelectionForm to represent a selectable column.
     */
    private static class JColumnSelectionButton extends JButton {
        private static final int SIZE = 20;
        private boolean isSelected;

        /**
         * Constructs a JColumnSelectionButton component.
         */
        public JColumnSelectionButton() {
            setPreferredSize(new Dimension(SIZE, SIZE));
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setBackground(new Color(0, 0, 0, 0));

            isSelected = false;
        }

        /**
         * Checks if the button is currently selected.
         *
         * @return true if the button is selected, false otherwise
         */
        public boolean isSelected() {
            return isSelected;
        }

        /**
         * Sets the selected state of the button.
         *
         * @param selected true to select the button, false to deselect it
         */
        public void setSelected(boolean selected) {
            isSelected = selected;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int[] xPoints = {0, getWidth() / 2, getWidth()};
            int[] yPoints = {0, getHeight(), 0};
            Polygon triangle = new Polygon(xPoints, yPoints, 3);

            if (isSelected) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.BLACK);
            }

            g.fillPolygon(triangle);
        }
    }
}
