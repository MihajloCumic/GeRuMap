package raf.dsw.gerumap.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class ProjectExplorerPanel extends JScrollPane {



    public ProjectExplorerPanel(JTree projectExplorer){
        super(projectExplorer);
        setMinimumSize(new Dimension(200, 150));

    }

}
