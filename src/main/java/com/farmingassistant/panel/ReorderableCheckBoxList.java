package com.farmingassistant.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ReorderableCheckBoxList extends JList<JCheckBox> {
    private DefaultListModel<JCheckBox> listModel;
    private int hoveredIndex = -1;

    public ReorderableCheckBoxList(List<JCheckBox> items) {
        listModel = new DefaultListModel<>();
        for (JCheckBox item : items) {
            listModel.addElement(item);
        }
        setModel(listModel);
        setCellRenderer(new CheckBoxListRenderer());
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setDragEnabled(true);
        setDropMode(DropMode.INSERT);
        setTransferHandler(new CheckBoxTransferHandler());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                int index = locationToIndex(evt.getPoint());
                if (index != -1) {
                    JCheckBox checkbox = listModel.getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoveredIndex = -1;
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                int index = locationToIndex(evt.getPoint());
                if (index != -1 && index != hoveredIndex) {
                    hoveredIndex = index;
                    repaint();
                } else if (index == -1) {
                    hoveredIndex = -1;
                    repaint();
                }
            }
        });
    }

    private JCheckBox createCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setOpaque(true);
        checkBox.setBackground(Color.WHITE);
        checkBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return checkBox;
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    public ListModel<JCheckBox> getListModel() {
        return listModel;
    }

    private class CheckBoxListRenderer extends JPanel implements ListCellRenderer<JCheckBox> {
        private final JCheckBox checkBox;

        public CheckBoxListRenderer() {
            setLayout(new BorderLayout());
            checkBox = new JCheckBox();
            checkBox.setOpaque(true);
            add(checkBox, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends JCheckBox> list, JCheckBox value, int index, boolean isSelected, boolean cellHasFocus) {
            checkBox.setText(value.getText());
            checkBox.setSelected(value.isSelected());
            checkBox.setEnabled(list.isEnabled());

            if (index == hoveredIndex) {
                checkBox.setBackground(Color.LIGHT_GRAY);
                checkBox.setForeground(Color.BLACK); // Set text color to black on hover
            } else {
                checkBox.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                checkBox.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            }

            return this;
        }
    }

    private class CheckBoxTransferHandler extends TransferHandler {
        private int draggedIndex = -1;

        @Override
        public int getSourceActions(JComponent c) {
            return MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            JList<JCheckBox> list = (JList<JCheckBox>) c;
            draggedIndex = list.getSelectedIndex();
            JCheckBox value = list.getSelectedValue();
            return new CheckBoxTransferable(value);
        }

        @Override
        public boolean canImport(TransferSupport info) {
            return info.isDataFlavorSupported(CheckBoxTransferable.CHECKBOX_DATA_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }

            try {
                Transferable transferable = support.getTransferable();
                JCheckBox draggedValue = (JCheckBox) transferable.getTransferData(CheckBoxTransferable.CHECKBOX_DATA_FLAVOR);
                JList.DropLocation dropLocation = (JList.DropLocation) support.getDropLocation();
                int dropIndex = dropLocation.getIndex();

                if (dropIndex > listModel.getSize()) {
                    dropIndex = listModel.getSize();
                }

                if (draggedIndex != -1) {
                    if (draggedIndex < dropIndex) {
                        dropIndex--;
                    }
                    listModel.remove(draggedIndex);
                    listModel.add(dropIndex, draggedValue);
                    draggedIndex = -1;
                }

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private static class CheckBoxTransferable implements Transferable {
        private static final DataFlavor CHECKBOX_DATA_FLAVOR = new DataFlavor(JCheckBox.class, "JCheckBox");
        private final JCheckBox checkBox;

        public CheckBoxTransferable(JCheckBox checkBox) {
            this.checkBox = checkBox;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{CHECKBOX_DATA_FLAVOR};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return CHECKBOX_DATA_FLAVOR.equals(flavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) {
            return checkBox;
        }
    }
}
