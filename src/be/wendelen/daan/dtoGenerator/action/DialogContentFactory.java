package be.wendelen.daan.dtoGenerator.action;

import com.intellij.util.ui.CheckBox;

import javax.swing.*;
import java.awt.*;

public class DialogContentFactory {
    private JPanel jPanel = new JPanel();
    private GridBagLayout gridLayout = new GridBagLayout();

    private ClassSelector classSelector;
    private PackageSelector dtoPackageSelector;
    private PackageSelector mapperPackageSelector;
    private JTextField dtoNameTextField;
    private JTextField mapperNameTextField;
    private JCheckBox generateMapperTest;

    private DialogContentFactory(){}

    public JComponent createContent() {

        jPanel.setLayout(gridLayout);
        jPanel.setPreferredSize(new Dimension(600, 400));

        classSelector.init();
        addLabel("Class to create the DTO from:");
        addRight(classSelector);

        addLabel("Classname DTO:");
        addRight(dtoNameTextField);

        dtoPackageSelector.init();
        addLabel("Package DTO:");
        addRight(dtoPackageSelector);

        addLabel("Classname mapper:");
        addRight(mapperNameTextField);

        mapperPackageSelector.init();
        addLabel("Package mapper:");
        addRight(mapperPackageSelector);

        generateMapperTest.setText("Generate test for mapper.");
        generateMapperTest.setSelected(true);
        addTwoColumn(generateMapperTest);

        return jPanel;
    }

    private void addLabel(String label) {
        JLabel classSelectorLabel = new JLabel(label);
        addLeft(classSelectorLabel);
    }

    private void addLeft(JComponent component){
        component.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        jPanel.add(component, gridBagConstraints);
    }

    private void addRight(JComponent component) {
        component.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        jPanel.add(component, gridBagConstraints);
    }

    private void addTwoColumn(JComponent jComponent) {
        jComponent.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = GridBagConstraints.NONE;
        jPanel.add(jComponent, gridBagConstraints);
    }

    public static Builder newDialogContent() {
        return new Builder();
    }

    public static class Builder {
        private DialogContentFactory dialogContentFactory = new DialogContentFactory();

        public Builder withClassSelector(ClassSelector selector) {
            dialogContentFactory.classSelector = selector;
            return this;
        }

        public Builder withDtoPackageSelector(PackageSelector dtoPackageSelector) {
            dialogContentFactory.dtoPackageSelector = dtoPackageSelector;
            return this;
        }

        public Builder withMapperPackageSelector(PackageSelector mapperPackageSelector) {
            dialogContentFactory.mapperPackageSelector = mapperPackageSelector;
            return this;
        }

        public Builder withDtoNameTextField(JTextField dtoNameTextField) {
            dialogContentFactory.dtoNameTextField = dtoNameTextField;
            return this;
        }

        public Builder withMapperNameTextField(JTextField mapperNameTextField) {
            dialogContentFactory.mapperNameTextField = mapperNameTextField;
            return this;
        }
        public Builder withGenerateMapperTest(JCheckBox generateMapperTest) {
            dialogContentFactory.generateMapperTest = generateMapperTest;
            return this;
        }

        public DialogContentFactory build() {
            if(dialogContentFactory.classSelector == null ||
                    dialogContentFactory.dtoPackageSelector == null ||
                    dialogContentFactory.mapperPackageSelector == null ||
                    dialogContentFactory.dtoNameTextField == null ||
                    dialogContentFactory.mapperNameTextField == null ||
                    dialogContentFactory.generateMapperTest == null) {
                throw new IllegalStateException("None can be null");
            }
            return dialogContentFactory;
        }
    }
}
